package com.blackjack.statistics.actor
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object EventListenerActor {

	sealed trait Command
	final case class CreateGame() extends Command

	def apply(): Behavior[Command] = listen

	private def listen(): Behavior[Command] =
		Behaviors.receiveMessage {
			case CreateGame() =>
				println("Received command CreateGame")
			Behaviors.same
		}

}

