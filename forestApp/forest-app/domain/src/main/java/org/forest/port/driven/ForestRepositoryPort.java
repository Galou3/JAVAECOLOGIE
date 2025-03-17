package org.forest.port.driven;

import org.forest.domain.model.Forest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ForestRepositoryPort {
    List<Forest> findAll();
    
    Optional<Forest> findById(UUID id);
    
    Forest insert(Forest forest);
    
    Forest update(Forest forest);
    
    void delete(UUID id);
} 