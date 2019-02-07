package com.axisrooms.EmployeeRecord.common.bean.scrunch;

import com.axisrooms.EmployeeRecord.common.enums.staticdump.AmenityType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomAmenity  {

    private String _id;
    private String name;
    private String description;
    private String category;
    private String roomId;
    private AmenityType type;
    @Tolerate
    public RoomAmenity() {

    }
}

