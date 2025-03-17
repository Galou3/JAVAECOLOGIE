package org.forest.port.driven;

import org.forest.domain.model.Tree;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TreeRepositoryPort {
    List<Tree> findAll();

    Optional<Tree> findById(UUID id);

    Tree insert(Tree tree);
    
    Tree update(Tree tree);
    
    void delete(UUID id);
}
