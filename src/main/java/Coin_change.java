import java.util.*;
import java.util.stream.IntStream;

class Coin_change {
    public int coinChange(int[] coins, int amount) {

        return coinChange(coins, amount, new HashMap<>());
    }

    public int coinChange(int[] coins, int amount, Map<Integer, Integer> memo) {

        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;

        if (memo.containsKey(amount))
            return memo.get(amount);

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {

            int res = coinChange(coins, amount - coin, memo);
            if (res >= 0 && res + 1 < min)
                min = 1 + res;
        }

        memo.put(amount, min == Integer.MAX_VALUE ? -1 : min);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
