import java.util.HashSet;
import java.util.Set;

public class TrainConsistApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Initialize HashSet for unique bogie IDs
        Set<String> bogieSet = new HashSet<>();

        // Add bogie IDs (including duplicates)
        bogieSet.add("BG101");
        bogieSet.add("BG102");
        bogieSet.add("BG103");
        bogieSet.add("BG101"); // duplicate
        bogieSet.add("BG102"); // duplicate

        // Display final set
        System.out.println("\nUnique Bogie IDs in Train:");
        System.out.println(bogieSet);

        // Program continues...
    }
}
