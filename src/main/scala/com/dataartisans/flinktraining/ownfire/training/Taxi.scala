package com.dataartisans.flinktraining.ownfire.training

import org.joda.time.DateTime

// Note: The data set contains records with invalid or missing coordinate information (longitude and latitude are 0.0).
object Taxi {

  case class Fare(
                   rideId: Long, // a unique id for each ride
                   taxiId: Long, // a unique id for each taxi
                   driverId: Long, // a unique id for each driver
                   isStart: Boolean, // TRUE for ride start events, FALSE for ride end events
                   startTime: DateTime, // the start time of a ride
                   endTime: DateTime, // the end time of a ride, "1970-01-01 00:00:00" for start events
                   startLon: Float, // the longitude of the ride start location
                   startLat: Float, // the latitude of the ride start location
                   endLon: Float, // the longitude of the ride end location
                   endLat: Float, // the latitude of the ride end location
                   passengerCnt: Short // number of passengers on the ride
                 )

  case class Ride(

                   rideId: Long, // a unique id for each ride
                   taxiId: Long, // a unique id for each taxi
                   driverId: Long, // a unique id for each driver
                   startTime: DateTime, // the start time of a ride
                   paymentType: String, // CSH or CRD
                   tip: Float, // tip for this ride
                   tolls: Float, // tolls for this ride
                   totalFare: Float // total fare collected
                 )

}
