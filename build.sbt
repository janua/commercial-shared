name := "commercial-shared"

organization := "com.gu"

bintrayOrganization := Some("guardian")

bintrayRepository := "frontend"

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.gu" % "content-api-models-scala" % "11.0",
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  "net.liftweb" %% "lift-json" % "3.0.1" % Test
)
