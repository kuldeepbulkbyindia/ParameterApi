package com.parameter.entities;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Parameters")


public class Parameter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long parameterId;
    
    @Column(name= "parameter_name",nullable = false)
    private String parameterName;
    
    @Column(name = "parameter_description" , nullable = false)
    private String parameterDescription;
    
    @Column(name = "parameter_value" , nullable = false)
    private String parameterValue;
    
    @Column(name = "operator",nullable = true)    
    private String operator;
    
    @Column(name = "activeInactive")
    private boolean activeInactive;

    
    
}
