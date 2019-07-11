package com.dataartisans.flinktraining.ownfire.training

import org.apache.flink.streaming.api.scala._
import com.dataartisans.flinktraining.exercises.datastream_java.sources.{TaxiFareSource, TaxiRideSource}
import com.dataartisans.flinktraining.exercises.datastream_java.utils.{ExerciseBase, GeoUtils}
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.joda.time.DateTime

object Example01 {

  def main(args: Array[String]): Unit = {

    // get an ExecutionEnvironment
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // configure event-time processing
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val maxDelay = 100
    val servingSpeed = 200000

    // get the taxi ride data stream
    val rides = env.addSource(
      new TaxiRideSource(ExerciseBase.pathToRideData,
        maxDelay, servingSpeed))

/*
    // get the taxi fare data stream
    val fares = env.addSource(
      new TaxiFareSource(
        ExerciseBase.pathToFareData,
        maxDelay, servingSpeed))
*/

   val nyTexi = rides.filter(row => GeoUtils.isInNYC(row.startLon, row.endLon))


    nyTexi.print()
    //fares.print()

    env.execute()
  }

}
