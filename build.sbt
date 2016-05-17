name := "reactiveProgramming"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "io.reactivex" %% "rxscala" % "0.26.1"

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.4.1"
    