package controllers

import javax.inject._
import play.api.ConfigLoader
import play.api.Configuration
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
 * Frontend controller managing all static resource associate routes.
 * @param assets Assets controller reference.
 * @param cc Controller components reference.
 */

class FrontendController @Inject() (
                                     assets: Assets,
                                     meta: AssetsMetadata,
                                     config: Configuration,
                                     cc: ControllerComponents,
                                     ws: WSClient
                                   )(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def getConf[T](name: String, default: T)(
    implicit loader: ConfigLoader[T]
  ): T =
    config.getOptional[T](name).getOrElse(default)

  final val isProduction = true


  def index: Action[AnyContent] =

    if (isProduction) {

      assets.at("index.html")
    } else {
      Action.async { implicit request =>
        ws.url("http://localhost:3000/").get().map {
          response =>

            Ok(response.body)
        }
    }


      }

  /**
   * Serves static front-end assets, and proxies from the local npm server when in development mode.
   * @param resource The URI of a file or asset.
   * @return
   */
  def assets(resource: String): Action[AnyContent] =
    if (isProduction) {
      val aggressiveCaching = resource.contains("static/media")
      val basePath = meta.finder.assetsBasePath
      assets.at(basePath, resource, aggressiveCaching)
    } else
      Action.async { implicit request =>
        ws.url(s"http://localhost:3000/$resource").get().map{
          response =>
            Ok(response.body)
        }
      }
}
