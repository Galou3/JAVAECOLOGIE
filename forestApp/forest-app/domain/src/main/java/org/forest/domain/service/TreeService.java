package org.forest.domain.service;

import org.forest.domain.model.Tree;
import org.forest.port.driven.TreeRepositoryPort;
import org.forest.port.driving.TreeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TreeService implements TreeUseCase {
    private TreeRepositoryPort treeRepository;

    @Autowired
    public TreeService(TreeRepositoryPort treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public Optional<Tree> get(UUID uuid) {
        return treeRepository.findById(uuid);
    }

    @Override
    public List<Tree> list() {
        return treeRepository.findAll();
    }

    @Override
    public Tree save(Tree tree) {
        validateTree(tree);
        return treeRepository.insert(tree);
    }
    
    @Override
    public Tree update(Tree tree) {
        validateTree(tree);
        
        // Vérifier que l'arbre existe
        if (tree.id() == null || treeRepository.findById(tree.id()).isEmpty()) {
            throw new IllegalArgumentException("L'arbre à mettre à jour n'existe pas");
        }
        
        return treeRepository.update(tree);
    }
    
    @Override
    public void delete(UUID uuid) {
        treeRepository.delete(uuid);
    }
    
    private void validateTree(Tree tree) {
        if (tree.birth() == null) {
            throw new IllegalArgumentException("Birth is required");
        }
        
        if (tree.species() == null) {
            throw new IllegalArgumentException("Species is required");
        }
        
        if (tree.exposure() == null) {
            throw new IllegalArgumentException("Exposure is required");
        }
        
        if (tree.carbonStorageCapacity() <= 0) {
            throw new IllegalArgumentException("Carbon storage capacity must be positive");
        }
    }
}
