package com.parameter.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParameterDto {
      
	 private long parameterId;
	 
	 private String parameterName;
	 
	 private String parameterDescription;
	 
	 private String parameterValue;
	 
	 private String operator;
	 
	 private boolean activeInactive;
}
