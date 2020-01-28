package com.blackjack.statistics
import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.actor.typed.scaladsl.AskPattern._
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.util.Timeout
import com.blackjack.statistics.actor.GameManager

import scala.concurrent.Future


class GameRoutes(gameManager: ActorRef[GameManager.GameCommand]) (implicit val system: ActorSystem[_]) {
	// If ask takes more time than this to complete the request is failed
	private implicit val timeout = Timeout.create(system.settings.config.getDuration("blackjack-app.routes.ask-timeout"))


	def handleCreate: Future[GameManager.ActionPerformed] = {
		println("Handle create")
		gameManager.ask(GameManager.CreateGame)

	}


	//#all-routes
	val gameRoute =
		(path("game"){
			onSuccess(handleCreate){
				performed =>
				complete(

					  StatusCodes.OK

				)
			}
		})
}
