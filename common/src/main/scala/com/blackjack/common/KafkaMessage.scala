package com.blackjack.common



import play.api.libs.json.Format
import play.api.libs.json.Json


case class KafkaMessage(key: String, value: String)

object KafkaMessage {
	implicit val format: Format[KafkaMessage] = Json.format
}