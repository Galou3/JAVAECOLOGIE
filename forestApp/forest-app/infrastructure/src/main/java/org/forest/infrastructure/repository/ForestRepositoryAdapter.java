package org.forest.infrastructure.repository;

import org.forest.domain.model.Forest;
import org.forest.port.driven.ForestRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * /!\ This repository is a simple in-memory one : DO NOT WRITE ANY BUSINESS LOGIC HERE /!\
 */
@Service
public class ForestRepositoryAdapter implements ForestRepositoryPort {
    private final List<Forest> mutableRepository = new ArrayList<>();

    @Override
    public List<Forest> findAll() {
        return mutableRepository;
    }

    @Override
    public Optional<Forest> findById(UUID id) {
        return mutableRepository.stream()
                .filter(forest -> id.equals(forest.id()))
                .findFirst();
    }

    @Override
    public Forest insert(Forest forest) {
        // Générer un nouvel UUID pour la forêt
        Forest persisted = new Forest(
                UUID.randomUUID(),
                forest.type(),
                forest.trees(),
                forest.surface()
        );
        mutableRepository.add(persisted);
        return persisted;
    }

    @Override
    public Forest update(Forest forest) {
        // Trouver l'indice de la forêt à mettre à jour
        int index = -1;
        for (int i = 0; i < mutableRepository.size(); i++) {
            if (mutableRepository.get(i).id().equals(forest.id())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            mutableRepository.set(index, forest);
            return forest;
        } else {
            throw new IllegalArgumentException("Forest not found with id " + forest.id());
        }
    }

    @Override
    public void delete(UUID id) {
        mutableRepository.removeIf(forest -> forest.id().equals(id));
    }
} 