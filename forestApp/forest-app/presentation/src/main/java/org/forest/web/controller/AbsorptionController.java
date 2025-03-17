package org.forest.web.controller;

import org.forest.api.controller.AbsorptionApi;
import org.forest.api.model.AbsorptionResult;
import org.forest.api.model.SurfaceResult;
import org.forest.port.driving.CO2AbsorptionUseCase;
import org.forest.port.driving.ForestUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class AbsorptionController implements AbsorptionApi {
    private final Logger logger = LoggerFactory.getLogger(AbsorptionController.class);

    private final CO2AbsorptionUseCase co2AbsorptionService;
    private final ForestUseCase forestService;

    @Autowired
    public AbsorptionController(CO2AbsorptionUseCase co2AbsorptionService, ForestUseCase forestService) {
        this.co2AbsorptionService = co2AbsorptionService;
        this.forestService = forestService;
    }

    @Override
    public ResponseEntity<SurfaceResult> getRequiredSurface(Double co2Amount) {
        double requiredSurface = co2AbsorptionService.getRequiredSurface(co2Amount);
        SurfaceResult result = new SurfaceResult();
        result.setCo2Amount(co2Amount);
        result.setRequiredSurfaceSqMeters(requiredSurface);
        return ResponseEntity.ok(result);
    }

    private Optional<UUID> getOptionalUUID(String uuid) {
        try {
            return Optional.of(UUID.fromString(uuid));
        } catch (IllegalArgumentException e) {
            logger.error("Error while parsing UUID", e);
            return Optional.empty();
        }
    }
} 