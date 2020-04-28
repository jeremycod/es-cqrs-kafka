package controllers


import config.Config.Game
import javax.inject.Inject
import org.webjars.play.WebJarAssets
import play.api.mvc.AbstractController
import play.api.mvc.ControllerComponents
import play.api.libs.ws.WSClient
import scala.concurrent.ExecutionContext.Implicits.global

class MainController @Inject()(webJarAssets: WebJarAssets,
                               cc: ControllerComponents,
                               ws: WSClient,
) extends AbstractController(cc) {

  def ping = Action {
    Ok("Ping");
  }

  def createGame() = Action.async { request =>
    ws.url(s"${Game.apiUrl}/game")
      .post("")
      .map {
        case res if res.status == OK => {
          Ok(res.json)
        }
        case _                       => {
          InternalServerError
        }
      }
      .recover { case e: Exception => {
        InternalServerError
      } }
  }
}
