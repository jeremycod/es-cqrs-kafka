package com.blackjack.statistics

import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.actor.typed.scaladsl.AskPattern._

import scala.util.Failure
import scala.util.Success
import akka.util.Timeout
import com.blackjack.statistics.actor.GameActor
import com.blackjack.statistics.actor.GameManager
import play.api.libs.json.Format
import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.concurrent.Future



class GameRoutes(gameManager: ActorRef[GameManager.GameCommand]) (implicit val system: ActorSystem[_]) {
	// If ask takes more time than this to complete the request is failed
	private implicit val timeout = Timeout.create(system.settings.config.getDuration("blackjack-app.routes.ask-timeout"))
	implicit val cardFormat: Format[GameActor.Card] = Json.format[GameActor.Card]
	implicit val gameStateFormat: Format[GameActor.GameState] = Json.format[GameActor.GameState]
	implicit val updatedStateFormat: Format[GameManager.UpdatedState] = Json.format[GameManager.UpdatedState]
	implicit val responseFormat: Format[GameManager.Response] = Json.format[GameManager.Response]
	implicit val failureResponseFormat: Format[GameManager.FailureResponse] = Json.format[GameManager.FailureResponse]
	implicit val actionPerformedFormat: Format[GameManager.ActionPerformed] = Json.format[GameManager.ActionPerformed]

//TODO: Z https://github.com/keithWcy/WeGame/blob/ba41ff7dbecf25890ed097ddb9c0e5dbcbcb3716/backend/src/main/scala/com/neo/sk/WeGame/http/gameService.scala
	def handleCreate: Future[JsValue] = {
		val future: Future[GameManager.Response] = gameManager.ask[GameManager.Response] {
			ref =>
				GameManager.CreateGame(ref)
		}
		future.transform {
			case Success(actionPerformed) => {
				Success(Json.toJson(actionPerformed))
			}
			case Failure(ex) => Success(Json.toJson(ex.getMessage))
		}(system.executionContext)
	}


	//#all-routes
	val gameRoute =
		(path("game"){
			onSuccess(handleCreate){
				performed =>

				complete(
					  performed.toString()
				)
			}
		})
}
