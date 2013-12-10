import play.Project._

organization := "pl.jozwik"

name := "scala-gist-reader"

version := "0.1"

lazy val gistReader = ProjectName("gistReader").settings(
  libraryDependencies ++= Seq("org.apache.httpcomponents" % "httpclient" % "4.3.1",
    "commons-io" % "commons-io" % "2.4")
)

lazy val testRunner = {
  ProjectName("testRunner").settings(
    libraryDependencies += "org.eclipse.jgit" % "org.eclipse.jgit" % "3.1.0.201310021548-r"
  ).dependsOn(gistReader)
}

lazy val web = {
  ProjectName("web").settings(playScalaSettings: _*).settings(
    libraryDependencies += "org.fusesource.jansi" % "jansi" % "1.11"
  ).dependsOn(testRunner)
}



libraryDependencies in Global ++= Seq(
  "org.specs2" %% "specs2" % "2.3.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.1" % "test",
  "ch.qos.logback" % "logback-classic" % "1.0.13")



def ProjectName(name: String): Project = (
  Project(name, file(name))
  )
