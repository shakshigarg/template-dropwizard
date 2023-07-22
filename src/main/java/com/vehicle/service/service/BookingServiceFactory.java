package com.vehicle.service.service;

import com.vehicle.service.models.BookingStrategy;

public class BookingServiceFactory {
    public IVehicleBookingService getBookingService(BookingStrategy bookingStrategy){
        switch (bookingStrategy){
            case FCFS:
                return new FCFSBookingService();
            case LOWEST_PRICE:
                return new LowestPriceBookingService();
        }
        return null;

    }
}
