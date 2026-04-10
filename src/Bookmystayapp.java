import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * MAIN CLASS - UseCase11ConcurrentBookingSimulation
 *
 * Simulates multiple users attempting to book rooms concurrently.
 * Demonstrates race conditions and synchronization.
 */
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        // Shared resources
        Queue<String> bookingQueue = new ConcurrentLinkedQueue<>();
        RoomInventory inventory = new RoomInventory(5); // 5 rooms
        AllocationService allocationService = new AllocationService();

        // Add booking requests
        for (int i = 1; i <= 10; i++) {
            bookingQueue.add("User-" + i);
        }

        // Create booking processor tasks
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService),
                "Processor-1"
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService),
                "Processor-2"
        );

        // Start concurrent processing
        t1.start();
        t2.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        System.out.println("\nAll bookings processed.");
        System.out.println("Remaining rooms: " + inventory.getAvailableRooms());
    }
}

/**
 * Shared Room Inventory
 */
class RoomInventory {
    private int availableRooms;

    public RoomInventory(int rooms) {
        this.availableRooms = rooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    // Synchronized method to prevent race condition
    public synchronized boolean bookRoom(String user) {
        if (availableRooms > 0) {
            System.out.println(user + " is booking a room...");
            availableRooms--;

            // Simulate delay (makes race conditions visible)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("✅ Room booked for " + user +
                    " | Remaining: " + availableRooms);
            return true;
        } else {
            System.out.println("❌ No rooms available for " + user);
            return false;
        }
    }
}

/**
 * Handles allocation logic
 */
class AllocationService {

    public void allocate(String user, RoomInventory inventory) {
        inventory.bookRoom(user);
    }
}

/**
 * Booking Processor (Runnable task)
 */
class ConcurrentBookingProcessor implements Runnable {

    private Queue<String> bookingQueue;
    private RoomInventory inventory;
    private AllocationService allocationService;

    public ConcurrentBookingProcessor(
            Queue<String> bookingQueue,
            RoomInventory inventory,
            AllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {
        while (true) {
            String user = bookingQueue.poll();

            if (user == null) {
                break; // No more bookings
            }

            System.out.println(Thread.currentThread().getName()
                    + " processing " + user);

            allocationService.allocate(user, inventory);
        }
    }
}