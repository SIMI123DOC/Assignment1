
package task3;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class DuplicateDetection {

    private static Path resolveCsvPath() {

        Path[] candidates = {
            Path.of("orders_50000.csv"),
            Path.of("..", "orders_50000.csv")
        };

        for (Path candidate : candidates) {
            if (Files.exists(candidate)) {
                return candidate.toAbsolutePath().normalize();
            }
        }

        return Path.of("orders_50000.csv");
    }

    public static void main(String[] args) {

        HashSet<String> uniqueOrders = new HashSet<>();

        int totalOrders = 0;
        int duplicateCount = 0;

        try (BufferedReader br =
                Files.newBufferedReader(resolveCsvPath())) {

            // Skip CSV header
            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String orderId = data[0];
                String customerId = data[1];
                String amount = data[2];
                String createdAt = data[3];

                totalOrders++;

                // Extract date and time up to the minute
                String createdMinute = createdAt.substring(0, 16);

                // Create a unique signature
                String signature =
                        customerId + "|" + amount + "|" + createdMinute;

                // Check for duplicate signature
                if (!uniqueOrders.add(signature)) {

                    duplicateCount++;

                    System.out.println("Potential Duplicate Found!");
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Customer ID: " + customerId);
                    System.out.println("Amount: " + amount);
                    System.out.println("Created At: " + createdAt);
                    System.out.println("-------------------------");
                }
            }

            System.out.println("\n===== DUPLICATE DETECTION RESULT =====");

            System.out.println("Total Orders: " + totalOrders);

            System.out.println("Unique Signatures: " + uniqueOrders.size());

            System.out.println("Potential Duplicates: " + duplicateCount);

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
