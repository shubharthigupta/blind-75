package Array.Easy.BestTimeToBuyAndSellStocks;

public class BestTimeToBuyAndSellStocksSolution {

    public static int maxProfit(int[] prices) {
        int profit = 0;
        int minPrice = prices[0];
        for(int i=1; i<prices.length; i++){
            int currPrice = prices[i];
            profit = Math.max(profit, (currPrice - minPrice));
            minPrice = Math.min(minPrice, currPrice);
        }
        return profit;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Typical case with profit
        runTest(new int[]{7, 1, 5, 3, 6, 4}, 5, "Test 1 - Regular profit case");

        // 2. No profit possible (descending prices)
        runTest(new int[]{7, 6, 4, 3, 1}, 0, "Test 2 - No profit (descending)");

        // 3. Only one day of prices
        runTest(new int[]{5}, 0, "Test 3 - Single day");

        // 4. Two days, profitable
        runTest(new int[]{1, 5}, 4, "Test 4 - Two days, profit");

        // 5. Two days, no profit
        runTest(new int[]{5, 1}, 0, "Test 5 - Two days, loss");

        // 6. Large constant values
        runTest(new int[]{1000, 1000, 1000, 1000}, 0, "Test 6 - No fluctuation");

        // 7. Max profit at end
        runTest(new int[]{2, 4, 1, 10}, 9, "Test 7 - Buy early, sell late");

        // 8. Max profit in middle
        runTest(new int[]{3, 8, 1, 2, 10}, 9, "Test 8 - Max profit in middle");

        // 9. Profit comes after initial drop
        runTest(new int[]{9, 2, 5, 7, 1, 10}, 9, "Test 9 - Drop then profit");

        // 10. Large input for performance
        int[] large = new int[10000];
        for (int i = 0; i < 10000; i++) {
            large[i] = i;
        }
        runTest(large, 9999, "Test 10 - Increasing prices (max profit)");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int[] input, int expected, String testName) {
        int result = maxProfit(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
        }
    }

    /*

    Solution Description :
    ----------------------

    Line 6 :
    Profit cannot be negative. Therefore, initializing profit with 0.
    In case of decreasing prices, maximum profit returned will be 0.

    Line 7:
    Initializing Minimum Price as the price on Day 1.

    Line 8:
    Since the selling (if prices drop) will start from Day 2, initialing the loop from i = 1 which is the second element of the array.

    Line 9-11:
    We set the current element of the loop as the current price.
    We then compare the existing profit with the profit made by buying the stock at minimum price and selling it at the current price.
    If the profit made is higher than the existing profit, we update the existing profit with the new value.
    Similarly, if the current price is lower than the existing minimum price, we update the minimum price with the current price.

     */

}
