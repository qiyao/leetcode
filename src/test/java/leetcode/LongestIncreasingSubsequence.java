package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class LongestIncreasingSubsequence1 extends LongestIncreasingSubsequence{
    public int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        int len = 0;
        for (int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if (i < 0) i = -(i+1);
            dp[i] = x;
            if (i == len) len++;
        }
        return len;
    }
}


public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {

        // lis[i] is longest increasing subsequence startingf rom
        // index i.
        int lis[] = new int[nums.length];
        lis[nums.length - 1] = 1;
        int ret = 1;

        if (nums.length > 1) {
            for (int i = nums.length - 2; i >= 0; i--) {

                    int maxLisFromIPlusOne = 0;
                    for (int j = i+1; j < nums.length; j++) {
                        if (nums[i] < nums[j]) {
                            maxLisFromIPlusOne = Math.max(maxLisFromIPlusOne, lis[j]);
                        }
                    }
                    lis[i] = maxLisFromIPlusOne + 1;
                    ret = Math.max(ret, lis[i]);
            }
        }

        return ret;
    }

    @Test
    public void test(){
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence1();
        assertEquals(4, lis.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        assertEquals(4, lis.lengthOfLIS(new int[]{0,1,0,3,2,3}));
        assertEquals(1, lis.lengthOfLIS(new int[]{7,7,7,7}));
    }
}
