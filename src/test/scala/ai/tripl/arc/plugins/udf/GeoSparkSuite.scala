package ai.tripl.arc.plugins

import ai.tripl.arc.ARC
import ai.tripl.arc.api.API._
import ai.tripl.arc.config.ArcPipeline
import ai.tripl.arc.config.Error._
import ai.tripl.arc.util.TestUtils

import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfter, FunSuite}
import ai.tripl.arc.udf.UDF


class GeoSparkSuite extends FunSuite with BeforeAndAfter {

  var session: SparkSession = _
  val outputView = "outputView"

  before {
    implicit val spark = SparkSession
                  .builder()
                  .master("local[*]")
                  .config("spark.ui.port", "9999")
                  .appName("Spark ETL Test")
                  .getOrCreate()
    spark.sparkContext.setLogLevel("INFO")
    implicit val logger = TestUtils.getLogger()
    implicit val arcContext = TestUtils.getARCContext(isStreaming=false)

    // set for deterministic timezone
    spark.conf.set("spark.sql.session.timeZone", "UTC")

    UDF.registerUDFs()(spark, logger, arcContext)

    session = spark
    import spark.implicits._
  }

  after {
    session.stop()
  }

  test("GeoSparkSuite") {
    implicit val spark = session
    import spark.implicits._
    implicit val logger = TestUtils.getLogger()
    implicit val arcContext = TestUtils.getARCContext(isStreaming=false)

    val conf = s"""{
      "plugins": {
        "config": []
      },
      "stages": [
        {
          "type": "SQLTransform",
          "name": "test",
          "description": "test",
          "environments": [
            "production",
            "test"
          ],
          "inputURI": "${spark.getClass.getResource("/").toString}/basic.sql",
          "outputView": "${outputView}",
          "persist": false,
        }
      ]
    }"""

    val pipelineEither = ArcPipeline.parseConfig(Left(conf), arcContext)

    pipelineEither match {
      case Left(err) => fail(err.toString)
      case Right((pipeline, _)) => {
        val row = ARC.run(pipeline)(spark, logger, arcContext).get.first

        assert(row.getDouble(0) == 650.22)
      }
    }
  }
}
