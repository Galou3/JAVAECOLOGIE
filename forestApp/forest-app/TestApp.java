import org.forest.domain.model.*;
import org.forest.domain.service.CO2AbsorptionService;
import org.forest.domain.service.ForestService;
import org.forest.domain.service.TreeService;
import org.forest.infrastructure.repository.ForestRepositoryAdapter;
import org.forest.infrastructure.repository.TreeRepositoryAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Application de test pour vérifier le fonctionnement des services
 */
public class TestApp {
    public static void main(String[] args) {
        System.out.println("Démarrage du test de l'application Forest");
        
        // Initialiser les repositories
        TreeRepositoryAdapter treeRepository = new TreeRepositoryAdapter();
        ForestRepositoryAdapter forestRepository = new ForestRepositoryAdapter();
        
        // Initialiser les services
        TreeService treeService = new TreeService(treeRepository);
        ForestService forestService = new ForestService(forestRepository);
        CO2AbsorptionService co2Service = new CO2AbsorptionService();
        
        // Créer quelques arbres
        Tree oak = new Tree(
                null,
                LocalDate.of(2010, 5, 15),
                Species.OAK,
                Exposure.SUNNY,
                25.5
        );
        
        Tree fir = new Tree(
                null,
                LocalDate.of(2015, 3, 10),
                Species.FIR,
                Exposure.MID_SHADOW,
                20.0
        );
        
        Tree beech = new Tree(
                null,
                LocalDate.of(2012, 8, 22),
                Species.BEECH,
                Exposure.SHADOW,
                18.5
        );
        
        // Sauvegarder les arbres
        Tree savedOak = treeService.save(oak);
        Tree savedFir = treeService.save(fir);
        Tree savedBeech = treeService.save(beech);
        
        System.out.println("Arbres créés:");
        System.out.println("- " + savedOak);
        System.out.println("- " + savedFir);
        System.out.println("- " + savedBeech);
        
        // Créer une forêt
        List<Tree> trees = new ArrayList<>();
        trees.add(savedOak);
        trees.add(savedFir);
        trees.add(savedBeech);
        
        Forest forest = new Forest(
                null,
                ForestType.TEMPERATE,
                trees,
                10000.0 // 1 hectare en m²
        );
        
        // Sauvegarder la forêt
        Forest savedForest = forestService.save(forest);
        System.out.println("\nForêt créée: " + savedForest);
        
        // Calculer l'absorption de CO2
        double absorption = co2Service.getAbsorption(savedForest);
        System.out.println("\nCapacité d'absorption de CO2 de la forêt: " + absorption + " kg/an");
        
        // Calculer la surface nécessaire pour absorber 1000 kg de CO2 par an
        double co2Amount = 1000.0;
        double requiredSurface = co2Service.getRequiredSurface(co2Amount);
        System.out.println("\nSurface nécessaire pour absorber " + co2Amount + " kg de CO2 par an: " + requiredSurface + " m²");
        
        // Obtenir les espèces d'arbres dans la forêt
        System.out.println("\nEspèces présentes dans la forêt:");
        forestService.getSpecies(savedForest.id()).forEach(species -> System.out.println("- " + species));
        
        System.out.println("\nTest terminé avec succès!");
    }
} 