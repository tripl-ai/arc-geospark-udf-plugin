import sbt._

object Dependencies {
  // versions
  lazy val sparkVersion = "3.0.1"

  // testing
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.7" % "test,it"

  // arc
  val arc = "ai.tripl" %% "arc" % "3.4.0" % "provided"

  val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

  // val geospark = "org.datasyslab" % "geospark" % "1.3.2-SNAPSHOT"
  // val geosparkSql = "org.datasyslab" % "geospark-sql_3.0" % "1.3.2-SNAPSHOT"

  // Project
  val etlDeps = Seq(
    scalaTest,

    arc,

    sparkSql,

    // geospark,
    // geosparkSql
  )
}