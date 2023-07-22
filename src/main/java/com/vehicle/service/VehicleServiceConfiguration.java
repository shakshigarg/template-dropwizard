package com.vehicle.service;

import com.vehicle.service.models.BookingStrategy;
import io.dropwizard.Configuration;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class VehicleServiceConfiguration extends Configuration {
    BookingStrategy bookingStrategy;
}
