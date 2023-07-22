package com.vehicle.service.db;

import com.vehicle.service.models.BranchDetails;
import com.vehicle.service.models.VehicleType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryDb {
    Map<String, BranchDetails> vehicleDb;
    Map<VehicleType, Set<String>> vehicleInventory;
    private static final InMemoryDb INSTANCE = new InMemoryDb();

    private InMemoryDb() {
        vehicleDb = new HashMap<>();
        vehicleInventory = new HashMap<>();
        vehicleInventory.put(VehicleType.SEDAN, new HashSet<>());
        vehicleInventory.put(VehicleType.SUV, new HashSet<>());
        vehicleInventory.put(VehicleType.HATCHBACK, new HashSet<>());
    }

    public static InMemoryDb getInstance() {
        return INSTANCE;
    }

    public Map<String, BranchDetails> getVehicleDb() {
        return vehicleDb;
    }

    public void setVehicleDb(Map<String, BranchDetails> vehicleDb) {
        this.vehicleDb = vehicleDb;
    }

    public Map<VehicleType, Set<String>> getVehicleInventory() {
        return vehicleInventory;
    }

    public void setVehicleInventory(Map<VehicleType, Set<String>> vehicleInventory) {
        this.vehicleInventory = vehicleInventory;
    }
}
