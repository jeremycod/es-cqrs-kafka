import sbt.Keys.scalaVersion
import sbt.Keys.scalacOptions

object Common {
	val commonSettings = Seq(
		scalaVersion    := "2.12.8",
		scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")
	)
	val akkaVersion = "2.6.1"

	val akkaHttpVersion = "10.1.11"
}
