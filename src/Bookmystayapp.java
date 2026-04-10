import java.util.*;

// -------------------- RoomInventory --------------------
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 5);
        inventory.put("Suite", 2);
    }

    public void increaseRoom(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void decreaseRoom(String roomType) {
        if (inventory.getOrDefault(roomType, 0) > 0) {
            inventory.put(roomType, inventory.get(roomType) - 1);
        } else {
            System.out.println("No rooms available for type: " + roomType);
        }
    }

    public void showInventory() {
        System.out.println("Current Inventory: " + inventory);
    }
}

// -------------------- CancellationService --------------------
class CancellationService {

    // Stack for rollback tracking
    private Stack<String> releasedRoomIds;

    // Map reservationId -> roomType
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    // Register booking
    public void registerBooking(String reservationId, String roomType) {
        if (reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Booking already exists: " + reservationId);
            return;
        }

        reservationRoomTypeMap.put(reservationId, roomType);
        System.out.println("Booking registered: " + reservationId + " -> " + roomType);
    }

    // Cancel booking and restore inventory
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation: Reservation not found -> " + reservationId);
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Restore inventory
        inventory.increaseRoom(roomType);

        // Track rollback
        releasedRoomIds.push(reservationId);

        // Remove booking
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Cancelled booking: " + reservationId + ", restored: " + roomType);
    }

    // Show rollback history (LIFO)
    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most recent first):");

        if (releasedRoomIds.isEmpty()) {
            System.out.println("No cancellations yet.");
            return;
        }

        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println(releasedRoomIds.get(i));
        }
    }
}

// -------------------- Main Class --------------------
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Initial inventory
        System.out.println("Initial מצב:");
        inventory.showInventory();

        // Register bookings
        service.registerBooking("R1", "Single");
        service.registerBooking("R2", "Double");

        // Simulate booking consumption
        inventory.decreaseRoom("Single");
        inventory.decreaseRoom("Double");

        System.out.println("\nAfter bookings:");
        inventory.showInventory();

        // Cancel booking
        System.out.println("\nCancelling R1...");
        service.cancelBooking("R1", inventory);

        System.out.println("\nAfter cancellation:");
        inventory.showInventory();

        // Show rollback history
        service.showRollbackHistory();
    }
}
