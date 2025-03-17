package org.forest.port.driving;

import org.forest.domain.model.Tree;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TreeUseCase {
    /**
     * Récupère un arbre par son ID
     */
    Optional<Tree> get(UUID uuid);

    /**
     * Liste tous les arbres
     */
    List<Tree> list();

    /**
     * Enregistre un nouvel arbre
     */
    Tree save(Tree tree);
    
    /**
     * Met à jour un arbre existant
     */
    Tree update(Tree tree);
    
    /**
     * Supprime un arbre
     */
    void delete(UUID uuid);
}
