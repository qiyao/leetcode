package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class NumArray {
    int bit[]; // Binary Indexed Tree.
    int nums[];

    public NumArray(int[] nums) {
        bit = new int[nums.length + 1];
        for (int i = 1; i < bit.length; i++) {
            int from = i - (i&-i) + 1;
            //System.out.println(from + " " + i);
            for (int j = from; j <= i; j++) {
                bit[i] += nums[j-1];
            }
        }
        this.nums = nums;
    }

    public void update(int index, int val) {
        for (int i = index+1; i < bit.length; i += (i&-i)) {
            bit[i] += val - nums[index];
        }
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return sum(right + 1) - sum(left);
    }

    private int sum(int x) {
        int ss = 0;
        for (; x > 0; x -= (x&-x)) {
            ss += bit[x];
        }
        return ss;
    }
}

public class RangeSumQuery {
    @Test
    public void test() {
        NumArray na = new NumArray(new int[]{1, 3, 5});
        assertEquals(9, na.sumRange(0,2 ));
        assertEquals(1, na.sumRange(0,0 ));
        assertEquals(8, na.sumRange(1,2 ));
        na.update(1, 3);
        assertEquals(9, na.sumRange(0,2 ));
        assertEquals(1, na.sumRange(0,0 ));
        na.update(1, 2);
        assertEquals(8, na.sumRange(0,2 ));
    }
}
