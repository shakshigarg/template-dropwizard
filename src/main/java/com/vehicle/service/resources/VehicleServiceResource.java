package com.vehicle.service.resources;

import com.vehicle.service.api.BookingDetails;
import com.vehicle.service.api.VehicleBookingResponse;
import com.vehicle.service.api.VehicleInventoryResponse;
import com.vehicle.service.api.VehicleServiceResponse;
import com.vehicle.service.exception.VehicleServiceException;
import com.vehicle.service.models.BookingStrategy;
import com.vehicle.service.service.VehicleService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.text.ParseException;


@Path("/vehicle-service")
@Produces(MediaType.APPLICATION_JSON)
public class VehicleServiceResource {
    VehicleService vehicleService;

    @Inject
    public VehicleServiceResource(BookingStrategy bookingStrategy) {
        vehicleService = new VehicleService(bookingStrategy);
    }

    @GET
    @Path("/book-vehicle")
    public VehicleServiceResponse bookVehicle(@QueryParam("vehicleType") @NotNull String vehicleType, @QueryParam("startTime") @NotNull String startTime, @QueryParam("endTime") @NotNull String endTime,@QueryParam("user") @NotNull String user) {
        try {
            BookingDetails bookingDetails = vehicleService.bookVehicle(vehicleType, startTime, endTime,user);
            return new VehicleBookingResponse("200", "Vehicle booked Successfully!", bookingDetails);
        } catch (ParseException vehicleServiceException) {
            return VehicleServiceResponse.builder().status("400").message("Invalid start or end date! Make sure to provide in yyyy-MM-dd'T'HH:mm:ss format").build();
        } catch (IllegalArgumentException illegalArgumentException) {
            return VehicleServiceResponse.builder().message("Vehicle type not supported").status("400").build();
        } catch (VehicleServiceException e) {
            return VehicleServiceResponse.builder().message(e.getMessage()).status("404").build();
        }
    }

    @GET
    @Path("/vehicle-inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public VehicleInventoryResponse getInventory() {
        return new VehicleInventoryResponse("Inventory loaded", "200", vehicleService.getAvailableInventory());

    }

    @GET
    @Path("/add-branch")
    public VehicleServiceResponse addBranch(@QueryParam("branchName") @NotNull String branchName) {
        try {
            vehicleService.addBranch(branchName);
            return VehicleServiceResponse.builder().message("Branch added successfully").status("200").build();
        } catch (VehicleServiceException e) {
            return VehicleServiceResponse.builder().message("Branch already exists!").status("400").build();
        }
    }

    @GET
    @Path("/allocate-price")
    public VehicleServiceResponse allocatePrice(@QueryParam("branchName") @NotNull String branchName, @QueryParam("vehicleType") @NotNull String vehicleType, @QueryParam("price") @NotNull int price) {
        try {
            vehicleService.allocatePrice(branchName, vehicleType, price);
            return VehicleServiceResponse.builder().message("Price allocated successfully").status("200").build();
        } catch (IllegalArgumentException exception) {
            return VehicleServiceResponse.builder().message("Vehicle type not supported").status("400").build();
        } catch (VehicleServiceException e) {
            return VehicleServiceResponse.builder().message(e.getMessage()).status("400").build();
        }
    }

    @GET
    @Path("/add-vehicle")
    public VehicleServiceResponse addVehicle(@QueryParam("vehicleId") @NotNull String vehicleId, @QueryParam("vehicleType") @NotNull String vehicleType, @QueryParam("branchName") @NotNull String branchName) {
        try {
            vehicleService.addVehicle(vehicleId, vehicleType, branchName);
            return VehicleServiceResponse.builder().message("Vehicle Added successfully").status("200").build();
        } catch (IllegalArgumentException exception) {
            return VehicleServiceResponse.builder().message("Vehicle already present!").status("400").build();
        } catch (VehicleServiceException e) {
            return VehicleServiceResponse.builder().message(e.getMessage()).status("400").build();
        }
    }
}
