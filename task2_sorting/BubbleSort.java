package task2_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class BubbleSort {

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

        ArrayList<String[]> orders = new ArrayList<>();

        int comparisons = 0;
        int swaps = 0;

        try (BufferedReader br =
                Files.newBufferedReader(resolveCsvPath())) {

            // Skip CSV header
            br.readLine();

            String line;

            // Load first 10,000 orders
            while ((line = br.readLine()) != null
                    && orders.size() < 10000) {

                orders.add(line.split(","));
            }

            System.out.println("Total Orders: " + orders.size());

            long startTime = System.nanoTime();

            // Bubble Sort - Descending Order
            for (int i = 0; i < orders.size() - 1; i++) {

                for (int j = 0; j < orders.size() - i - 1; j++) {

                    comparisons++;

                    double amount1 =
                            Double.parseDouble(orders.get(j)[2]);

                    double amount2 =
                            Double.parseDouble(orders.get(j + 1)[2]);

                    if (amount1 < amount2) {

                        String[] temp = orders.get(j);

                        orders.set(j, orders.get(j + 1));

                        orders.set(j + 1, temp);

                        swaps++;
                    }
                }

                // Display first 3 passes
                if (i < 3) {

                    System.out.println("\nAfter Pass " + (i + 1));

                    for (int k = 0;
                         k < Math.min(5, orders.size()); k++) {

                        System.out.println(
                                "Order ID: " + orders.get(k)[0]
                                + " | Amount: " + orders.get(k)[2]
                        );
                    }
                }
            }

            long endTime = System.nanoTime();

            System.out.println("\n===== BUBBLE SORT RESULT =====");

            System.out.println("Sorting Order: Highest to Lowest");

            System.out.println("\nTop 5 Orders:");

            for (int i = 0; i < Math.min(5, orders.size()); i++) {

                System.out.println(
                        "Order ID: " + orders.get(i)[0]
                        + " | Amount: " + orders.get(i)[2]
                );
            }

            System.out.println("\nTotal Comparisons: " + comparisons);
            System.out.println("Total Swaps: " + swaps);

            System.out.println(
                    "Execution Time: "
                    + (endTime - startTime) / 1_000_000.0
                    + " ms"
            );

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}