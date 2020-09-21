name := "coingecko"

ThisBuild / organization := "com.ojerindem.coingecko"
ThisBuild / version      := "0.1-SNAPSHOT"

scalaVersion := "2.13.3"

val circeVersion = "0.13.0"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

// https://mvnrepository.com/artifact/com.typesafe/config
libraryDependencies += "com.typesafe" % "config" % "1.4.0"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"

libraryDependencies ++= Seq(
  "io.circe"  %% "circe-core"     % circeVersion,
  "io.circe"  %% "circe-generic"  % circeVersion,
  "io.circe"  %% "circe-parser"   % circeVersion
)

coverageMinimum := 80
coverageFailOnMinimum := true
coverageExcludedPackages := ".*coingecko.app*"


