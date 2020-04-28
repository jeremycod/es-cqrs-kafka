package com.blackjack.statistics.actor

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.blackjack.statistics.actor.GameActor.Card
import com.blackjack.statistics.actor.GameManager.ActionPerformed
import com.blackjack.statistics.actor.GameManager.Response
import com.blackjack.statistics.actor.GameManager.UpdatedState

import scala.collection.immutable.Stack
import scala.util.Random


object GameActor {
	sealed trait Command
	final case class InitDeck(gameId: String, replyTo: ActorRef[GameManager.UpdatedState]) extends Command
	final case class ShuffleDeck(replyTo: ActorRef[GameManager.Response]) extends Command

	val ranks = List("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
	val suits = List("hearts", "spades", "diamonds", "clubs")

	sealed trait Response
	case class Card(rank: String, suit: String)
	case class GameState(cards: List[Card], dealerCards: List[Card], playerCards: List[Card]) extends Response


	def init(): Behavior[Command] = Behaviors.receivePartial[Command] {
		case (context, InitDeck(gameId, replyTo)) =>
			val initDeck = for (rank <- ranks; suit <- suits) yield Card(rank, suit)
			val shuffledDeck = Random.shuffle(initDeck)
			val (dealerCards, remaindingCards) = shuffledDeck.splitAt(1)
			val (playerCards, deckCards) = remaindingCards.splitAt(2)
			val newState = GameState(deckCards, dealerCards, playerCards)
			replyTo ! UpdatedState(newState)
	  	play(newState)
	}

	def play(state: GameState):Behavior[Command] = Behaviors.receivePartial[Command] {
		case (context, ShuffleDeck(replyTo)) =>
			play(GameState(Random.shuffle(state.cards), state.dealerCards, state.playerCards))
	}

}
