package com.vehicle.service.models;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BookedSlot {
    Date startTime;
    Date endTime;
    String user;
}
