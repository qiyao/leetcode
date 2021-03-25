package leetcode;

import org.junit.Test;

public class SearchInRotatedSortedArray {


    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int m = (low + high) / 2;
            if (nums[m] >= nums[low]) {
                if (target <= nums[m] && target >= nums[low]) high = m;
                else low = m + 1;
            } else {
                if (target >= nums[m] && target <= nums[high]) low = m+1;
                else high = m;
            }

        }

        return nums[low] == target ? low : - 1;
    }

    @Test
    public void test() {

    }
}
