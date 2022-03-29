package leetcode;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BuySellStock {
    public int maxProfit(int[] prices) {
        // dp[i] is the max profit starting from the i-th day.
        int dp[] = new int[prices.length];

        for (int i = prices.length - 2; i>=0; i --) {
            for (int j = 1; i + j < prices.length; j++) {
                int val = prices[i+j] - prices[i];
                if (i + j + 2 < prices.length) {
                    if (dp[i+j+2] > 0)
                        val += dp[i+j+2];
                }
                if (val > dp[i]) {
                    dp[i] = val;
                    System.out.println("buy on " + i + ", sell on " + (i+j) + ", dp[i] " + dp[i]);
                }
                if (dp[i] < dp[i+1]) {
                    dp[i] = dp[i+1];
                }
            }
        }

        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max)
                max = dp[i];
        }
        return max;
    }

    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) return 0;
        int buy[] = new int[prices.length];
        int sell[] = new int[prices.length];
        int hold[] = new int[prices.length];
        int skip[] = new int[prices.length];

        buy[0] = 0 - prices[0];
        hold[0] = 0 - prices[0];
        for(int i = 1; i < prices.length; i++){
            buy[i] = Math.max(sell[i-1], skip[i-1]) - prices[i];
            sell[i] = Math.max(buy[i-1], hold[i-1]) + prices[i] - fee;
            hold[i] = Math.max(buy[i-1], hold[i-1]);
            skip[i] = Math.max(sell[i-1], skip[i-1]);
        }
        int max = Math.max(sell[prices.length - 1], buy[prices.length - 1]);
        max = Math.max(max, hold[prices.length - 1]);
        max = Math.max(max, skip[prices.length - 1]);
        max = Math.max(0, max);
        return max;
    }

    @Test
    public void test() {
        BuySellStock bss = new BuySellStock();
        assertEquals(3, bss.maxProfit(new int[]{1,2,3,0,2}));
        assertEquals(0, bss.maxProfit(new int[]{1}));
        assertEquals(7, bss.maxProfit(new int[]{6,1,6,4,3,0,2}));

        assertEquals(8, bss.maxProfit(new int[]{1,3,2,8,4,9}, 2));
        assertEquals(6, bss.maxProfit(new int[]{1,3,7,5,10,3}, 3));
    }
}
