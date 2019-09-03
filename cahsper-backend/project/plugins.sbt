import sbt.util

logLevel := util.Level.Warn

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.0.4")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0")
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.7")
