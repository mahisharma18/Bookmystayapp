import java.util.Arrays;

public class UseCase19BinarySearch {

    // Binary Search Method
    public static boolean binarySearch(String[] bogieIds, String key) {

        int low = 0;
        int high = bogieIds.length - 1;

        while (low <= high) {

            // Compute mid index
            int mid = (low + high) / 2;

            // Compare using compareTo()
            int comparison = key.compareTo(bogieIds[mid]);

            if (comparison == 0) {
                return true; // Found
            } else if (comparison < 0) {
                high = mid - 1; // Search left half
            } else {
                low = mid + 1; // Search right half
            }
        }

        return false; // Not found
    }

    public static void main(String[] args) {

        // Step 1: Create sorted bogie IDs
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        // Ensure sorting (important precondition)
        Arrays.sort(bogieIds);

        System.out.println("Sorted Bogie IDs: " + Arrays.toString(bogieIds));

        // Step 2: Search key
        String searchKey = "BG309";

        // Step 3: Perform binary search
        boolean found = binarySearch(bogieIds, searchKey);

        // Step 4: Display result
        if (found) {
            System.out.println("✅ Bogie ID " + searchKey + " FOUND.");
        } else {
            System.out.println("❌ Bogie ID " + searchKey + " NOT FOUND.");
        }

        // Additional test cases

        System.out.println("\n--- Additional Tests ---");

        // Not found
        System.out.println("Search BG999: " +
                (binarySearch(bogieIds, "BG999") ? "Found" : "Not Found"));

        // First element
        System.out.println("Search BG101: " +
                (binarySearch(bogieIds, "BG101") ? "Found" : "Not Found"));

        // Last element
        System.out.println("Search BG550: " +
                (binarySearch(bogieIds, "BG550") ? "Found" : "Not Found"));

        // Single element
        String[] single = {"BG101"};
        System.out.println("Single Array Search BG101: " +
                (binarySearch(single, "BG101") ? "Found" : "Not Found"));
    }
}