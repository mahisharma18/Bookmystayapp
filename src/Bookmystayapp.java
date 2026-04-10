import java.util.Arrays;

public class UseCase16ManualSorting {

    // Bubble Sort Method
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        // Outer loop for passes
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Inner loop for comparison
            for (int j = 0; j < n - i - 1; j++) {

                // Compare adjacent elements
                if (arr[j] > arr[j + 1]) {

                    // Swap logic
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            // Optimization: stop if already sorted
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        // Test dataset (can modify for testing)
        int[] capacities = {72, 56, 24, 70, 60};

        System.out.println("Original Capacities: " + Arrays.toString(capacities));

        // Perform Bubble Sort
        bubbleSort(capacities);

        System.out.println("Sorted Capacities:   " + Arrays.toString(capacities));

        // Additional test cases
        int[] duplicateArray = {72, 56, 56, 24};
        bubbleSort(duplicateArray);
        System.out.println("Duplicate Sorted:    " + Arrays.toString(duplicateArray));

        int[] singleElement = {50};
        bubbleSort(singleElement);
        System.out.println("Single Element:      " + Arrays.toString(singleElement));

        int[] allEqual = {40, 40, 40};
        bubbleSort(allEqual);
        System.out.println("All Equal:           " + Arrays.toString(allEqual));
    }
}