import sbt._

object Dependencies {
  // versions
  lazy val sparkVersion = "3.0.2"

  // testing
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.7" % "test,it"

  // arc
  val arc = "ai.tripl" %% "arc" % "3.7.0" % "provided"

  val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

  val geospark = "org.apache.sedona" %% "sedona-core-3.0" % "1.0.0-incubating"
  val geosparkSql = "org.apache.sedona" %% "sedona-sql-3.0" % "1.0.0-incubating"
  val locationtech = "org.locationtech.jts" % "jts-core" % "1.18.0"
  val geotools = "org.datasyslab" % "geotools-wrapper" % "geotools-24.0"

  // Project
  val etlDeps = Seq(
    scalaTest,

    arc,

    sparkSql,

    geospark,
    geosparkSql,
    locationtech,
    geotools,
  )
}