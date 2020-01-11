import Common._

name := "game_cards"

version := "0.1"



lazy val `game` = (project in file("game"))
	.settings(commonSettings: _*)
	.settings(
		inThisBuild(List(
			organization    := "com.blackjack.game"
		)),
		libraryDependencies ++= Seq(
			"com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
			"com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
			"com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
			"com.typesafe.akka" %% "akka-stream"              % akkaVersion,
			"ch.qos.logback"    % "logback-classic"           % "1.2.3",

			"com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test,
			"com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
			"org.scalatest"     %% "scalatest"                % "3.0.8"         % Test
		),
		fork := true
	)
	.settings(
		resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
	)


lazy val `webapp` = (project in file("webapp")).enablePlugins(PlayScala)
	.settings(commonSettings: _*)
	.settings(
		libraryDependencies ++= Seq(
			jdbc , ehcache , ws , specs2 % Test , guice,
			"org.webjars" %% "webjars-play" % "2.6.3",
			"org.webjars" % "angularjs" % "1.7.9",
			"org.webjars" % "bootstrap" % "4.4.1"
		),
		fork := true
	)
	.settings(
		resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
	)
	.enablePlugins(PlayScala)



