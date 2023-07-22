package com.vehicle.service.service;

import com.vehicle.service.api.BookingDetails;
import com.vehicle.service.dao.VehicleDao;
import com.vehicle.service.dao.VehicleDaoImpl;
import com.vehicle.service.exception.VehicleServiceException;
import com.vehicle.service.models.VehicleType;
import java.util.Date;

public interface IVehicleBookingService {
    public VehicleDao vehicleDao=new VehicleDaoImpl();
    public BookingDetails bookVehicle(VehicleType vehicleType, Date startTimeDate, Date endTimeDate, String user) throws VehicleServiceException;
}
