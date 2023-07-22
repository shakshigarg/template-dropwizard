package com.vehicle.service.service;

import com.vehicle.service.api.BookingDetails;
import com.vehicle.service.dao.VehicleDao;
import com.vehicle.service.models.*;
import com.vehicle.service.utils.VehicleServiceUtils;
import com.vehicle.service.dao.VehicleDaoImpl;
import com.vehicle.service.exception.VehicleServiceException;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class VehicleService {
    VehicleDao vehicleDao;
    IVehicleBookingService bookingService;
    BookingServiceFactory bookingServiceFactory;

    public VehicleService(BookingStrategy bookingStrategy) {
        this.vehicleDao = new VehicleDaoImpl();
        this.bookingServiceFactory = new BookingServiceFactory();
        this.bookingService = bookingServiceFactory.getBookingService(bookingStrategy);
    }

    public void addBranch(String branchName) throws VehicleServiceException {
        if (vehicleDao.isBranchExists(branchName)) {
            throw new VehicleServiceException("Branch already exists!");
        }
        BranchDetails branchDetails = new BranchDetails(branchName);
        vehicleDao.addOrUpdateBranch(branchDetails);
    }

    public void allocatePrice(String branchName, String vehicleType, int price) throws IllegalArgumentException, VehicleServiceException {
        VehicleType vehicleTypeEnum = VehicleServiceUtils.validateAndGetVehicleType(vehicleType);
        if (!vehicleDao.isBranchExists(branchName)) {
            throw new VehicleServiceException("Branch doesn't exists!");
        }
        vehicleDao.addPriceToVehicle(branchName, vehicleTypeEnum, price);
    }

    public void addVehicle(String vehicleId, String vehicleType, String branchName) throws IllegalArgumentException, VehicleServiceException {
        VehicleType vehicleTypeEnum = VehicleServiceUtils.validateAndGetVehicleType(vehicleType);
        if (!vehicleDao.isBranchExists(branchName)) {
            throw new VehicleServiceException("Branch doesnt exists!");
        }
        VehicleDetails vehicleDetails = vehicleDao.getVehicleDetails(branchName, vehicleTypeEnum);
        if (VehicleServiceUtils.isVehicleExists(vehicleDetails.getVehicleSet(), vehicleId)) {
            throw new VehicleServiceException("Vehicle already exists!");
        }
        Vehicle vehicle = new Vehicle(vehicleId);
        vehicleDao.addVehicleInBranch(branchName, vehicleTypeEnum, vehicle);
        vehicleDao.addInVehicleInventory(vehicleTypeEnum, branchName);
    }

    public Map<String, BranchDetails> getAvailableInventory() {
        return vehicleDao.getAllDetails();
    }

    public BookingDetails bookVehicle(String vehicleType, String startTime, String endTime, String user) throws IllegalArgumentException, ParseException, VehicleServiceException {
        Date startTimeDate = VehicleServiceUtils.getDateFromString(startTime);
        Date endTimeDate = VehicleServiceUtils.getDateFromString(endTime);
        VehicleType vehicleTypeEnum = VehicleServiceUtils.validateAndGetVehicleType(vehicleType);
        return bookingService.bookVehicle(vehicleTypeEnum, startTimeDate, endTimeDate, user);
    }
}
