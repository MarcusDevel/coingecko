name := "coingecko"

ThisBuild / organization := "com.ojerindem.coingecko"
ThisBuild / version      := "0.1-SNAPSHOT"

scalaVersion := "2.13.3"

val circeVersion = "0.13.0"

// https://mvnrepository.com/artifact/log4j/log4j
libraryDependencies += "log4j" % "log4j" % "1.2.17"

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


