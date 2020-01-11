// @GENERATOR:play-routes-compiler
// @SOURCE:/home/zoran/git_samples/game_cards/webapp/conf/routes
// @DATE:Sat Jan 11 11:56:37 PST 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
