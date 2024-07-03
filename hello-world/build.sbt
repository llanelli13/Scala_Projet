lazy val root = (project in file("."))
  .aggregate(core, app)
  .settings(
    name := "functional-graphs",
    version := "0.1.0",
    scalaVersion := "3.0.2"
  )

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.0",
      "dev.zio" %% "zio-json" % "0.1.5",
      "org.scalatest" %% "scalatest" % "3.2.10" % Test
    )
  )

lazy val app = (project in file("app"))
  .dependsOn(core)
  .settings(
    name := "app"
  )
