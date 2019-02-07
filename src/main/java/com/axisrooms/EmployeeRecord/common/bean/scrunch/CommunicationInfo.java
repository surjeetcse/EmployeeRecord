package com.axisrooms.EmployeeRecord.common.bean.scrunch;

import com.axisrooms.EmployeeRecord.common.enums.staticdump.CommunicationCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunicationInfo {

    private String phone;
    private String email;
    private String fax;
    private String website;
    private String mobile;
    private CommunicationCategory communicationCategory;

    @Tolerate
    public CommunicationInfo() {

    }
}
