// Custom Runtime Exception
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

// Goods Bogie Class
class GoodsBogie {
    private int id;
    private String shape; // Rectangular / Cylindrical
    private String cargo;

    public GoodsBogie(int id, String shape) {
        this.id = id;
        this.shape = shape;
    }

    public void assignCargo(String cargoType) {
        try {
            // Validation Rule
            if (shape.equalsIgnoreCase("Rectangular") && cargoType.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("Unsafe: Cannot assign Petroleum to Rectangular Bogie");
            }

            // Safe assignment
            this.cargo = cargoType;
            System.out.println("✅ Cargo assigned successfully: " + cargoType + " to Bogie " + id);

        } catch (CargoSafetyException e) {
            // Handle exception
            System.out.println("❌ Error: " + e.getMessage());

        } finally {
            // Always executes
            System.out.println("🔄 Assignment attempt completed for Bogie " + id);
        }
    }

    public String getCargo() {
        return cargo;
    }

    public String getShape() {
        return shape;
    }
}

// Main Class
public class UseCase15CargoHandling {

    public static void main(String[] args) {

        // Create bogies
        GoodsBogie b1 = new GoodsBogie(1, "Cylindrical");
        GoodsBogie b2 = new GoodsBogie(2, "Rectangular");

        // Safe assignment
        b1.assignCargo("Petroleum");

        System.out.println();

        // Unsafe assignment (handled safely)
        b2.assignCargo("Petroleum");

        System.out.println();

        // Program continues after exception
        b2.assignCargo("Coal");

        System.out.println("\n✅ Program continues execution safely.");
    }
}