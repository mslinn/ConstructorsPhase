organization := "com.micronautics"

name := "ConstructorsPhase"

version := "0.1"

scalaVersion := "2.9.1"

scalacOptions ++= Seq("-deprecation")

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test" withSources()
)

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel := Level.Error

logLevel in compile := Level.Warn

