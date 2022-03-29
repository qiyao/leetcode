package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SlowSums {
    int getTotalTime(int[] arr) {
        // Write your code here
        int total = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            total += arr[i] * (i + 1);
        }
        total -= arr[arr.length - 1];
        return total;
    }

    @Test
    public void test() {
        SlowSums ss = new SlowSums();
        assertEquals(26, ss.getTotalTime(new int[] {4, 2, 1, 3}));
        assertEquals(88, ss.getTotalTime(new int[]{2, 3, 9, 8, 4}));
    }

    int minOperations(int[] arr) {
        // Candidates.
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        int[] targetArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            targetArray[i] = i+1;
        int target = array2int(targetArray);

        queue.offer(arr);
        seen.add(array2int(arr));

        int ret = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (target == array2int(curr)) {
                    return ret;
                }

                // reverse some of them, arr[, ].
                for (int j = 0; j < arr.length; j++) {
                    for (int k = j + 1; k < arr.length; k++) {
                        int[] next = curr.clone();
                        reverse(next, j, k);
                        if (!seen.contains(array2int(next))) {
                            queue.offer(next);
                            seen.add(array2int(next));
                        }
                    }
                }
            }
            ret++;
        }
        return ret;
    }

    /**
     * Reverse arr[j,...,k].
     * @param arr
     * @param from
     * @param to
     */
    private void reverse (int[] arr, int from, int to) {
        for (; from < to; from++, to--) {
            int tmp = arr[to];
            arr[to] = arr[from];
            arr[from] = tmp;
        }
    }

    private int array2int(int[] arr) {
        int ret = 0;
        for (int i = 0; i < arr.length; i++){
            ret = arr[i] + ret * 10;
        }
        return ret;
    }

    @Test
    public void test2() {
        SlowSums ss = new SlowSums();
        assertEquals(1, ss.minOperations(new int[]{1, 2, 5, 4, 3}));
        assertEquals(2, ss.minOperations(new int[]{3, 1, 2}));
    }
}
