import java.util.*;

/**
 * CLASS - AddOnService
 * Represents an optional add-on service.
 */
class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * CLASS - AddOnServiceManager
 * Manages services linked to reservations.
 */
class AddOnServiceManager {

    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        List<AddOnService> services = servicesByReservation.get(reservationId);
        double total = 0.0;

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }
        return total;
    }
}

/**
 * MAIN CLASS
 */
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single-1";

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500.0));
        manager.addService(reservationId, new AddOnService("Spa", 1000.0));

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}
