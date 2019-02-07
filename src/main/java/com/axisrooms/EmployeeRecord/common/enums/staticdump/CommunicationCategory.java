package com.axisrooms.EmployeeRecord.common.enums.staticdump;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public enum CommunicationCategory {
    PRIMARY,
    SECONDARY
}
