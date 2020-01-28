package com.blackjack.statistics
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._

object EventsSubscriber {

	val kafkaProperties = makeKafkaProperties()
	val consumer = new KafkaConsumer[String, String](kafkaProperties)
	val topic = "test"


	private def makeKafkaProperties(): Properties = {
		val kafkaProperties= new Properties()
		kafkaProperties.setProperty("bootstrap.servers", "localhost:9092")
		kafkaProperties.put("group.id", "CountryCounter")

		kafkaProperties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		kafkaProperties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		kafkaProperties
	}

	def apply(){
		consumer.subscribe(java.util.Arrays.asList(topic))
		println(s"Subscribe for topic:$topic")
			while (true) {
				val records = consumer.poll(java.time.Duration.ofMillis(1000)).asScala
			  for (record <- records){
				  println(s"Record:${record.toString}")
			  }

			}


	}

}
