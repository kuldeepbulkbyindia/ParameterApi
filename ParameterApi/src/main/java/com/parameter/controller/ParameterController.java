package com.parameter.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.parameter.Service.ParameterService;
import com.parameter.dto.MultipleParameterStatusUpdateRequest;
import com.parameter.dto.ParameterDto;
import com.parameter.dto.ParameterStatusUpdateRequest;

@RestController
@RequestMapping("/parameters")
public class ParameterController {
	
	@Autowired
	private ParameterService parameterService;
	
	@PostMapping("/addParameter")
	public ResponseEntity<ParameterDto> createParameter(@RequestBody ParameterDto parameterDto) {
		ParameterDto createdParameter = parameterService.createParameter(parameterDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdParameter);
	}
	
	@PutMapping("/updateParameter/{parameterId}")
	public ResponseEntity<ParameterDto> updateParameter(@RequestBody ParameterDto parameterDto, @PathVariable("parameterId") long parameterId) {
		ParameterDto updatedParameter = parameterService.updateParameter(parameterDto, parameterId);
		return ResponseEntity.ok(updatedParameter);
	}
	
	@DeleteMapping("/deleteParameter/{parameterId}")
	public ResponseEntity<String> deleteParameter(@PathVariable("parameterId") long parameterId) {
		parameterService.deleteParameter(parameterId);
		return ResponseEntity.ok("Parameter deleted successfully");
	}
	
	@GetMapping("/{parameterId}")
	public ResponseEntity<ParameterDto> getParameterById(@PathVariable("parameterId") long parameterId) {
		ParameterDto parameter = parameterService.getParameterById(parameterId);
		return ResponseEntity.ok(parameter);
	}
	
	@GetMapping
	public ResponseEntity<List<ParameterDto>> getAllParameters() {
		List<ParameterDto> parameters = parameterService.getParameters();
		return ResponseEntity.ok(parameters);
	}
	
	@PutMapping("/changeStatus")
    public ResponseEntity<Map<String, String>> updateParameterStatus(@RequestBody ParameterStatusUpdateRequest request) {
		parameterService.updateParameterStatus(request);

        boolean isActive = request.getActiveInactive() != null && request.getActiveInactive();
        String message = isActive ? "Parameter unblocked successfully." : "Parameter blocked successfully.";
        Map<String, String> response = new HashMap<>();
        response.put("message", message);

        return ResponseEntity.ok(response);
    }
	 @PutMapping("/changeMultipleStatus")
	    public ResponseEntity<Map<String, String>> updateAllParameterStatus(@RequestBody MultipleParameterStatusUpdateRequest request) {
	        if (request.getParameterIds() == null || request.getParameterIds().isEmpty()) {
	            // Return a response indicating that at least one roleId is required
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("error", "At least one parameterId is required.");
	            return ResponseEntity.badRequest().body(errorResponse);
	        }

	        parameterService.updateAllParameterStatus(request);

	        boolean isActive = request.getActiveInactive() != null && request.getActiveInactive();
	        String message = isActive ? "Parameters unblocked successfully!" : "Parameters blocked successfully!";
	        Map<String, String> response = new HashMap<>();
	        response.put("message", message);
	        return ResponseEntity.ok(response);
	    }
}

	   

