
package task5;

import java.util.Arrays;

public class DynamicProgramming {

    public static void main(String[] args) {

        int[] coins = {4, 3, 1};
        int amount = 6;

        // dp[i] stores the minimum coins needed for amount i
        int[] dp = new int[amount + 1];

        // Initially, mark all amounts as impossible
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        // Calculate minimum coins for every amount
        for (int i = 1; i <= amount; i++) {

            for (int coin : coins) {

                if (i >= coin) {

                    dp[i] = Math.min(
                        dp[i],
                        dp[i - coin] + 1
                    );
                }
            }
        }

        System.out.println("===== DYNAMIC PROGRAMMING =====");
        System.out.println("Amount: " + amount);
        System.out.println("Minimum coins required: " + dp[amount]);
        // Display the actual coins selected
System.out.println("\nCoins Selected:");

int remaining = amount;

while (remaining > 0) {

    for (int coin : coins) {

        if (remaining >= coin &&
            dp[remaining] == dp[remaining - coin] + 1) {

            System.out.println(coin);

            remaining = remaining - coin;
            break;
        }
    }
}

        // Display the DP table
        System.out.println("\nDP Table:");

        for (int i = 0; i <= amount; i++) {
            System.out.println("Amount " + i + " = " + dp[i]);
        }
    }
}
