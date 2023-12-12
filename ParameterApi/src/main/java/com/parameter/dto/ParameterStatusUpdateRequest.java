package com.parameter.dto;

import lombok.Data;

@Data
public class ParameterStatusUpdateRequest {
     private Long parameterId;
     private Boolean activeInactive;
}
