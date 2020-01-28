package com.blackjack.statistics
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.adapter._
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.blackjack.statistics.actor.GameManager

import scala.util.Failure
import scala.util.Success

//#main-class
object BootGame {
  //#start-http-server
  private def startHttpServer(routes: Route, system: ActorSystem[_]): Unit = {
    import Config.Api._
    // Akka HTTP still needs a classic ActorSystem to start
    implicit val classicSystem: akka.actor.ActorSystem = system.toClassic
    import system.executionContext

    val futureBinding = Http().bindAndHandle(routes, bindHost, bindPort)
    futureBinding.onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        system.log.info("Server online at http://{}:{}/", address.getHostString, address.getPort)
      case Failure(ex) =>
        system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
        system.terminate()
    }
  }
  //#start-http-server
  def main(args: Array[String]): Unit = {
    //#server-bootstrapping
    val rootBehavior = Behaviors.setup[Nothing] { context =>
      val gameManagerActor = context.spawn(GameManager(), "GameManagerActor")
      context.watch(gameManagerActor)

      val routes = new GameRoutes(gameManagerActor)(context.system)
      startHttpServer(routes.gameRoute, context.system)

      Behaviors.empty
    }
    val system = ActorSystem[Nothing](rootBehavior, "GameHttpServer")
    //#server-bootstrapping
  }
}
