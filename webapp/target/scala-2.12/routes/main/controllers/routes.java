// @GENERATOR:play-routes-compiler
// @SOURCE:/home/zoran/git_samples/blackjack_game/webapp/conf/routes
// @DATE:Sat Jan 11 18:14:50 PST 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseMainController MainController = new controllers.ReverseMainController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseMainController MainController = new controllers.javascript.ReverseMainController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
