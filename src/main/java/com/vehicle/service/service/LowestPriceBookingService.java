package com.vehicle.service.service;

import com.vehicle.service.api.BookingDetails;
import com.vehicle.service.exception.VehicleServiceException;
import com.vehicle.service.models.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.Set;

public class LowestPriceBookingService implements IVehicleBookingService {

    @Override
    public BookingDetails bookVehicle(VehicleType vehicleTypeEnum, Date startTimeDate, Date endTimeDate, String user) throws VehicleServiceException {
        Set<String> branchHavingVehicleType = vehicleDao.getVehicleInventory(vehicleTypeEnum);
        BookingDetails bookingDetails = null;
        Vehicle vehicleToEdit = null;
        for (String branchName : branchHavingVehicleType) {
            VehicleDetails vehicleDetails = vehicleDao.getVehicleDetails(branchName, vehicleTypeEnum);
            for (Vehicle vehicle : vehicleDetails.getVehicleSet()) {
                if (CollectionUtils.isEmpty(vehicle.getSlots())) {
                    if (bookingDetails == null || (bookingDetails != null && bookingDetails.getPrice() > vehicleDetails.getPrice())) {
                        vehicleToEdit = vehicle;
                        bookingDetails = BookingDetails.builder().vehicleId(vehicle.getId()).branchName(branchName).price(vehicleDetails.getPrice()).build();
                    }
                }
                for (BookedSlot bookedSlot : vehicle.getSlots()) {
                    if ((startTimeDate.before(bookedSlot.getStartTime()) && endTimeDate.before(bookedSlot.getStartTime())) || (startTimeDate.after(bookedSlot.getEndTime()) && endTimeDate.after(bookedSlot.getEndTime()))) {
                        if (bookingDetails == null || (bookingDetails != null && bookingDetails.getPrice() > vehicleDetails.getPrice())) {
                            vehicleToEdit = vehicle;
                            bookingDetails = BookingDetails.builder().vehicleId(vehicle.getId()).branchName(branchName).price(vehicleDetails.getPrice()).build();
                        }
                    }
                }
            }
        }
        if (bookingDetails != null) {
            vehicleDao.markSlotForVehicle(bookingDetails.getBranchName(), vehicleTypeEnum, vehicleToEdit, startTimeDate, endTimeDate, user);
            return bookingDetails;
        }
        throw new VehicleServiceException("Sorry, No Slots Available!");
    }
}
