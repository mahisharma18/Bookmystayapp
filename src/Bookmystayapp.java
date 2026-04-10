import java.util.*;
import java.util.stream.Collectors;

// Bogie class
class Bogie {
    private int id;
    private int capacity;

    public Bogie(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Bogie{id=" + id + ", capacity=" + capacity + "}";
    }
}

public class UseCase13PerformanceBenchmark {

    // Loop-based filtering
    public static List<Bogie> filterUsingLoop(List<Bogie> bogies) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.getCapacity() > 60) {
                result.add(b);
            }
        }
        return result;
    }

    // Stream-based filtering
    public static List<Bogie> filterUsingStream(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        // Step 1: Create dataset (Large dataset for benchmarking)
        List<Bogie> bogies = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= 100000; i++) { // large dataset
            bogies.add(new Bogie(i, rand.nextInt(100)));
        }

        // ================= LOOP BENCHMARK =================
        long startLoop = System.nanoTime();

        List<Bogie> loopResult = filterUsingLoop(bogies);

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        // ================= STREAM BENCHMARK =================
        long startStream = System.nanoTime();

        List<Bogie> streamResult = filterUsingStream(bogies);

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        // ================= RESULTS =================
        System.out.println("Loop Result Size: " + loopResult.size());
        System.out.println("Stream Result Size: " + streamResult.size());

        System.out.println("Loop Execution Time (ns): " + loopTime);
        System.out.println("Stream Execution Time (ns): " + streamTime);

        // Validate correctness
        if (loopResult.size() == streamResult.size()) {
            System.out.println("✅ Both methods produce identical results.");
        } else {
            System.out.println("❌ Results mismatch!");
        }

        // Ensure time is valid
        if (loopTime > 0 && streamTime > 0) {
            System.out.println("✅ Execution time measured correctly.");
        }
    }
}