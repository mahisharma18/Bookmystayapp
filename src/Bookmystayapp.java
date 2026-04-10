// Custom Exception Class
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// Passenger Bogie Class
class PassengerBogie {
    private int id;
    private int capacity;

    // Constructor with validation (Fail-Fast)
    public PassengerBogie(int id, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "PassengerBogie{id=" + id + ", capacity=" + capacity + "}";
    }
}

// Main Class
public class UseCase14CustomException {

    public static void main(String[] args) {

        try {
            // Valid bogie
            PassengerBogie b1 = new PassengerBogie(1, 80);
            System.out.println("Created: " + b1);

            // Invalid bogie (Negative capacity)
            PassengerBogie b2 = new PassengerBogie(2, -10);
            System.out.println("Created: " + b2);

        } catch (InvalidCapacityException e) {
            System.out.println("❌ Exception Occurred: " + e.getMessage());
        }

        try {
            // Invalid bogie (Zero capacity)
            PassengerBogie b3 = new PassengerBogie(3, 0);
            System.out.println("Created: " + b3);

        } catch (InvalidCapacityException e) {
            System.out.println("❌ Exception Occurred: " + e.getMessage());
        }

        try {
            // Multiple valid bogies
            PassengerBogie b4 = new PassengerBogie(4, 70);
            PassengerBogie b5 = new PassengerBogie(5, 90);

            System.out.println("Created: " + b4);
            System.out.println("Created: " + b5);

        } catch (InvalidCapacityException e) {
            System.out.println("❌ Exception Occurred: " + e.getMessage());
        }
    }
}