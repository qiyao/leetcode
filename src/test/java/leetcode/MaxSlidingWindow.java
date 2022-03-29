package leetcode;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MaxSlidingWindow
{
    public int[] maxSlidingWindow(int[] nums, int k) {
        int out[] = new int[nums.length - k + 1];
        // A queue of indexes.  Index is added to HEAD.
        Deque<Integer> qmax = new LinkedList<Integer>();
        // [i, i+k] is the sliding window.  New index is added to qmax head.
        // HEAD 0    1..............TAIL
        //      X+N, X,
        // nums[X+N] < nums[X]
        for (int i = 0; i < nums.length; i++) {
            // Remove numbers out of range k, from TAIL.
            if (!qmax.isEmpty() && qmax.peekLast() <= i - k) {
                // k = 3, i = 3,
                // [1  3  -1] -3  5  3  6  7 3
                // qmax: TAIL [0, 1, 2] HEAD
                qmax.removeLast();
                // qmax: TAIL [1, 2] HEAD
            }
            
            // Remove numbers less than nums[i] from HEAD.
            // k = 3, i = 3,
            // [1  3  -1] -3  5  3  6  7 3
            // qmax: TAIL [0, 1, 2] HEAD
            // Then, qmax: TAIL [1, 2] HEAD
            while(!qmax.isEmpty() && nums[qmax.peek()] <= nums[i])
                qmax.remove();
            
            qmax.addFirst(i);
            // qmax: TAIL [1, 2, 3] HEAD

            if (i >= k - 1)
                out[i - k + 1] = nums[qmax.peekLast()];
        }
        
        return out;
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        int out[] = new int[nums.length - k + 1];
        Deque<Integer> qmax = new ArrayDeque<Integer>();
        // [1  3  -1] -3  5  3  6  7 3
        // qmax: TAIL [0, 1, 2] HEAD

        for (int i = 0; i < nums.length; i++) {
            // Remove one from TAIL if the window size is greater than k.
            if (!qmax.isEmpty() && i - qmax.peekLast() >= k) {
                qmax.removeLast();
            }

            // Before put nums[i] into window, remove all elements in window from HEAD if they
            // are less than nums[i].
            while(!qmax.isEmpty() && nums[qmax.peek()] <= nums[i]) {
                qmax.remove();
            }

            qmax.addFirst(i);

            if (i >= k - 1) {
                out[i - k + 1] = nums[qmax.peekLast()];
            }
        }

        return out;
    }

    @Test
    public void test()
    {
        MaxSlidingWindow w = new MaxSlidingWindow();
        int r[] = w.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3);
        int expected[] = {3,3,5,5,6,7};
        assertTrue(Arrays.equals(expected, r));

    }

}
