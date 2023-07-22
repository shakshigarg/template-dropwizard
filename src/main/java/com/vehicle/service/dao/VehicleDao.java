package com.vehicle.service.dao;

import com.vehicle.service.models.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface VehicleDao {
    void addOrUpdateBranch(BranchDetails branchDetails);
    BranchDetails getBranch(String branchName);
    void addOrUpdateVehicleDetails(String branchName, VehicleDetails vehicleDetails);
    VehicleDetails getVehicleDetails(String branch, VehicleType vehicleType);
    void addInVehicleInventory(VehicleType vehicleType, String branchName);
    Set<String> getVehicleInventory(VehicleType vehicleType);

    void updateVehicle(String branchName, VehicleType vehicleType, Vehicle vehicle);

    void addPriceToVehicle(String branchName, VehicleType vehicleType, int price);

    void addVehicleInBranch(String branchName, VehicleType vehicleTypeEnum, Vehicle vehicle);

    void markSlotForVehicle(String branchName, VehicleType vehicleTypeEnum, Vehicle vehicle, Date startTimeDate, Date endTimeDate, String user);
    public Map<String, BranchDetails> getAllDetails();
    public boolean isBranchExists(String branchName);
//    void markVehicleAsBooked(String branchName, VehicleType vehicleType, BookedSlot bookedSlot);
//    void allocatePrice(String branchName,VehicleType vehicleType,int price);

}
