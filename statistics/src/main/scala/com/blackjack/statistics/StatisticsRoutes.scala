package com.blackjack.statistics
import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.complete

import akka.http.scaladsl.server.Directives.path
import akka.util.Timeout

class StatisticsRoutes (implicit val system: ActorSystem[_]){
	// If ask takes more time than this to complete the request is failed
	private implicit val timeout = Timeout.create(system.settings.config.getDuration("blackjack-app.routes.ask-timeout"))

	//TODO: Create some routes here
	//#all-routes
	val gameRoute =
		(path("statistics") {
		complete(
			StatusCodes.OK
		)
  }
 )
}
