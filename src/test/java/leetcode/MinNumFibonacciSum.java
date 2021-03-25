package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MinNumFibonacciSum {
    public int findMinFibonacciNumbers(int k) {
        if (k <= 3)
            return 1;
        int a = greatestFibonacciNumberLessThan(k);
        if (a == k)
            return 1;
        return 1 + findMinFibonacciNumbers(k - a);
    }

    private int greatestFibonacciNumberLessThan(int k) {
        if (k <= 3)
            return k;
        int res = 1;
        int pre = 1;
        while (res + pre <= k) {
            int tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    // unique sorted array.
    private int count(int nums[], int left, int right) {
        if (nums[left] == nums[right])
            return 1;
        int mid = (left + right) / 2;
        // [left, mid], (mid, right]
        int leftCount = count(nums, left, mid);
        int rightCount = count(nums, mid+1, right);
        if (nums[mid] == nums[mid + 1])
            return leftCount + rightCount - 1;
        else
            return leftCount + rightCount;
    }

    public int countUnique(int nums[]) {
        return count(nums, 0, nums.length - 1);
    }

    // Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 0) return 0;
        Map<Long, Integer> preSum = new HashMap<>();

        long sum = 0;
        int result = 0;
        preSum.put(0L,1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result += preSum.getOrDefault(sum - k, 0);
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

    //Verifying an Alien Dictionary
    static class AlienComparator implements Comparator<String> {
        // dict[order[i] - 'a'] = WEIGHT
        int dict[];

        AlienComparator(String order) {
            dict = new int[26];
            for (int i = 0; i < dict.length; i++) {
                dict[order.charAt(i) - 'a'] = i;
            }
        }

        @Override
        public int compare(String o1, String o2) {
            for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
                int order1 = dict[o1.charAt(i) - 'a'];
                int order2 = dict[o2.charAt(i) - 'a'];
                if (order1 < order2)
                    return -1;
                else if (order1 > order2)
                    return 1;
            }

            if (o1.length() < o2.length())
                return -1;
            else if (o1.length() > o2.length())
                return 1;
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return obj.equals(this);
        }
    };

    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 1)
            return true;

        AlienComparator c = new AlienComparator(order);

        for (int i = 0; i < words.length - 1; i++) {
            // compare words[i] and words[i+1]
            if (c.compare(words[i], words[i+1]) > 0)
                return false;
        }
        return true;
    }

    // Task Scheduler
    public int leastInterval(char[] tasks, int n) {
        int map[] = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }

        Arrays.sort(map);
        // Find the number of MAX in map.
        int i = 1;
        while (i <= map.length && map[map.length - i] == map[map.length - 1])
            i++;
        return Math.max(tasks.length, (map[25] - 1) * (n + 1) + i - 1);
    }



    @Test
    public void test() {
        MinNumFibonacciSum s = new MinNumFibonacciSum();
        //System.out.println(s.findMinFibonacciNumbers(5));
        //System.out.println(s.countUnique(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 5, 5, 5, 7, 7, 8, 8, 10}));
        //System.out.println(s.subarraySum(new int[]{1,1,1,2}, 2));
        //System.out.println(s.isAlienSorted(new String[]{"apple","app"}, "worldabcefghijkmnpqstuvxyz"));
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
    }
}
