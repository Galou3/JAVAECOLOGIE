package org.forest.infrastructure.repository;

import org.forest.domain.model.Tree;
import org.forest.port.driven.TreeRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * /!\ This repository is a simple in-memory one : DO NOT WRITE ANY BUSINESS LOGIC HERE /!\
 *
 */
@Service
public class TreeRepositoryAdapter implements TreeRepositoryPort {
    private final List<Tree> mutableRepository = new ArrayList<>();

    @Override
    public List<Tree> findAll() {
        return mutableRepository;
    }
    
    @Override
    public Optional<Tree> findById(UUID id) {
        return mutableRepository.stream()
                .filter(tree -> id.equals(tree.id()))
                .findFirst();
    }

    @Override
    public Tree insert(Tree tree) {
        final Tree persisted = new Tree(UUID.randomUUID(), tree.birth(), tree.species(), tree.exposure(), tree.carbonStorageCapacity());
        mutableRepository.add(persisted);

        return persisted;
    }
    
    @Override
    public Tree update(Tree tree) {
        // Trouver l'indice de l'arbre à mettre à jour
        int index = -1;
        for (int i = 0; i < mutableRepository.size(); i++) {
            if (mutableRepository.get(i).id().equals(tree.id())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            mutableRepository.set(index, tree);
            return tree;
        } else {
            throw new IllegalArgumentException("Tree not found with id " + tree.id());
        }
    }
    
    @Override
    public void delete(UUID id) {
        mutableRepository.removeIf(tree -> tree.id().equals(id));
    }
}
