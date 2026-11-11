
package task5;

import java.util.Arrays;

public class Greedy {

    public static void main(String[] args) {

        int[] coins = {4, 3, 1};
        int amount = 6;

        // Sort coins in descending order
        Arrays.sort(coins);

        System.out.println("===== GREEDY COIN CHANGE =====");
        System.out.println("Amount: " + amount);

        int count = 0;

        // Select the largest coin first
        for (int i = coins.length - 1; i >= 0; i--) {

            while (amount >= coins[i]) {

                amount = amount - coins[i];
                count++;

                System.out.println("Selected coin: " + coins[i]);
            }
        }

        System.out.println("Total coins used: " + count);
    }
}

