package com.blackjack.statistics.actor
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.AbstractBehavior
import akka.actor.typed.scaladsl.ActorContext
import akka.actor.typed.scaladsl.Behaviors
import com.blackjack.statistics.GameCommand

class GameCommandRequestActor(context: ActorContext[GameCommand], gameCommand: GameCommand)
extends AbstractBehavior[GameCommand](context){
  override def onMessage(msg: GameCommand)
    : Behavior[GameCommand] =
    ???
}

object GameCommandRequestActor {
	def apply(command: GameCommand): Behavior[GameCommand] = Behaviors.setup(
		context => new GameCommandRequestActor(context, command))
}
