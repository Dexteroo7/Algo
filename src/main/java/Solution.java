import java.util.Arrays;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
class Solution {

    public int maxProfit(int k, int[] prices) {

        if (k == 0 || prices == null || prices.length < 2)
            return 0;

        if (k >= prices.length)
            return infiniteTransactions(prices);

        int[] purchaseCost = new int[k];
        Arrays.fill(purchaseCost, Integer.MAX_VALUE);
        int[] profits = new int[k];
        Arrays.fill(profits, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {

            purchaseCost[0] = min(purchaseCost[0], prices[i]);
            profits[0] = max(profits[0], prices[i] - purchaseCost[0]);

            for (int j = 1; j < profits.length; j++) {

                purchaseCost[j] = min(purchaseCost[j], prices[i] - profits[j - 1]);
                profits[j] = max(profits[j], prices[i] - purchaseCost[j]);
            }
        }

        return profits[profits.length - 1];
    }

    public int infiniteTransactions(int[] prices) {

        int boughtAt = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {

            if (boughtAt <= prices[i]) {
                profit += prices[i] - boughtAt;
            }
            boughtAt = prices[i];
        }

        return profit;
    }
}
