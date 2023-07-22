package com.vehicle.service.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleBookingResponse extends com.vehicle.service.api.VehicleServiceResponse {
    com.vehicle.service.api.BookingDetails bookingDetails;
    public VehicleBookingResponse(String status, String message) {
        super(status, message);
    }
    public VehicleBookingResponse(String status, String message, com.vehicle.service.api.BookingDetails bookingDetails) {
        super(status, message);
        this.bookingDetails=bookingDetails;
    }
}
