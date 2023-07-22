package com.vehicle.service.service;

import com.vehicle.service.api.BookingDetails;
import com.vehicle.service.exception.VehicleServiceException;
import com.vehicle.service.models.BookedSlot;
import com.vehicle.service.models.Vehicle;
import com.vehicle.service.models.VehicleDetails;
import com.vehicle.service.models.VehicleType;
import org.apache.commons.collections4.CollectionUtils;
import java.util.Date;
import java.util.Set;

public class FCFSBookingService implements IVehicleBookingService {
    @Override
    public BookingDetails bookVehicle(VehicleType vehicleTypeEnum, Date startTimeDate, Date endTimeDate, String user) throws VehicleServiceException {
        Set<String> branchHavingVehicleType = vehicleDao.getVehicleInventory(vehicleTypeEnum);
        for (String branchName : branchHavingVehicleType) {
            VehicleDetails vehicleDetails = vehicleDao.getVehicleDetails(branchName, vehicleTypeEnum);
            for (Vehicle vehicle : vehicleDetails.getVehicleSet()) {
                if (CollectionUtils.isEmpty(vehicle.getSlots())) {
                    vehicleDao.markSlotForVehicle(branchName, vehicleTypeEnum, vehicle, startTimeDate, endTimeDate, user);
                    return BookingDetails.builder().vehicleId(vehicle.getId()).branchName(branchName).price(vehicleDetails.getPrice()).build();
                }
                for (BookedSlot bookedSlot : vehicle.getSlots()) {
                    if ((startTimeDate.before(bookedSlot.getStartTime()) && endTimeDate.before(bookedSlot.getStartTime())) || (startTimeDate.after(bookedSlot.getEndTime()) && endTimeDate.after(bookedSlot.getEndTime()))) {
                        return BookingDetails.builder().vehicleId(vehicle.getId()).branchName(branchName).build();
                    }
                }
            }
        }

        throw new VehicleServiceException("Sorry, No Slots Available!");

    }
}
