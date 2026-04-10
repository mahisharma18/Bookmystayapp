import java.util.Arrays;

public class UseCase18LinearSearch {

    // Linear Search Method
    public static boolean linearSearch(String[] bogieIds, String key) {

        // Traverse array sequentially
        for (int i = 0; i < bogieIds.length; i++) {

            // Compare using equals()
            if (bogieIds[i].equals(key)) {
                return true; // Match found (early termination)
            }
        }

        return false; // No match found
    }

    public static void main(String[] args) {

        // Step 1: Create array of bogie IDs (unsorted)
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        System.out.println("Bogie List: " + Arrays.toString(bogieIds));

        // Step 2: Search key (can change for testing)
        String searchKey = "BG309";

        // Step 3: Perform search
        boolean found = linearSearch(bogieIds, searchKey);

        // Step 4: Display result
        if (found) {
            System.out.println("✅ Bogie ID " + searchKey + " FOUND.");
        } else {
            System.out.println("❌ Bogie ID " + searchKey + " NOT FOUND.");
        }

        // Additional test cases

        System.out.println("\n--- Additional Tests ---");

        // Not found case
        System.out.println("Search BG999: " +
                (linearSearch(bogieIds, "BG999") ? "Found" : "Not Found"));

        // First element match
        System.out.println("Search BG101: " +
                (linearSearch(bogieIds, "BG101") ? "Found" : "Not Found"));

        // Last element match
        System.out.println("Search BG550: " +
                (linearSearch(bogieIds, "BG550") ? "Found" : "Not Found"));

        // Single element array
        String[] single = {"BG101"};
        System.out.println("Single Array Search BG101: " +
                (linearSearch(single, "BG101") ? "Found" : "Not Found"));
    }
}