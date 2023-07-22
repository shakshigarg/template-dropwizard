package com.vehicle.service.utils;

import com.vehicle.service.models.Vehicle;
import com.vehicle.service.models.VehicleType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class VehicleServiceUtils {

    public static VehicleType validateAndGetVehicleType(String vehicleType) throws IllegalArgumentException {
            return VehicleType.valueOf(vehicleType);
    }

    public static boolean isVehicleExists(Set<Vehicle> vehicleList, String vehicleId) {
        return vehicleList.stream().anyMatch(vehicle -> vehicle.getId().equals(vehicleId));
    }
    public static Date getDateFromString(String dateString) throws ParseException {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(dateString);
            return date;
    }
}
