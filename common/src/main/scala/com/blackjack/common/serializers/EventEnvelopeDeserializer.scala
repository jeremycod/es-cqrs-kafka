package com.blackjack.common.serializers
import com.blackjack.protobuf.game_events.EventEnvelope
import org.apache.kafka.common.serialization.{Deserializer, Serializer}

import scala.util.Try

class EventEnvelopeDeserializer extends Deserializer[EventEnvelope]{
  override def deserialize(topic: String, data: Array[Byte])
    : EventEnvelope = Try {
	  EventEnvelope.parseFrom(data)
  }.get
}
