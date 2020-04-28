package com.blackjack.statistics.actor
import java.util.Properties

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.blackjack.common.KafkaKeys
import com.blackjack.common.KafkaTopics
import com.blackjack.common.serializers.EventEnvelopeSerializer
import com.blackjack.protobuf.game_events.EventEnvelope
import com.blackjack.statistics.actor.GameManager.ActionPerformed
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

object KafkaProducerActor {

	val kafkaProperties = makeKafkaProperties()
	val producer = new KafkaProducer[String, EventEnvelope](kafkaProperties)

	final case class Message(replyTo: ActorRef[ActionPerformed], envelope: EventEnvelope)

  def apply(): Behavior[Message] = {
    Behaviors.receiveMessage[Message] {
      case Message(replyTo, eventEnvelope) =>
        val record = new ProducerRecord[String, EventEnvelope](
          KafkaTopics.EVENT,
	        KafkaKeys.COMMAND,
          eventEnvelope)
        producer.send(record)
        Behaviors.same
    }
  }


	private def makeKafkaProperties(): Properties = {
		val kafkaProperties= new Properties()
		kafkaProperties.setProperty("bootstrap.servers", "localhost:9092")
		kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
		kafkaProperties.put("value.serializer", classOf[EventEnvelopeSerializer])
		kafkaProperties
	}

}
