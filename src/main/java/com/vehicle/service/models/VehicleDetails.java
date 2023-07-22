package com.vehicle.service.models;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.vehicle.service.constants.ServiceConstants.DEFAULT_PRICE;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class VehicleDetails {
    int price;
    Set<Vehicle> vehicleSet;
    VehicleType vehicleType;

    VehicleDetails(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        this.price = DEFAULT_PRICE;
        this.vehicleSet = new HashSet<>();
    }
}
