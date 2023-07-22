package com.vehicle.service.api;

import com.vehicle.service.models.BranchDetails;
import lombok.*;

import java.util.Map;

@Data
@ToString
@EqualsAndHashCode
public class VehicleInventoryResponse extends com.vehicle.service.api.VehicleServiceResponse {

    Map<String, BranchDetails> inventory;

    public VehicleInventoryResponse(String status, String message, Map<String, BranchDetails> inventory) {
        super(status, message);
        this.inventory = inventory;
    }

}
