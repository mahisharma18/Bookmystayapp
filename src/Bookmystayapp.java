import java.util.Scanner;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Enum for Room Types
enum RoomType {
    SINGLE, DOUBLE, SUITE
}

// Inventory class (basic placeholder)
class RoomInventory {
    public boolean isAvailable(RoomType type) {
        // Simulated availability check
        return true;
    }
}

// Validator class
class ReservationValidator {
    public void validate(String guestName, RoomType roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (roomType == null) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (!inventory.isAvailable(roomType)) {
            throw new InvalidBookingException("Selected room type is not available.");
        }
    }
}

// Queue class (basic placeholder)
class BookingRequestQueue {
    public void addBooking(String guestName, RoomType roomType) {
        System.out.println("Booking successful for " + guestName + " (" + roomType + ")");
    }
}

// Main Application
public class BookingApp {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            // Input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String input = scanner.nextLine();

            // Convert input to Enum safely
            RoomType roomType;
            try {
                roomType = RoomType.valueOf(input.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidBookingException("Invalid room type selected.");
            }

            // Validate booking
            validator.validate(guestName, roomType, inventory);

            // Add booking to queue
            bookingQueue.addBooking(guestName, roomType);

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
