// @GENERATOR:play-routes-compiler
// @SOURCE:/home/zoran/git_samples/blackjack_game/webapp/conf/routes
// @DATE:Sat Jan 11 18:14:50 PST 2020


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
