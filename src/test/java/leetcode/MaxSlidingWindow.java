package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

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
            // Remove numbers out of range k, from TAIL, because index on TAIL side is
            // less than HEAD side.
            while(!qmax.isEmpty() && qmax.peekLast() <= i - k)
                qmax.removeLast();
            
            // Remove numbers less than nums[i].
            while(!qmax.isEmpty() && nums[qmax.peek()] <= nums[i])
                qmax.remove();
            
            qmax.addFirst(i);

//            for (Integer I : qmax) {
//                if (i >= k - 1)
//                    System.out.print(':');
//                System.out.print(nums[I]);
//                System.out.print(' ');
//            }
//            System.out.print('\n');

            if (i >= k - 1)
                out[i - k + 1] = nums[qmax.peekLast()];
            
        }
        
        return out;
    }

    @Test
    public void test()
    {
        MaxSlidingWindow w = new MaxSlidingWindow();
        int r[] = w.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3);
        for (int i : r) {
            System.out.print(i);
            System.out.print(' ');
        }

    }

}
