addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.12")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.0.0")

resolvers ++= Seq(
  "Artima Maven Repository" at "https://repo.artima.com/releases"
)