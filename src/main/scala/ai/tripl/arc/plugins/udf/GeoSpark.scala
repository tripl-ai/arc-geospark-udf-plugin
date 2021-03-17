package ai.tripl.arc.plugins.udf

import scala.collection.JavaConverters._

import org.apache.spark.sql.SparkSession

import ai.tripl.arc.util.log.logger.Logger
import ai.tripl.arc.api.API.ARCContext
import ai.tripl.arc.util.Utils

import org.apache.sedona.sql.utils.SedonaSQLRegistrator

class GeoSpark extends ai.tripl.arc.plugins.UDFPlugin {

  val version = ai.tripl.arc.plugins.udf.geospark.BuildInfo.version

  // one udf plugin can register multiple user defined functions
  override def register()(implicit spark: SparkSession, logger: Logger, arcContext: ARCContext) = {
    SedonaSQLRegistrator.registerAll(spark)
  }
}