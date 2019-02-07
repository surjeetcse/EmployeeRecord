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
public class RoomType {
    private String code;
    private String name;
    @Tolerate
    public RoomType() {

    }
}
