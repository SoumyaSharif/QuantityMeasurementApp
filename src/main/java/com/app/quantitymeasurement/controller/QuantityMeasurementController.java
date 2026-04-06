package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurement", description = "APIs for comparing, converting, and calculating quantities")
@Data
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // ================= COMPARE =================

    @PostMapping("/compare")
    @Operation(summary = "Compare two quantities")
    public boolean compare(@RequestBody QuantityInputDTO input) {

        return service.compare(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO()
        );
    }

    // ================= CONVERT =================

    @PostMapping("/convert/{targetUnit}")
    @Operation(summary = "Convert a quantity to a target unit")
    public QuantityDTO convert(
            @RequestBody QuantityDTO input,
            @PathVariable String targetUnit) {

        return service.convert(input, targetUnit);
    }

    // ================= ADD =================

    @PostMapping("/add")
    @Operation(summary = "Add two quantities")
    public QuantityDTO add(@RequestBody QuantityInputDTO input) {

        return service.add(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO()
        );
    }

    // ================= SUBTRACT =================

    @PostMapping("/subtract")
    @Operation(summary = "Subtract one quantity from another")
    public QuantityDTO subtract(@RequestBody QuantityInputDTO input) {

        return service.subtract(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO()
        );
    }

    // ================= DIVIDE =================

    @PostMapping("/divide")
    @Operation(summary = "Divide one quantity by another")
    public double divide(@RequestBody QuantityInputDTO input) {

        return service.divide(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO()
        );
    }
}
