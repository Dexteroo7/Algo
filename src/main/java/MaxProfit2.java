//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/
class MaxProfit2 {
    public int maxProfit(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        int profit = 0;

        int boughtAt = prices[0];
        for (int index = 0; index < prices.length; index++) {
            //don't buy previous one
            if (prices[index] < boughtAt)
                boughtAt = prices[index];
            else {
                //sell and buy again?
                profit += prices[index] - boughtAt;
                boughtAt = prices[index];
            }
        }

        return profit;
    }
}
