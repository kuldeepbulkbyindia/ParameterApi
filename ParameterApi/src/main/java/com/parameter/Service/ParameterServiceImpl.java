package com.parameter.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parameter.Exception.ResourceNotFoundException;
import com.parameter.dto.MultipleParameterStatusUpdateRequest;
import com.parameter.dto.ParameterDto;
import com.parameter.dto.ParameterStatusUpdateRequest;
import com.parameter.entities.Parameter;
import com.parameter.repository.ParameterRepository;


@Service
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ParameterRepository parameterRepo;

	
	
	@Override
	public void deleteParameter(long parameterId) {
		Parameter parameter = parameterRepo.findById(parameterId)
				.orElseThrow(() -> new ResourceNotFoundException("Parameter", "ParameterId", parameterId));
		parameterRepo.delete(parameter);
	}

	@Override
	public ParameterDto getParameterById(long parameterId) {
		Parameter parameter = parameterRepo.findById(parameterId)
				.orElseThrow(() -> new ResourceNotFoundException("Parameter", "ParameterId", parameterId));
		return modelMapper.map(parameter, ParameterDto.class);
	}

	@Override
	public List<ParameterDto> getParameters() {
		List<Parameter> parameters = parameterRepo.findAll();
		return parameters.stream()
				.map(param -> modelMapper.map(param, ParameterDto.class))
				.collect(Collectors.toList());
	}

	   @Override
	    public ParameterDto createParameter(ParameterDto parameterDto) {
	        Parameter parameter = modelMapper.map(parameterDto, Parameter.class);
	        parameter = parameterRepo.save(parameter);
	        return modelMapper.map(parameter, ParameterDto.class);
	    }

	    @Override
	    public ParameterDto updateParameter(ParameterDto parameterDto, long parameterId) {
	        Parameter existingParameter = parameterRepo.findById(parameterId)
	                .orElseThrow(() -> new ResourceNotFoundException("Parameter", "ParameterId", parameterId));

	        // Update the fields
	        existingParameter.setParameterName(parameterDto.getParameterName());
	        existingParameter.setParameterDescription(parameterDto.getParameterDescription());
	        existingParameter.setParameterValue(parameterDto.getParameterValue());
	        existingParameter.setOperator(parameterDto.getOperator());
	        existingParameter.setActiveInactive(parameterDto.isActiveInactive());

	        // Save the updated parameter
	        existingParameter = parameterRepo.save(existingParameter);

	        return modelMapper.map(existingParameter, ParameterDto.class);
	    }

		@Override
		public void updateParameterStatus(ParameterStatusUpdateRequest request) {
			Long parameterId  = request.getParameterId();
			Boolean activeInactive = request.getActiveInactive();
			  Parameter parameter = parameterRepo.findById(parameterId)
					       .orElseThrow(()-> new ResourceNotFoundException("Parameter", "parameterId",parameterId));
			 
			 parameter.setActiveInactive(activeInactive);
			 parameterRepo.save(parameter);
		}

		@Override
		public void updateAllParameterStatus(MultipleParameterStatusUpdateRequest request) {
			List<Long> parameterIds = request.getParameterIds();
			Boolean activeInactive = request.getActiveInactive();
			
			List<Parameter> parameters = new ArrayList<>();
			
			for(Long parameterId:parameterIds) {
				Parameter parameter = parameterRepo.findById(parameterId)
					       .orElseThrow(()-> new ResourceNotFoundException("Parameter", "parameter",parameterId));
				parameters.add(parameter);
			}
			
			for(Parameter parameter:parameters) {
				parameter.setActiveInactive(activeInactive);
			}
			parameterRepo.saveAll(parameters);
		}
			
		}			
		
	



