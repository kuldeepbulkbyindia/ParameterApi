package com.parameter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.parameter.entities.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
	  
}
