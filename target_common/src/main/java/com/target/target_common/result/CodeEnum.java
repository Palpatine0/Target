package com.target.target_common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeEnum {
    SUCCESS(200, "OK"),
    SYSTEM_ERROR(500,"System error"),
    PARAMETER_ERROR(601,"Param error"),
    INSERT_PRODUCT_TYPE_ERROR(602,"Minimum sub level"),
    DELETE_PRODUCT_TYPE_ERROR(604,"Cant do.Has sub level")
    ;

    private final Integer code;
    private final String message;
}
