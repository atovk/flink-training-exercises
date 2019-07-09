package com.dataartisans.flinktraining.ownfire.training

import org.apache.flink.streaming.api.scala._
import com.dataartisans.flinktraining.exercises.datastream_java.sources.{TaxiFareSource, TaxiRideSource}
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object Example01 {

  def main(args: Array[String]): Unit = {

    // get an ExecutionEnvironment
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // configure event-time processing
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val maxDelay = 10
    val servingSpeed = 2

    // get the taxi ride data stream
    val rides = env.addSource(
      new TaxiRideSource(
        ClassLoader.getSystemClassLoader.getResource("nycTaxiRides.gz").getPath,
        maxDelay, servingSpeed))

    // get the taxi fare data stream
    val fares = env.addSource(
      new TaxiFareSource(
        ClassLoader.getSystemClassLoader.getResource("nycTaxiFares.gz").getPath,
        maxDelay, servingSpeed))


    rides.print()
    fares.print()

    env.execute()
  }

}
