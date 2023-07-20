package com.vehicle.service.resources;

import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;


@Path("/vehicle-service")
@Produces(MediaType.APPLICATION_JSON)
public class VehicleServiceResource {
    @GET
    @Path("/book-vehicle")
    public String  bookVehicle(){
        return "success";
        }
}
