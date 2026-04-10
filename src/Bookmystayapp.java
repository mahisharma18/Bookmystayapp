import java.util.LinkedHashSet;
import java.util.Set;

public class TrainConsistApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Initialize LinkedHashSet for ordered & unique bogies
        Set<String> trainFormation = new LinkedHashSet<>();

        // Add bogies
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        // محاولة إضافة مكرر (duplicate)
        trainFormation.add("Sleeper"); // duplicate, will be ignored

        // Display final formation
        System.out.println("\nFinal Train Formation (Ordered & Unique):");
        System.out.println(trainFormation);

        // Program continues...
    }
}
