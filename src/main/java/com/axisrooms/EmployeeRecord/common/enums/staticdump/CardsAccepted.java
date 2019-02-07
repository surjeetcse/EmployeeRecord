package com.axisrooms.EmployeeRecord.common.enums.staticdump;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public enum CardsAccepted {
    VISA,
    MASTERCARD,
    AMERICAN_EXPRESS,
    DISCOVER
}
