package com.vehicle.service;

import com.vehicle.service.resources.VehicleServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class VehicleServiceApplication extends Application<VehicleServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new VehicleServiceApplication().run(args);
    }


    @Override
    public void run(VehicleServiceConfiguration vehicleServiceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(VehicleServiceResource.class);
    }

}
