package org.forest.domain.service;

import org.forest.domain.model.Forest;
import org.forest.domain.model.Species;
import org.forest.domain.model.Tree;
import org.forest.port.driving.CO2AbsorptionUseCase;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CO2AbsorptionService implements CO2AbsorptionUseCase {
    // Facteur de diversité: plus il y a d'espèces différentes, plus l'absorption est efficace
    private static final double DIVERSITY_FACTOR_BASE = 1.0;
    private static final double DIVERSITY_FACTOR_INCREMENT = 0.2;
    private static final double MAX_DIVERSITY_FACTOR = 2.0;

    @Override
    public double getAbsorption(Forest forest) {
        if (forest == null || forest.trees() == null || forest.trees().isEmpty()) {
            return 0;
        }

        // Calculer l'absorption totale de base des arbres
        double baseAbsorption = forest.trees().stream()
                .mapToDouble(Tree::carbonStorageCapacity)
                .sum();
        
        // Appliquer le facteur de diversité
        double diversityFactor = calculateDiversityFactor(forest);
        
        // Le type de forêt influence également l'absorption
        double forestTypeFactor = getForestTypeFactor(forest);
        
        return baseAbsorption * diversityFactor * forestTypeFactor;
    }
    
    @Override
    public double getRequiredSurface(double co2Amount) {
        // Absorption moyenne par m² (en kg/an)
        // Cette valeur est arbitraire et pourrait être basée sur des études réelles
        double averageAbsorptionPerSquareMeter = 0.5;
        
        return co2Amount / averageAbsorptionPerSquareMeter;
    }
    
    private double calculateDiversityFactor(Forest forest) {
        Set<Species> uniqueSpecies = new HashSet<>();
        forest.trees().forEach(tree -> uniqueSpecies.add(tree.species()));
        
        int speciesCount = uniqueSpecies.size();
        double diversityFactor = DIVERSITY_FACTOR_BASE + (speciesCount - 1) * DIVERSITY_FACTOR_INCREMENT;
        
        // Limiter le facteur de diversité à une valeur maximale
        return Math.min(diversityFactor, MAX_DIVERSITY_FACTOR);
    }
    
    private double getForestTypeFactor(Forest forest) {
        // Différents types de forêts ont différentes capacités d'absorption
        return switch (forest.type()) {
            case TROPICAL -> 1.5;  // Les forêts tropicales sont les plus efficaces
            case TEMPERATE -> 1.2;
            case BOREAL -> 1.0;
        };
    }
}
