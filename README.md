# Event sourced blackjack game
Simple blackjack game implemented using CQRS and event-sourcing

This example is based on the book [Designing Event-Driven Systems - Concepts and Patterns for Streaming Services with Apache Kafka](https://www.confluent.io/designing-event-driven-systems/)
## Architecture overview

## Running

1. Run Kafka (Docker is enough for quick start): `docker run ... dockerfile/kafka`
2. Run game server: `sbt "project game" run`
3. Run statistics app (optional): `sbt "project statistics" run`
4. Run web application: `sbt "project webapp" run`

 
