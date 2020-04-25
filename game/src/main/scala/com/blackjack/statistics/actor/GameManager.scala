package com.blackjack.statistics.actor

import java.util.Properties
import java.util.UUID

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.receptionist.Receptionist
import akka.actor.typed.receptionist.ServiceKey
import akka.actor.typed.scaladsl.ActorContext
import akka.actor.typed.scaladsl.Behaviors
import com.blackjack.common.KafkaTopics
import com.blackjack.common.helpers.Generators
import com.blackjack.common.serializers.EventEnvelopeSerializer
import com.blackjack.protobuf.game_events.EventEnvelope
import com.blackjack.protobuf.game_events.GameStarted
import com.blackjack.statistics.actor.KafkaProducerActor.Message
import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord



object GameManager {

  val logger = Logger(GameManager.getClass)
  // actor protocol

  sealed trait GameCommand
  final case class CreateGame(replyTo: ActorRef[ActionPerformed]) extends GameCommand
  final case class FinishGame(replyTo: ActorRef[ActionPerformed]) extends GameCommand

  final case class ActionPerformed()

  def apply(): Behavior[GameCommand] = Behaviors.setup {
  context =>
  Behaviors.withStash(1000){
    stash =>
     // context.system.receptionist ! Receptionist.Register(Key, context.self)

        createGame(context)

  }

}

  def createGame(context: ActorContext[GameCommand]): Behavior[GameCommand] = Behaviors.receiveMessagePartial[GameCommand] {
    case CreateGame(replyTo) =>
      val kafkaProducer = context.spawn(KafkaProducerActor(), "KafkaProducer")
      // TODO: Create some behaviour here
      val createGameMessage = GameStarted().update(
        _.gameId := UUID.randomUUID().toString
      )
      val eventEnvelope = EventEnvelope().update(
        _.correlationId := Generators.correlationId,
        _.gameStarted := createGameMessage
      )

      kafkaProducer ! Message(eventEnvelope)
      Behaviors.same
  }

  def playGame(): Behavior[GameCommand] =
    Behaviors.setup { context =>
      Behaviors.receiveMessage {
        case CreateGame(replyTo) =>
          context.log.error("Game is already created")
          Behaviors.same
        case FinishGame(replyTo) =>
        context.log.info("Finish game")
        apply()
      }

    }


  val Key: ServiceKey[GameCommand] = ServiceKey("gameManager")
}
//#game-manager-actor
