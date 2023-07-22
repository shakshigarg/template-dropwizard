package com.vehicle.service.dao;

import com.vehicle.service.db.InMemoryDb;
import com.vehicle.service.models.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class VehicleDaoImpl implements VehicleDao {

    InMemoryDb inMemoryDb = InMemoryDb.getInstance();

    @Override
    public void addOrUpdateBranch(BranchDetails branchDetails) {
        inMemoryDb.getVehicleDb().put(branchDetails.getName(), branchDetails);
    }

    public boolean isBranchExists(String branchName) {
        return inMemoryDb.getVehicleDb().get(branchName) != null;
    }

    @Override
    public BranchDetails getBranch(String branchName) {
        return inMemoryDb.getVehicleDb().get(branchName);
    }

    @Override
    public void addOrUpdateVehicleDetails(String branchName, VehicleDetails vehicleDetails) {
        inMemoryDb.getVehicleDb().get(branchName).getVehicleDetailsMap().put(vehicleDetails.getVehicleType(), vehicleDetails);

    }

    @Override
    public VehicleDetails getVehicleDetails(String branch, VehicleType vehicleType) {
        return inMemoryDb.getVehicleDb().get(branch).getVehicleDetailsMap().get(vehicleType);
    }

    @Override
    public void addInVehicleInventory(VehicleType vehicleType, String branchName) {
        inMemoryDb.getVehicleInventory().get(vehicleType).add(branchName);
    }

    @Override
    public Set<String> getVehicleInventory(VehicleType vehicleType) {
        return inMemoryDb.getVehicleInventory().get(vehicleType);
    }

    @Override
    public void updateVehicle(String branchName, VehicleType vehicleType, Vehicle vehicle) {
        inMemoryDb.getVehicleDb().get(branchName).getVehicleDetailsMap().get(vehicleType).getVehicleSet().add(vehicle);
    }

    @Override
    public void addPriceToVehicle(String branchName, VehicleType vehicleType, int price) {
        inMemoryDb.getVehicleDb().get(branchName).getVehicleDetailsMap().get(vehicleType).setPrice(price);
    }

    @Override
    public void addVehicleInBranch(String branchName, VehicleType vehicleTypeEnum, Vehicle vehicle) {
        inMemoryDb.getVehicleDb().get(branchName).getVehicleDetailsMap().get(vehicleTypeEnum).getVehicleSet().add(vehicle);
    }

    @Override
    public void markSlotForVehicle(String branchName, VehicleType vehicleTypeEnum, Vehicle vehicle, Date startTimeDate, Date endTimeDate, String user) {
        vehicle.getSlots().add(BookedSlot.builder().startTime(startTimeDate).endTime(endTimeDate).user(user).build());
    }

    public Map<String, BranchDetails> getAllDetails() {
        return inMemoryDb.getVehicleDb();
    }

}
