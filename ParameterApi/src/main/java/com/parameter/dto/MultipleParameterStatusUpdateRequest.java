package com.parameter.dto;

import java.util.List;

import lombok.Data;

@Data
public class MultipleParameterStatusUpdateRequest {
	 private List<Long> parameterIds;
	 private Boolean activeInactive;
}
