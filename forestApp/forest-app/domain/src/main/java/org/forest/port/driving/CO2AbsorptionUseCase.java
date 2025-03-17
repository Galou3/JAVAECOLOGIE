package org.forest.port.driving;

import org.forest.domain.model.Forest;

/**
 * Interface pour les calculs liés à l'absorption de CO2
 */
public interface CO2AbsorptionUseCase {

    /**
     * Calcule la capacité d'absorption de CO2 d'une forêt en kg/an
     * @param forest La forêt à analyser
     * @return La quantité de CO2 absorbée en kg/an
     */
    double getAbsorption(Forest forest);
    
    /**
     * Calcule la surface de forêt nécessaire pour absorber une quantité donnée de CO2
     * @param co2Amount La quantité de CO2 à absorber en kg/an
     * @return La surface nécessaire en m²
     */
    double getRequiredSurface(double co2Amount);
}
