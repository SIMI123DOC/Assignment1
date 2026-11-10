package task1_searching;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinearSearch {

    public static void main(String[] args) {

        int targetOrderId = 100025;

        int comparisons = 0;
        int position = 0;
        boolean found = false;

        try (BufferedReader br = new BufferedReader(
                new FileReader("orders_50000.csv"))) {

            // Skip the first row (column names)
            String line = br.readLine();

            // Read orders one by one
            while ((line = br.readLine()) != null) {

                position++;
                comparisons++;

                String[] data = line.split(",");

                int orderId = Integer.parseInt(data[0]);

                // Check whether the order ID matches
                if (orderId == targetOrderId) {

                    System.out.println("Order Found!");
                    System.out.println("--------------------");
                    System.out.println("Order ID: " + data[0]);
                    System.out.println("Customer ID: " + data[1]);
                    System.out.println("Amount: " + data[2]);
                    System.out.println("Created At: " + data[3]);

                    System.out.println("Position: " + position);

                    found = true;
                    break;
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
