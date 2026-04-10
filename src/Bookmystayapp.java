import java.util.*;

public class UseCase20DefensiveSearch {

    // Search method with defensive check
    public static boolean searchBogie(List<String> bogieIds, String key) {

        // Step 1: State validation (Fail-Fast)
        if (bogieIds == null || bogieIds.isEmpty()) {
            throw new IllegalStateException("Cannot perform search: No bogies available in the train.");
        }

        // Step 2: Perform search (Linear Search)
        for (String id : bogieIds) {
            if (id.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        // Case 1: Valid dataset
        List<String> bogies = Arrays.asList("BG101", "BG205", "BG309");

        try {
            boolean found = searchBogie(bogies, "BG205");
            System.out.println("Search Result: " + (found ? "FOUND" : "NOT FOUND"));
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println();

        // Case 2: Empty dataset
        List<String> emptyBogies = new ArrayList<>();

        try {
            boolean found = searchBogie(emptyBogies, "BG101");
            System.out.println("Search Result: " + (found ? "FOUND" : "NOT FOUND"));
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println();

        // Program continues after exception
        System.out.println("✅ Program continues safely after handling invalid state.");
    }
}