package com.axisrooms.EmployeeRecord.common.bean.scrunch;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointOfInterest {

    private String nameOfAttraction;
    private String distanceInKm;
    private String description;
    @Tolerate
    public PointOfInterest() {

    }
}
