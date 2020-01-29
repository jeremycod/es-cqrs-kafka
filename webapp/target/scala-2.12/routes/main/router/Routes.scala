// @GENERATOR:play-routes-compiler
// @SOURCE:/home/zoran/git_samples/blackjack_game/webapp/conf/routes
// @DATE:Tue Jan 28 20:01:29 PST 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  MainController_1: controllers.MainController,
  // @LINE:12
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    MainController_1: controllers.MainController,
    // @LINE:12
    Assets_0: controllers.Assets
  ) = this(errorHandler, MainController_1, Assets_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, MainController_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.MainController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ping""", """controllers.MainController.ping"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """game""", """controllers.MainController.createGame"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_MainController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_MainController_index0_invoker = createInvoker(
    MainController_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_MainController_ping1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ping")))
  )
  private[this] lazy val controllers_MainController_ping1_invoker = createInvoker(
    MainController_1.ping,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainController",
      "ping",
      Nil,
      "GET",
      this.prefix + """ping""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_MainController_createGame2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("game")))
  )
  private[this] lazy val controllers_MainController_createGame2_invoker = createInvoker(
    MainController_1.createGame,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainController",
      "createGame",
      Nil,
      "POST",
      this.prefix + """game""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Assets_versioned3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned3_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_MainController_index0_route(params@_) =>
      call { 
        controllers_MainController_index0_invoker.call(MainController_1.index)
      }
  
    // @LINE:8
    case controllers_MainController_ping1_route(params@_) =>
      call { 
        controllers_MainController_ping1_invoker.call(MainController_1.ping)
      }
  
    // @LINE:9
    case controllers_MainController_createGame2_route(params@_) =>
      call { 
        controllers_MainController_createGame2_invoker.call(MainController_1.createGame)
      }
  
    // @LINE:12
    case controllers_Assets_versioned3_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned3_invoker.call(Assets_0.versioned(path, file))
      }
  }
}
