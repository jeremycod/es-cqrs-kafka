package com.blackjack.statistics
import java.util.Properties

import akka.actor.ActorSystem
import akka.actor.typed.ActorRef
import akka.actor.typed.scaladsl.ActorContext
import akka.event.Logging
import com.blackjack.common.KafkaTopics
import com.blackjack.common.serializers.EventEnvelopeDeserializer
import com.blackjack.protobuf.game_events.EventEnvelope
import com.blackjack.protobuf.game_events.EventEnvelope.Payload
import com.blackjack.protobuf.game_events.GameStarted
import com.blackjack.statistics.actor.EventListenerActor
import com.blackjack.statistics.actor.EventListenerActor.CreateGame
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._





class EventsSubscriber(eventListenerActor: ActorRef[EventListenerActor.Command], ctx: ActorContext[_]) {
	val kafkaProperties = makeKafkaProperties()
	val consumer = new KafkaConsumer[String, EventEnvelope](kafkaProperties)

	consumer.subscribe(java.util.Arrays.asList(KafkaTopics.EVENT))
	ctx.log.info(s"Subscribe for topic:${KafkaTopics.EVENT}")
	while (true) {
		val records = consumer.poll(java.time.Duration.ofMillis(1000)).asScala
		for (record <- records){
			val payload = record.value.payload
			payload match {
				case Payload.GameStarted(gameId) =>
					ctx.log.info(s"Game started for:$gameId")
					eventListenerActor ! CreateGame()
					case _ =>
					ctx.log.info("UNKNOWN MESSAGE")

			}
			 //val createGameMessage = GameStarted.parseFrom(record.value)
			//ctx.log.info(s"Create game message received:${createGameMessage.gameId}")


		}

	}

	private def processEventMessage(key: String, envelope: EventEnvelope): Either[String, GameStarted] = {
		envelope.payload match {
			case Payload.GameStarted(gameStarted) =>
				ctx.log.info(s"Game $gameStarted.gameIdId started")
				Right(GameStarted(gameStarted.gameId))
				case _ => Left("Error")
		}

	}

	private def makeKafkaProperties(): Properties = {
		val kafkaProperties= new Properties()
		kafkaProperties.setProperty("bootstrap.servers", "localhost:9092")
		kafkaProperties.put("group.id", "CountryCounter")

		kafkaProperties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		kafkaProperties.put("value.deserializer",classOf[EventEnvelopeDeserializer]);
		kafkaProperties
	}


}
