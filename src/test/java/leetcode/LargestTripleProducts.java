package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LargestTripleProducts {

    int[] findMaxProduct(int[] arr) {
        // Write your code here
        int[] ret = new int[arr.length];
        if (arr.length < 3) {
            Arrays.fill(ret, -1);
            return ret;
        }
        ret[0] = ret[1] = -1;
        ret[2] = arr[0] * arr[1] * arr[2];
        int[] max = new int[3];
        max[0] = arr[0];
        max[1] = arr[1];
        max[2] = arr[2];
        for (int i = 3; i < arr.length; i++) {
            Arrays.sort(max);
            if (arr[i] > max[0]) {
                max[0] = arr[i];
            }
            ret[i] = max[0] * max[1] * max[2];
        }
        return ret;
    }

    @Test
    public void test1() {
        LargestTripleProducts ltp = new LargestTripleProducts();
        int[] ret = ltp.findMaxProduct(new int[] {1, 2, 3, 4, 5});
        //int[] expected_1 = {-1, -1, 6, 24, 60};
        assertEquals(-1, ret[0]);
        assertEquals(-1, ret[1]);
        assertEquals(6, ret[2]);
        assertEquals(24, ret[3]);
        assertEquals(60, ret[4]);

        ret = ltp.findMaxProduct(new int[] {2, 4, 7, 1, 5, 3});
        // {};
        //    int[] expected_2 = {-1, -1, 56, 56, 140, 140};
        assertEquals(-1, ret[0]);
        assertEquals(-1, ret[1]);
        assertEquals(56, ret[2]);
        assertEquals(56, ret[3]);
        assertEquals(140, ret[4]);
    }

    int[] findMedian(int[] arr) {
        // Write your code here
        int[] ret = new int[arr.length];
        if (arr.length == 0)
            return ret;
        ret[0] = arr[0];
        if (arr.length == 1) {
            return ret;
        }
        ret[1] = (arr[0] + arr[1]) / 2;
        if (arr.length == 2) {
            return ret;
        }
        int[] middle = new int[3];
        middle[0] = 0;
        middle[1] = arr[0];
        middle[2] = 0;
        for (int i = 2; i < arr.length; i = i + 2) {
            middle[0] = arr[i];
            middle[2] = arr[i-1];
            Arrays.sort(middle);
            ret[i] = middle[1];
        }

        middle = new int[4];
        middle[0] = 0;
        middle[1] = arr[0];
        middle[2] = arr[1];
        middle[3] = 0;
        for (int i = 3; i < arr.length; i = i + 2) {
            middle[0] = arr[i];
            middle[3] = arr[i-1];
            Arrays.sort(middle);
            ret[i] = (middle[1] + middle[2]) / 2;
        }
        return ret;
    }

    @Test
    public void test2() {
        // ;
        //    int[] expected_1 = {5, 10, 5, 4};
        LargestTripleProducts ltp = new LargestTripleProducts();
        int[] ret = ltp.findMedian(new int[]{5, 15, 1, 3});
        assertEquals(5, ret[0]);
        assertEquals(10, ret[1]);
        assertEquals(5, ret[2]);
        assertEquals(4, ret[3]);

        // nt[] arr_2 = {};
        //    int[] expected_2 = {2, 3, 4, 3, 4, 3};
        ret = ltp.findMedian(new int[]{2, 4, 7, 1, 5, 3});
        assertEquals(2, ret[0]);
        assertEquals(3, ret[1]);
        assertEquals(4, ret[2]);
    }
}
