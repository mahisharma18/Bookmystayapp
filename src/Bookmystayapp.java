import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UseCase12DataPersistenceRecovery {

    // ================= RoomInventory =================
    static class RoomInventory {
        private Map<String, Integer> rooms = new HashMap<>();

        public void setRoomCount(String type, int count) {
            rooms.put(type, count);
        }

        public int getRoomCount(String type) {
            return rooms.getOrDefault(type, 0);
        }

        public Map<String, Integer> getAllRooms() {
            return rooms;
        }

        public void clear() {
            rooms.clear();
        }
    }

    // ================= FilePersistenceService =================
    static class FilePersistenceService {

        // Save inventory to file
        public void saveInventory(RoomInventory inventory, String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {
                    writer.write(entry.getKey() + "=" + entry.getValue());
                    writer.newLine();
                }
                System.out.println("Inventory saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving inventory: " + e.getMessage());
            }
        }

        // Load inventory from file
        public void loadInventory(RoomInventory inventory, String filePath) {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("No valid inventory data found. Starting fresh.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                inventory.clear();
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=");

                    if (parts.length == 2) {
                        String roomType = parts[0];
                        int count = Integer.parseInt(parts[1]);
                        inventory.setRoomCount(roomType, count);
                    }
                }

                System.out.println("Inventory loaded successfully.");

            } catch (IOException | NumberFormatException e) {
                System.out.println("Error loading inventory: " + e.getMessage());
            }
        }
    }

    // ================= Main Method =================
    public static void main(String[] args) {

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService service = new FilePersistenceService();

        System.out.println("System Recovery");

        // Load existing data
        service.loadInventory(inventory, filePath);

        // If no data, initialize default values
        if (inventory.getAllRooms().isEmpty()) {
            inventory.setRoomCount("Single", 5);
            inventory.setRoomCount("Double", 3);
            inventory.setRoomCount("Suite", 2);
        }

        // Display inventory
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.getAllRooms().keySet()) {
            System.out.println(type + ": " + inventory.getRoomCount(type));
        }

        // Save inventory
        service.saveInventory(inventory, filePath);
    }
}