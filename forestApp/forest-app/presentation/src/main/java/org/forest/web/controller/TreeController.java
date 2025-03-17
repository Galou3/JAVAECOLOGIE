package org.forest.web.controller;

import org.forest.api.controller.TreeApi;
import org.forest.api.model.TreeInput;
import org.forest.domain.model.Tree;
import org.forest.domain.model.Species;
import org.forest.domain.model.Exposure;
import org.forest.port.driving.TreeUseCase;
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
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour les opérations sur les arbres
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class TreeController implements TreeApi {
    private final Logger logger = LoggerFactory.getLogger(TreeController.class);

    private TreeUseCase treeService;

    @Autowired
    public TreeController(TreeUseCase treeUseCase) {
        this.treeService = treeUseCase;
    }

    @Override
    public ResponseEntity<org.forest.api.model.Tree> createTree(TreeInput treeInput) {
        Tree domainTree = mapToDomain(treeInput);
        Tree savedTree = treeService.save(domainTree);
        return new ResponseEntity<>(map(savedTree), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteTree(String id) {
        return getOptionalUUID(id)
                .map(uuid -> {
                    try {
                        treeService.delete(uuid);
                        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                    } catch (Exception e) {
                        logger.error("Error deleting tree", e);
                        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<org.forest.api.model.Tree>> listTrees() {
        return ResponseEntity.ok(treeService.list().stream().map(this::map).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<org.forest.api.model.Tree> getTree(String id) {
        return getOptionalUUID(id)
                .flatMap(treeService::get)
                .map(this::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    private Optional<UUID> getOptionalUUID(String uuid) {
        try {
            return Optional.of(UUID.fromString(uuid));
        } catch (IllegalArgumentException e) {
            logger.error("Error while parsing UUID", e);
            return Optional.empty();
        }
    }

    private Tree mapToDomain(TreeInput treeInput) {
        return new Tree(null, // L'ID sera généré lors de la sauvegarde
                treeInput.getBirth(),
                Species.valueOf(treeInput.getSpecies().getValue()),
                Exposure.valueOf(treeInput.getExposure().getValue()),
                treeInput.getCarbonStorageCapacity()
        );
    }

    private org.forest.api.model.Tree map(Tree modelTree) {
        return new org.forest.api.model.Tree()
                .id(modelTree.id())
                .birth(modelTree.birth())
                .species(org.forest.api.model.Species.valueOf(modelTree.species().name()))
                .exposure(org.forest.api.model.Exposure.valueOf(modelTree.exposure().name()))
                .carbonStorageCapacity(modelTree.carbonStorageCapacity());
    }
}
