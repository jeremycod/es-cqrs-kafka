import Common._

name := "game_cards"

version := "0.1"

lazy val `root` = (project in file("."))
	.settings(commonSettings: _*)
  .enablePlugins(PlayScala)
  .dependsOn(common)
  .aggregate(common)
	.dependsOn(game)
	.aggregate(game)
	.dependsOn(statistics)
	.aggregate(statistics)
	.dependsOn(webapp)
	.aggregate(webapp)

lazy val `common` = (project in file("common"))
	.settings(commonSettings: _*)
	.settings(
		inThisBuild(List(
			organization    := "com.blackjack.statistics"
		)),
		libraryDependencies ++= Seq(
			"com.typesafe.play" %% "play-json" % "2.8.0",
			"org.apache.kafka" %% "kafka" % kafkaVersion,
		),
		fork := true
	)
	.settings(PB.targets in Compile := Seq(scalapb.gen() -> (sourceManaged in Compile).value))
	//.enablePlugins(PlayScala)


lazy val `game` = (project in file("game"))
	.settings(commonSettings: _*)
	.settings(
		inThisBuild(List(
			organization    := "com.blackjack.game"
		)),
		resolvers += Resolver.bintrayRepo("cakesolutions", "maven"),
		libraryDependencies ++= Seq(
			"com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
			"com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
			"com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
			"com.typesafe.akka" %% "akka-stream"              % akkaVersion,
			"ch.qos.logback"    % "logback-classic"           % "1.2.3",
			"org.apache.kafka" %% "kafka"                     % kafkaVersion,
			"com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test,
			"com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
			"org.scalatest"     %% "scalatest"                % "3.0.8"         % Test,
			"com.thesamet.scalapb" %% "compilerplugin" % "0.9.0",
			"net.cakesolutions" %% "scala-kafka-client-akka" % "2.0.0"
		),
		fork := true
	)

	.settings(
		resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
	)
	.settings(PB.targets in Compile := Seq(scalapb.gen() -> (sourceManaged in Compile).value))
	//.enablePlugins(PlayScala)
  .dependsOn(common)

lazy val `statistics` = (project in file("statistics"))
	.settings(commonSettings: _*)
	.settings(
		inThisBuild(List(
			organization    := "com.blackjack.statistics"
		)),
		resolvers += Resolver.bintrayRepo("cakesolutions", "maven"),
		libraryDependencies ++= Seq(
			"com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
			"com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
			"com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
			"com.typesafe.akka" %% "akka-stream"              % akkaVersion,
			"ch.qos.logback"    % "logback-classic"           % "1.2.3",
			"org.apache.kafka" %% "kafka"                     % kafkaVersion,
			"com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test,
			"com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
			"org.scalatest"     %% "scalatest"                % "3.0.8"         % Test,
			"com.thesamet.scalapb" %% "compilerplugin" % "0.9.0",
			"net.cakesolutions" %% "scala-kafka-client-akka" % "2.0.0"
		),
		fork := true
	)
	.settings(PB.targets in Compile := Seq(scalapb.gen() -> (sourceManaged in Compile).value))
	.settings(
		resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
	)
	.enablePlugins(PlayScala)
	.dependsOn(common)


lazy val `webapp` = (project in file("webapp")).enablePlugins(PlayScala)
	.settings(commonSettings: _*)
  .settings(watchSources ++= (baseDirectory.value / "webapp" / "ui" ** "*").get)
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
	.disablePlugins(PlayFilters)
	.settings(
		watchSources ++= (baseDirectory.value / "webapp" / "ui" ** "*").get
	)



