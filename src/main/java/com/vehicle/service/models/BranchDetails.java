package com.vehicle.service.models;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BranchDetails {
    String name;
    Map<VehicleType, VehicleDetails> vehicleDetailsMap;
    int moneyEarned;

    public BranchDetails(String branchName) {
        this.name = branchName;
        this.vehicleDetailsMap = new HashMap<>();
        this.vehicleDetailsMap.put(VehicleType.SEDAN, new VehicleDetails(VehicleType.SEDAN));
        this.vehicleDetailsMap.put(VehicleType.SUV, new VehicleDetails(VehicleType.SUV));
        this.vehicleDetailsMap.put(VehicleType.HATCHBACK, new VehicleDetails(VehicleType.HATCHBACK));
    }
}
