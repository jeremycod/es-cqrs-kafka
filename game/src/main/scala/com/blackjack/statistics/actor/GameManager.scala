package com.blackjack.statistics.actor

import java.util.Properties

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.receptionist.Receptionist
import akka.actor.typed.receptionist.ServiceKey
import akka.actor.typed.scaladsl.Behaviors
import com.blackjack.statistics.actor.GameManager.kafkaProperties
import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord



object GameManager {

  val logger = Logger(GameManager.getClass)
  // actor protocol

  sealed trait GameCommand
  final case class CreateGame(replyTo: ActorRef[ActionPerformed]) extends GameCommand

  final case class ActionPerformed()

//TODO: Temporary This should go in separate Actor
  val kafkaProperties = makeKafkaProperties()


  val producer = new KafkaProducer[String, String](kafkaProperties)

  val topic = "test"

  val message = "Sample message"



  def apply(): Behavior[GameCommand] = Behaviors.setup {
  context =>
  Behaviors.withStash(1000){
    stash =>
      context.system.receptionist ! Receptionist.Register(Key, context.self)
      handleRequest()
  }

}

  def handleRequest(): Behavior[GameCommand] = Behaviors.receiveMessage {
    case CreateGame(replyTo) =>
      println("CREATE GAME")
      // TODO: Create some behaviour here
      val record = new ProducerRecord[String, String](topic, message)
       producer.send(record)
      println("Sent message to kafka")


      replyTo ! ActionPerformed()
    Behaviors.same
  }

  private def makeKafkaProperties(): Properties = {
    val kafkaProperties= new Properties()
    kafkaProperties.setProperty("bootstrap.servers", "localhost:9092")
    kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    kafkaProperties
  }

  val Key: ServiceKey[GameCommand] = ServiceKey("gameManager")
}
//#game-manager-actor
