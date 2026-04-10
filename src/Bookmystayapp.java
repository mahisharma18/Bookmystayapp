import java.util.Arrays;

public class UseCase17BuiltInSorting {

    public static void main(String[] args) {

        // Step 1: Create array of bogie type names
        String[] bogieTypes = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};

        System.out.println("Original Bogie Types: " + Arrays.toString(bogieTypes));

        // Step 2: Sort using built-in method
        Arrays.sort(bogieTypes);

        // Step 3: Display sorted result
        System.out.println("Sorted Bogie Types:   " + Arrays.toString(bogieTypes));

        // Additional Test Cases

        // Unsorted input
        String[] unsorted = {"Luxury", "General", "Sleeper", "AC Chair"};
        Arrays.sort(unsorted);
        System.out.println("Unsorted → Sorted:    " + Arrays.toString(unsorted));

        // Already sorted
        String[] sorted = {"AC Chair", "First Class", "General"};
        Arrays.sort(sorted);
        System.out.println("Already Sorted:       " + Arrays.toString(sorted));

        // Duplicate values
        String[] duplicates = {"Sleeper", "AC Chair", "Sleeper", "General"};
        Arrays.sort(duplicates);
        System.out.println("Duplicates Sorted:    " + Arrays.toString(duplicates));

        // Single element
        String[] single = {"Sleeper"};
        Arrays.sort(single);
        System.out.println("Single Element:       " + Arrays.toString(single));
    }
}