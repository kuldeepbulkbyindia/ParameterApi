package com.parameter.Service;
import java.util.List;
import com.parameter.dto.MultipleParameterStatusUpdateRequest;
import com.parameter.dto.ParameterDto;
import com.parameter.dto.ParameterStatusUpdateRequest;

public interface ParameterService {
	
	ParameterDto createParameter(ParameterDto parameterDto);
	
	ParameterDto updateParameter(ParameterDto parameterDto,long parameterId);
	
	public void deleteParameter(long parameterId);
	
	ParameterDto getParameterById(long parameterId);
	
	List<ParameterDto> getParameters();
	
	 void updateParameterStatus(ParameterStatusUpdateRequest request);
	 
	 void updateAllParameterStatus(MultipleParameterStatusUpdateRequest request);
	
}

