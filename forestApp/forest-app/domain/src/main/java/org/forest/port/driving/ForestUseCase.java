package org.forest.port.driving;

import org.forest.domain.model.Forest;
import org.forest.domain.model.Species;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ForestUseCase {
    /**
     * Récupère une forêt par son ID
     */
    Optional<Forest> get(UUID uuid);

    /**
     * Liste toutes les forêts
     */
    List<Forest> list();

    /**
     * Enregistre une nouvelle forêt
     */
    Forest save(Forest forest);
    
    /**
     * Met à jour une forêt existante
     */
    Forest update(Forest forest);
    
    /**
     * Supprime une forêt
     */
    void delete(UUID uuid);
    
    /**
     * Récupère toutes les espèces d'arbres présentes dans une forêt
     */
    Set<Species> getSpecies(UUID forestId);
} 