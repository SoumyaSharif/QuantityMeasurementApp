package com.app.quantitymeasurement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="quantity_measurements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityMeasurementEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   
   

    @Column(nullable = false)
    private String operation;

    @Column(nullable = false)
    private String operand1;

    @Column(nullable = false)
    private String operand2;

    @Column(nullable = false)
    private String result;

    @Column
    private String errorMessage;


}