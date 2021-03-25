package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class CoinChange
{    
    public int coinChange(int[] coins, int amount) {
        int dp[][] = new int[coins.length][amount + 1];
        
        for (int i = 0; i < coins.length; i++) 
            dp[i][0] = 0;
        
        for (int j = 0; j < amount + 1; j++) {
            if (j % coins[0]  == 0)
                dp[0][j] = j / coins[0];
            else
                dp[0][j] = -1;
        }
        
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                // dp[i][j] = min {dp[i-1][j - coins[i] * k] + k}
                int min = Integer.MAX_VALUE;
                for (int k = 0; coins[i] * k <= j; k++) {
                    int r = dp[i-1][j - coins[i] * k];
                    if (r != -1) {
                        if ((r + k) < min)
                            min = r + k;
                    }
                }
                dp[i][j] = (min == Integer.MAX_VALUE) ? -1 : min;
            }
        }
        
        return dp[coins.length - 1][amount];
    }
    
    private int process (int amount, int[] coins, int index, int [][] map) {
        int ret = 0;

        if (index == coins.length) {
            ret = amount == 0 ? 1:0;
        } else {
            for (int i = 0; coins[index] * i <= amount; i++) {
                int v = 0;
    
                v = map[index+1][amount - coins[index] * i];
                if (v == -1) {
                    v = process(amount - coins[index] * i, coins, index+1, map);
                    map[index+1][amount - coins[index] * i] = v;
                }
                
                ret += v;
            }
        }
        return ret;
    }
    public int change(int amount, int[] coins) {
        int map[][] = new int[coins.length+1][amount + 1];
        for (int[] array : map) {
            Arrays.fill(array, -1);
        }
        return process (amount, coins, 0, map);
    }
    
    /**
     * Minimum Insertion Steps to Make a String Palindrome.
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int dp[][] = new int[s.length()][s.length()];
        for (int[] array : dp) {
            Arrays.fill(array, -1);
        }

        getDP(dp, 0, s.length() - 1, s);
        return dp[0][s.length()-1];
    }
    
    private void getDP(int [][] dp, int i, int j, String s) {
        if (i == j)
            dp[i][j] = 0;
        else if (j == i+1) {
            dp[i][j] = s.charAt(i) == s.charAt(j) ? 0:1;
        }
       else {
           if (s.charAt(i) == s.charAt(j)) {
               if (dp[i+1][j-1] == -1) {
                   getDP(dp, i+1, j-1, s);
               }
               dp[i][j] = dp[i+1][j-1];
           } else {
               if (dp[i+1][j] == -1)
                   getDP(dp, i+1, j, s);
               if (dp[i][j-1] == -1)
                   getDP(dp, i, j-1, s);

               dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) +1;
           }
        }
    }

    
    private void calDP(int []dp, String s, int index) {
        if (dp[index] != -1)
            return;

        if (index == 0 || s.charAt(index) == '(')
            dp[index] = 0;
        else {
//            if (dp[index-1] == -1)
//                calDP(dp, s, index-1);
            
            if (index - 1 - dp[index-1] < 0 || s.charAt(index - 1 - dp[index-1]) == ')')
                dp[index] = 0;
            else {
                int leftSide = 0;
                if (index - 2 - dp[index-1] >= 0) {
//                    if (dp[index - 2 - dp[index-1]] == -1)
//                        calDP(dp, s, index - 2 - dp[index-1]);

                    leftSide = dp[index-2- dp[index-1]];
                }

                dp[index] = dp[index-1] + 2 + leftSide;
            }
        }
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int dp[] = new int[s.length()];
        Arrays.fill(dp, -1);

        for (int i = 0; i < dp.length; i++)
            calDP(dp, s, i);
        
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max)
                max = dp[i];
        }
        return max;
    }



    @Test
    public void test() {
        CoinChange cc = new CoinChange();
        int array[] = new int[] {3,2,3,1,2,4,5,5,6};
        //System.out.println(cc.findKthLargest(array, 4));
    }
}
