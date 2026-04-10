import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

// Passenger Bogie Class
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name + " - Capacity: " + capacity;
    }
}

// Goods Bogie Class
class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    public String getType() {
        return type;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return type + " Bogie carrying " + cargo;
    }
}

// Main Class
public class RailwayManagementSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ---------------- UC11: Regex Validation ----------------
        System.out.print("Enter Train ID (Format TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (Format PET-AB): ");
        String cargoCode = scanner.nextLine();

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        boolean isTrainValid = trainPattern.matcher(trainId).matches();
        boolean isCargoValid = cargoPattern.matcher(cargoCode).matches();

        if (!isTrainValid || !isCargoValid) {
            System.out.println("❌ Invalid input! Program terminated.");
            return;
        }

        System.out.println("✅ Inputs are valid!");

        // ---------------- UC7: Sorting Passenger Bogies ----------------
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 40));

        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("\nSorted Passenger Bogies:");
        bogies.forEach(System.out::println);

        // ---------------- UC10: Aggregation ----------------
        int totalCapacity = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seating Capacity: " + totalCapacity);

        // ---------------- UC12: Safety Validation ----------------
        List<GoodsBogie> goodsList = new ArrayList<>();

        goodsList.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsList.add(new GoodsBogie("Open", "Coal"));
        goodsList.add(new GoodsBogie("Box", "Grain"));
        // Try invalid case:
        // goodsList.add(new GoodsBogie("Cylindrical", "Coal"));

        boolean isSafe = goodsList.stream()
                .allMatch(b ->
                        !b.getType().equalsIgnoreCase("Cylindrical")
                                || b.getCargo().equalsIgnoreCase("Petroleum")
                );

        System.out.println("\nGoods Bogies:");
        goodsList.forEach(System.out::println);

        if (isSafe) {
            System.out.println("\n✅ Train is SAFETY COMPLIANT");
        } else {
            System.out.println("\n❌ Train is NOT SAFE (Invalid cargo assignment)");
        }

        scanner.close();
    }
}