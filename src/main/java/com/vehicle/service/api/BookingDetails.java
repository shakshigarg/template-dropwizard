package com.vehicle.service.api;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BookingDetails {
    String vehicleId;
    String branchName;
    int price;
}
