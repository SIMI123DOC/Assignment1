
package task2_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MergeSort {

    static long comparisons = 0;

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

    // Merge two sorted halves
    public static void merge(
            String[][] orders,
            String[][] temp,
            int left,
            int mid,
            int right) {

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {

            comparisons++;

            double amount1 =
                    Double.parseDouble(orders[i][2]);

            double amount2 =
                    Double.parseDouble(orders[j][2]);

            // Descending order
            if (amount1 >= amount2) {
                temp[k] = orders[i];
                i++;
            } else {
                temp[k] = orders[j];
                j++;
            }

            k++;
        }

        while (i <= mid) {
            temp[k] = orders[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = orders[j];
            j++;
            k++;
        }

        // Copy merged elements back
        for (int x = left; x <= right; x++) {
            orders[x] = temp[x];
        }
    }

    // Divide and sort
    public static void mergeSort(
            String[][] orders,
            String[][] temp,
            int left,
            int right) {

        if (left < right) {

            int mid = left + (right - left) / 2;

            mergeSort(orders, temp, left, mid);

            mergeSort(orders, temp, mid + 1, right);

            merge(orders, temp, left, mid, right);
        }
    }

    public static void main(String[] args) {

        ArrayList<String[]> orderList = new ArrayList<>();

        try (BufferedReader br =
                Files.newBufferedReader(resolveCsvPath())) {

            // Skip CSV header
            br.readLine();

            String line;

            // Load first 10,000 orders
            while ((line = br.readLine()) != null
                    && orderList.size() < 10000) {

                orderList.add(line.split(","));
            }

            System.out.println("Total Orders: " + orderList.size());

            String[][] orders =
                    orderList.toArray(new String[0][]);

            String[][] temp =
                    new String[orders.length][];

            long startTime = System.nanoTime();

            mergeSort(orders, temp, 0, orders.length - 1);

            long endTime = System.nanoTime();

            System.out.println("\n===== MERGE SORT RESULT =====");

            System.out.println("Sorting: Highest to Lowest");

            System.out.println("\nTop 5 Orders:");

            for (int i = 0; i < Math.min(5, orders.length); i++) {

                System.out.println(
                        "Order ID: " + orders[i][0]
                        + " | Amount: " + orders[i][2]
                );
            }

            System.out.println("\nTotal Comparisons: " + comparisons);

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
