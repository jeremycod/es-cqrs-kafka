package controllers

import config.Config.Game
import javax.inject.Inject
import org.webjars.play.WebJarAssets
import play.api.mvc.AbstractController
import play.api.mvc.ControllerComponents
import play.libs.ws.WSClient
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class MainController  @Inject()(
	                               webJarAssets: WebJarAssets,
                                cc: ControllerComponents,
                                ws: WSClient,
                                ) extends AbstractController(cc) {

	def index = Action {
		Ok(views.html.game("Your Game is ready."))
	}

	def createGame() = Action.async { request =>
	Future{
		ws.url(s"${Game.apiUrl}/game")
			.post("")
		Ok("")
	}


	}
}
