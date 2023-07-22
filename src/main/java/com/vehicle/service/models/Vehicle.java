package com.vehicle.service.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Vehicle {
    String id;
    List<BookedSlot> slots;

    public Vehicle(String id) {
        this.id = id;
        this.slots = new ArrayList<>();
    }
}
