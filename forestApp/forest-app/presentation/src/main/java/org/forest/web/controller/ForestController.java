package org.forest.web.controller;

import org.forest.api.controller.ForestApi;
import org.forest.api.model.Forest;
import org.forest.api.model.ForestInput;
import org.forest.api.model.Species;
import org.forest.port.driving.ForestUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Collections;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class ForestController implements ForestApi {
    private final Logger logger = LoggerFactory.getLogger(ForestController.class);

    private final ForestUseCase forestService;

    @Autowired
    public ForestController(ForestUseCase forestService) {
        this.forestService = forestService;
    }

    @Override
    public ResponseEntity<Forest> createForest(ForestInput forestInput) {
        org.forest.domain.model.Forest domainForest = mapToDomain(forestInput);
        org.forest.domain.model.Forest savedForest = forestService.save(domainForest);
        return new ResponseEntity<>(mapToApi(savedForest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteForest(String id) {
        return getOptionalUUID(id)
                .map(uuid -> {
                    forestService.delete(uuid);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Forest> getForest(String id) {
        return getOptionalUUID(id)
                .flatMap(forestService::get)
                .map(this::mapToApi)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Forest>> listForests() {
        List<Forest> forests = forestService.list().stream()
                .map(this::mapToApi)
                .collect(Collectors.toList());
        return ResponseEntity.ok(forests);
    }

    @Override
    public ResponseEntity<org.forest.api.model.Forest> updateForest(String id, ForestInput forestInput) {
        // Convertir l'ID string en UUID
        Optional<UUID> optionalUUID = getOptionalUUID(id);
        
        if (optionalUUID.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        UUID uuid = optionalUUID.get();
        
        try {
            // Vérifier que la forêt existe
            Optional<org.forest.domain.model.Forest> existingForest = forestService.get(uuid);
            if (existingForest.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            // Convertir le ForestInput en Forest du domaine
            org.forest.domain.model.Forest domainForest = mapToDomain(forestInput);
            
            // Créer une nouvelle forêt avec l'UUID existant
            org.forest.domain.model.Forest forestToUpdate = new org.forest.domain.model.Forest(
                uuid,
                domainForest.type(),
                domainForest.trees(),
                domainForest.surface()
            );
            
            // Mettre à jour la forêt
            org.forest.domain.model.Forest updatedForest = forestService.update(forestToUpdate);
            
            // Convertir la forêt mise à jour en DTO et retourner la réponse
            return ResponseEntity.ok(mapToApi(updatedForest));
        } catch (IllegalArgumentException e) {
            logger.error("Error updating forest", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Unexpected error updating forest", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private Optional<UUID> getOptionalUUID(String uuid) {
        try {
            return Optional.of(UUID.fromString(uuid));
        } catch (IllegalArgumentException e) {
            logger.error("Error while parsing UUID", e);
            return Optional.empty();
        }
    }

    private org.forest.domain.model.Forest mapToDomain(ForestInput forestInput) {
        // Convertir la liste d'arbres en s'assurant que chaque arbre a un ID
        List<org.forest.domain.model.Tree> treesWithIds = mapTrees(forestInput.getTrees());
        
        return new org.forest.domain.model.Forest(
                null, // L'ID sera généré par le repository
                mapForestType(forestInput.getType().getValue()),
                treesWithIds,
                forestInput.getSurface()
        );
    }

    private List<org.forest.domain.model.Tree> mapTrees(List<org.forest.api.model.Tree> trees) {
        return trees.stream()
                .map(this::mapTree)
                .collect(Collectors.toList());
    }

    private org.forest.domain.model.Tree mapTree(org.forest.api.model.Tree apiTree) {
        // Générer un nouvel UUID si l'arbre n'en a pas
        UUID treeId = (apiTree.getId() != null) ? apiTree.getId() : UUID.randomUUID();
        
        return new org.forest.domain.model.Tree(
                treeId, // Utilisez l'ID existant ou générez-en un nouveau
                apiTree.getBirth(),
                org.forest.domain.model.Species.valueOf(apiTree.getSpecies().getValue()),
                org.forest.domain.model.Exposure.valueOf(apiTree.getExposure().getValue()),
                apiTree.getCarbonStorageCapacity()
        );
    }

    private org.forest.domain.model.ForestType mapForestType(String type) {
        return org.forest.domain.model.ForestType.valueOf(type);
    }

    private Forest mapToApi(org.forest.domain.model.Forest domainForest) {
        Forest apiForest = new Forest();
        apiForest.setId(domainForest.id());
        apiForest.setType(org.forest.api.model.ForestType.valueOf(domainForest.type().name()));
        apiForest.setSurface(domainForest.surface());
        
        List<org.forest.api.model.Tree> apiTrees = domainForest.trees().stream()
                .map(this::mapToApiTree)
                .collect(Collectors.toList());
        apiForest.setTrees(apiTrees);
        
        return apiForest;
    }

    private org.forest.api.model.Tree mapToApiTree(org.forest.domain.model.Tree domainTree) {
        org.forest.api.model.Tree apiTree = new org.forest.api.model.Tree();
        apiTree.setId(domainTree.id());
        apiTree.setBirth(domainTree.birth());
        apiTree.setSpecies(org.forest.api.model.Species.valueOf(domainTree.species().name()));
        apiTree.setExposure(org.forest.api.model.Exposure.valueOf(domainTree.exposure().name()));
        apiTree.setCarbonStorageCapacity(domainTree.carbonStorageCapacity());
        return apiTree;
    }
} 