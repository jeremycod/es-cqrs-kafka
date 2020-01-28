
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object game extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>"""),_display_(/*10.13*/message),format.raw/*10.20*/("""</title>

    <link rel='stylesheet' href='"""),_display_(/*12.35*/routes/*12.41*/.Assets.versioned("lib/bootstrap/css/bootstrap.min.css")),format.raw/*12.97*/("""'>
    <link rel='stylesheet' href='"""),_display_(/*13.35*/routes/*13.41*/.Assets.versioned("stylesheets/main.css")),format.raw/*13.82*/("""'>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">BlackJack Game</a>
        </div>
    </div>
</nav>

<div class="container" ng-app="blackjack-game">

    <div ng-switch on="page">

        <div ng-switch-when="create" ng-controller="CreateGameController">
            <p>
                <button ng-disabled="loading" type="button" class="btn btn-lg btn-primary" ng-click="createNewGame()">Create new game</button>
             <button ng-disabled="loading" id="start-game" type="button" class="btn btn-lg btn-primary" ng-click="startGame()">Start game</button>
            </p>
        </div>
     </div>

    <footer>
        <div class="row">
            <div class="col-lg-12">
                <hr />
                <p>Full source code available on <a href="https://github.com/">GitHub</a></p>
                <p>More details available on <a href="http://blog.scalac.io/">ScalaC Team Blog</a></p>
            </div>
        </div>
    </footer>

</div>
<script src='"""),_display_(/*48.15*/routes/*48.21*/.Assets.versioned("lib/jquery/jquery.js")),format.raw/*48.62*/("""' type='text/javascript'></script>
<script src='"""),_display_(/*49.15*/routes/*49.21*/.Assets.versioned("lib/bootstrap/js/bootstrap.js")),format.raw/*49.71*/("""' type='text/javascript'></script>
<script src='"""),_display_(/*50.15*/routes/*50.21*/.Assets.versioned("lib/angularjs/angular.js")),format.raw/*50.66*/("""' type='text/javascript'></script>
<script src='"""),_display_(/*51.15*/routes/*51.21*/.Assets.versioned("javascripts/app.js")),format.raw/*51.60*/("""' type='text/javascript'></script>
<script src='"""),_display_(/*52.15*/routes/*52.21*/.Assets.versioned("javascripts/controllers/create_game_controller.js")),format.raw/*52.91*/("""' type='text/javascript'></script>
<script src='"""),_display_(/*53.15*/routes/*53.21*/.Assets.versioned("javascripts/controllers/start_game_controller.js")),format.raw/*53.90*/("""' type='text/javascript'></script>
<script src='"""),_display_(/*54.15*/routes/*54.21*/.Assets.versioned("javascripts/controllers/game_controller.js")),format.raw/*54.84*/("""' type='text/javascript'></script>
<script src='"""),_display_(/*55.15*/routes/*55.21*/.Assets.versioned("javascripts/services/event_service.js")),format.raw/*55.79*/("""' type='text/javascript'></script>
<script src='"""),_display_(/*56.15*/routes/*56.21*/.Assets.versioned("javascripts/services/command_service.js")),format.raw/*56.81*/("""' type='text/javascript'></script>
    </body>
</html>"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-01-11T18:14:51.647723
                  SOURCE: /home/zoran/git_samples/blackjack_game/webapp/app/views/game.scala.html
                  HASH: 434803e96b1a4a30e881082aadf8eb882b6cb71a
                  MATRIX: 728->1|839->19|1078->231|1106->238|1177->282|1192->288|1269->344|1333->381|1348->387|1410->428|2542->1533|2557->1539|2619->1580|2695->1629|2710->1635|2781->1685|2857->1734|2872->1740|2938->1785|3014->1834|3029->1840|3089->1879|3165->1928|3180->1934|3271->2004|3347->2053|3362->2059|3452->2128|3528->2177|3543->2183|3627->2246|3703->2295|3718->2301|3797->2359|3873->2408|3888->2414|3969->2474
                  LINES: 21->1|26->2|34->10|34->10|36->12|36->12|36->12|37->13|37->13|37->13|72->48|72->48|72->48|73->49|73->49|73->49|74->50|74->50|74->50|75->51|75->51|75->51|76->52|76->52|76->52|77->53|77->53|77->53|78->54|78->54|78->54|79->55|79->55|79->55|80->56|80->56|80->56
                  -- GENERATED --
              */
          