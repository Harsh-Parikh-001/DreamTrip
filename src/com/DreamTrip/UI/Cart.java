package com.DreamTrip.UI;

public class Cart
{
    Cart()
    {
        for(int i=0; i<hotelCounter; i++)
        {
            String hotel_img = Hotel.hotel_image[hotelIndexArray[i]];
            String hotel_name = Hotel.hotel_name[hotelIndexArray[i]];
            String location = Hotel.hotel_location[hotelIndexArray[i]];
            String cost_per_night = Hotel.cost_per_night[hotelIndexArray[i]];
//            ...... Add Other Data Needed For Cart...... For Hotels

//            ....... Add Code to Dynamically Display these Elements in Cart Using Java Fx
        }
        for(int i=0; i<trainCounter; i++)
        {
            String train_img = Trains.train_image[trainIndexArray[i]];
            String origin = Trains.origin[trainIndexArray[i]];
            String destination = Trains.destination[trainIndexArray[i]];
            String train_number = Trains.train_number[trainIndexArray[i]];
            String cost_to_travel = Trains.cost_to_trave[trainIndexArray[i]];
//            ...... Add Other Data Needed For Cart...... For Trains

            //            ....... Add Code to Dynamically Display these Elements in Cart Using Java Fx

        }
        for(int i=0; i<flightCounter; i++)
        {
            String flight_img = Flights.flight_image[flightIndexArray[i]];
            String origin = Flights.flight_origin[flightIndexArray[i]];
            String destination = Flights.flight_destination[flightIndexArray[i]];
            String airlines_name = Flights.airlines_name[flightIndexArray[i]];
            String cost_to_travel = Flights.flight_fare[flightIndexArray[i]];
//            ...... Add Other Data Needed For Cart...... For Trains

            //            ....... Add Code to Dynamically Display these Elements in Cart Using Java Fx

        }    }
}
