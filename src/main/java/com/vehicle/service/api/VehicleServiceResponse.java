package com.vehicle.service.api;

import lombok.*;

import javax.ws.rs.core.Response;
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class VehicleServiceResponse {
    String status;
    String message;
}
