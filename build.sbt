ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.16"

lazy val root = (project in file("."))
  .settings(
    name := "lichess-spark",
    coverageEnabled := true,
    libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.0",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.3.0",

    libraryDependencies += "org.specs2" %% "specs2-core" % "4.5.1" % "test"
    //libraryDependencies += "org.lichess" %% "scalachess" % "10.5.0"
  )

resolvers ++= Seq(
  "lila-maven" at "https://raw.githubusercontent.com/ornicar/lila-maven/master",
  "Artima Maven Repository" at "https://repo.artima.com/releases"
)