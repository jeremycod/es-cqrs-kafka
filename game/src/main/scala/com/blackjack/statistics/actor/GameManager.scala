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
import com.blackjack.statistics.actor.GameActor.InitDeck
import com.blackjack.statistics.actor.KafkaProducerActor.Message
import com.typesafe.scalalogging.Logger
import akka.actor.typed.scaladsl.AskPattern._
import akka.util.Timeout
import com.blackjack.statistics.actor.GameActor.GameState
import akka.pattern.{ask, pipe}
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success

object GameManager {

  val logger = Logger(GameManager.getClass)

  // actor protocol

  sealed trait GameCommand
  final case class CreateGame(replyTo: ActorRef[Response]) extends GameCommand
  final case class FinishGame(replyTo: ActorRef[Response]) extends GameCommand
  final case class UpdatedState(newState: GameState) extends GameCommand

  sealed trait Response
  final case class ActionPerformed(
    gameId: String,
    actionName: String,
    newState: GameState
  ) extends Response

  final case class FailureResponse(gameId: String, actionName: String, message: String) extends Response






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
      implicit val timeout = Timeout.create(context.system.settings.config.getDuration("blackjack-app.routes.ask-timeout"))
      implicit val scheduler = context.system.scheduler
      implicit val ec = context.executionContext
      val kafkaProducer = context.spawnAnonymous(KafkaProducerActor())

      val createGameMessage = GameStarted().update(
        _.gameId := UUID.randomUUID().toString
      )
      val deckActor = context.spawnAnonymous(GameActor.init)

      val future: Future[GameManager.UpdatedState] = deckActor.ask[GameManager.UpdatedState]{
        ref =>
          InitDeck(createGameMessage.gameId, ref)

      }
      future.onComplete{
        case Success(updatedState) =>{
          replyTo ! ActionPerformed(createGameMessage.gameId, "GameStarted", updatedState.newState)
          val eventEnvelope = EventEnvelope().update(
            _.correlationId := Generators.correlationId,
            _.gameStarted := createGameMessage
          )
          kafkaProducer ! Message(replyTo, eventEnvelope)
          }
        case Failure(err) => replyTo ! FailureResponse(createGameMessage.gameId, "GameStarted", err.getLocalizedMessage)
      }





      Behaviors.same
  }

  def playGame(): Behavior[GameCommand] =
    Behaviors.setup { context =>
      Behaviors.receiveMessagePartial {
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
