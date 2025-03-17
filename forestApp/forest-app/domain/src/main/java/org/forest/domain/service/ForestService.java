package org.forest.domain.service;

import org.forest.domain.model.Forest;
import org.forest.domain.model.Species;
import org.forest.port.driven.ForestRepositoryPort;
import org.forest.port.driving.ForestUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ForestService implements ForestUseCase {
    private final ForestRepositoryPort forestRepository;

    @Autowired
    public ForestService(ForestRepositoryPort forestRepository) {
        this.forestRepository = forestRepository;
    }

    @Override
    public Optional<Forest> get(UUID uuid) {
        return forestRepository.findById(uuid);
    }

    @Override
    public List<Forest> list() {
        return forestRepository.findAll();
    }

    @Override
    public Forest save(Forest forest) {
        validateForest(forest);
        return forestRepository.insert(forest);
    }
    
    @Override
    public Forest update(Forest forest) {
        validateForest(forest);
        // Vérifier que la forêt existe
        if (forest.id() == null || forestRepository.findById(forest.id()).isEmpty()) {
            throw new IllegalArgumentException("La forêt à mettre à jour n'existe pas");
        }
        return forestRepository.update(forest);
    }
    
    @Override
    public void delete(UUID uuid) {
        forestRepository.delete(uuid);
    }
    
    @Override
    public Set<Species> getSpecies(UUID forestId) {
        return forestRepository.findById(forestId)
                .map(forest -> forest.trees().stream()
                        .map(tree -> tree.species())
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }
    
    private void validateForest(Forest forest) {
        if (forest.surface() <= 0) {
            throw new IllegalArgumentException("La surface de la forêt doit être positive");
        }
        
        if (forest.type() == null) {
            throw new IllegalArgumentException("Le type de forêt est requis");
        }
        
        if (forest.trees() == null) {
            throw new IllegalArgumentException("La liste d'arbres ne peut pas être null");
        }
    }
} 