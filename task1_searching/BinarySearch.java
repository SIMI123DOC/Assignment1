package task1_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearch {

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

        return Path.of("orders_50000.csv").toAbsolutePath().normalize();
    }

    public static void main(String[] args) {

        int targetOrderId = 100025;

        ArrayList<String[]> orders = new ArrayList<>();

        int comparisons = 0;

        try (BufferedReader br = Files.newBufferedReader(resolveCsvPath())) {

            // Skip CSV header
            br.readLine();

            String line;

            // Load all orders into the ArrayList
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                orders.add(data);
            }

            orders.sort(Comparator.comparingInt(order -> Integer.parseInt(order[0])));

            // Binary Search
            int low = 0;
            int high = orders.size() - 1;

            boolean found = false;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                comparisons++;

                int currentOrderId = Integer.parseInt(orders.get(mid)[0]);

                if (currentOrderId == targetOrderId) {

                    String[] order = orders.get(mid);

                    System.out.println("Order Found!");
                    System.out.println("--------------------");
                    System.out.println("Order ID: " + order[0]);
                    System.out.println("Customer ID: " + order[1]);
                    System.out.println("Amount: " + order[2]);
                    System.out.println("Created At: " + order[3]);
                    System.out.println("Position: " + (mid + 1));

                    found = true;
                    break;
                } else if (currentOrderId < targetOrderId) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (!found) {
                System.out.println("Order Not Found!");
            }

            System.out.println("--------------------");
            System.out.println("Total Comparisons: " + comparisons);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
