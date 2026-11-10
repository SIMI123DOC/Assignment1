
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> orders = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                new FileReader("orders_50000.csv")
            );

            String line;

            // Skip CSV header
            br.readLine();

            while ((line = br.readLine()) != null) {
                orders.add(line);
            }

            br.close();

            System.out.println("Total orders: " + orders.size());

            System.out.println("First 5 orders:");

            for (int i = 0; i < 5; i++) {
                System.out.println(orders.get(i));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
