package com.blackjack.common.serializers
import com.blackjack.protobuf.game_events.EventEnvelope
import org.apache.kafka.common.serialization.{Deserializer, Serializer}


class EventEnvelopeSerializer extends Serializer[EventEnvelope]{
  override def serialize(
    topic: String,
    data: EventEnvelope)
    : Array[Byte] = data.toByteArray
}
