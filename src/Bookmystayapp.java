import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

// Bogie Class
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name + " - Capacity: " + capacity;
    }
}

// Main Application
public class RailwayManagementSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ---------------- UC11: Regex Validation ----------------
        System.out.print("Enter Train ID (Format TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (Format PET-AB): ");
        String cargoCode = scanner.nextLine();

        // Regex Patterns
        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        boolean isTrainValid = trainMatcher.matches();
        boolean isCargoValid = cargoMatcher.matches();

        if (!isTrainValid) {
            System.out.println("❌ Invalid Train ID format!");
        } else {
            System.out.println("✅ Valid Train ID");
        }

        if (!isCargoValid) {
            System.out.println("❌ Invalid Cargo Code format!");
        } else {
            System.out.println("✅ Valid Cargo Code");
        }

        // Continue only if valid
        if (!isTrainValid || !isCargoValid) {
            System.out.println("Program terminated due to invalid input.");
            return;
        }

        // ---------------- UC7: Sorting using Comparator ----------------
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 40));

        // Sort by capacity
        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("\nSorted Bogies (by Capacity):");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // ---------------- UC10: Aggregation using reduce() ----------------
        int totalCapacity = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seating Capacity: " + totalCapacity);

        scanner.close();
    }
}