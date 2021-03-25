package leetcode;

import org.junit.Test;

import java.util.*;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int indexToReplace = - 1;
        int indexToReplaceHigh = - 1;
        for (int i = nums.length - 1; i > 0 && i > indexToReplace; i--) {
            // j < i, find the first nums[j] less than nums[i]
            for (int j = i - 1; j >= 0 && j > indexToReplace; j--) {
                if (nums[j] < nums[i]) {
                    if (j > indexToReplace) {
                        indexToReplace = j;
                        indexToReplaceHigh = i;
                    }
                }
            }
        }
        if (indexToReplace != -1) {
            // swap indexToReplace and indexToReplaceHigh
            int tmp = nums[indexToReplace];
            nums[indexToReplace] = nums[indexToReplaceHigh];
            nums[indexToReplaceHigh] = tmp;
        }
        // Sort nums from indexToReplace + 1 in ascending order.
        Arrays.sort(nums, indexToReplace + 1, nums.length);
    }

    private int[] int2array(int nn) {
        long l = 1;
        int digits = 0;
        int n = nn;

        while (n != 0) {
            l = l * 10;
            n = n / 10;
            digits++;
        }
        int ret[] = new int[digits];
        l = l / 10;
        n = nn;
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (int) (n / l);
            n = n % (int)l;
            l = l / 10;
        }

        return ret;
    }
    private int array2int(int[] array) {
        long ret = 0;
        for (int i = 0; i < array.length; i++) {
            ret = ret * 10 + array[i];
        }
        if (ret > Integer.MAX_VALUE)
            return -1;
        else
            return (int) ret;
    }

    public int nextGreaterElement(int n) {
        int nums[] = int2array(n);
        int swapIndexLow = -1;
        int swapIndexHigh = -1;
        for (int i = nums.length - 1; i > 0 && i > swapIndexLow; i--) {
            for (int j = i - 1; j >= 0 && j > swapIndexLow; j--) {
                // j ... i.
                if (nums[j] < nums[i] && swapIndexLow < j) {
                    swapIndexLow = j;
                    swapIndexHigh = i;
                }
            }
        }

        if (swapIndexLow == -1) {
            return -1;
        } else {
            // swap swapIndexLow and swapIndexHigh
            int tmp = nums[swapIndexLow];
            nums[swapIndexLow] = nums[swapIndexHigh];
            nums[swapIndexHigh] = tmp;
            // sort nums from swapIndexLow + 1.
            Arrays.sort(nums, swapIndexLow + 1, nums.length);
            return array2int(nums);
        }
    }

    public boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>();
        seen.add(1);
        while (!seen.contains(n)) {
            //System.out.println(n);
            seen.add(n);

            int a[] = int2array(n);
            int sum = 0;
            for (int i = 0; i < a.length; i++) {
                sum += a[i] * a[i];
            }
            n = sum;
        }
        return n == 1;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i))
                    return false;
            } else {
                if (map.containsValue(t.charAt(i)))
                    return false;
                map.put(s.charAt(i), t.charAt(i));
            }
        }

        return true;
    }

    public boolean wordPattern(String pattern, String str) {
        String[] array = str.split(" ");
        if (pattern.length() != array.length)
            return false;

        HashSet<String> set = new HashSet<>();
        String[] map = new String[26];
        for (int i = 0; i < pattern.length(); i++) {
            if (map[pattern.charAt(i) - 'a'] != null) {
                if (!map[pattern.charAt(i) - 'a'].equals(array[i]))
                    return false;
            } else {
                if (set.contains(array[i]))
                    return false;
                map[pattern.charAt(i) - 'a'] = array[i];
                set.add(array[i]);
            }
        }
        return true;
    }

    HashMap<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        if (map.containsKey(input))
            return map.get(input);

        List<Integer> ret = new ArrayList<Integer>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));

                for (Integer l : left) {
                    for (Integer r : right) {
                        switch (c) {
                            case '+':
                                ret.add(l + r); break;
                            case '-':
                                ret.add(l - r); break;
                            case '*':
                                ret.add(l * r); break;
                        }
                    }
                }
            }
        }

        if (ret.isEmpty()) {
            ret.add(Integer.valueOf(input));
        }

        map.put(input, ret);

        return ret;
    }

    public boolean isAnagram(String s, String t) {
        int buckets[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            buckets[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            buckets[t.charAt(i) - 'a']--;
            if (buckets[t.charAt(i) - 'a'] < 0)
                return false;
        }

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != 0)
                return false;
        }
        return true;
    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        for (; i < citations.length; i++) {
            if (citations[citations.length - i - 1] < (i + 1))
                break;
        }
        return i;
    }

    static private boolean isFibonacci(long x) {
        long v = 5 * x * x + 4;
        long sqrt = (long) Math.sqrt(v);
        if (v == sqrt * sqrt)
            return true;
        v = 5 * x * x - 4;
        sqrt = (long) Math.sqrt(v);
        if (v == sqrt * sqrt)
            return true;

        return false;
    }

    @Test
    public void test() {
        /*
        long N = 4000000L; // 4 million

        long pre = 0;
        long cur = 1;
        long result = 0;
        for (int i = 0; i < 35; i++) {
            if (pre % 2== 0) {
                System.out.print(pre + "(" + i + ") ");
                result += pre;
            }
            long tmp = pre + cur;
            pre = cur;
            cur = tmp;
        }
        System.out.println(result);

        //System.out.println((long) Math.sqrt(Long.MAX_VALUE));

        long f_x = 0; // greatest fibonacci number less than N.
        long f_x_plus_1 = 0;

        for (f_x = N - 1; f_x > 0; f_x--) {
            if (isFibonacci(f_x))
                break;
        }
        for (f_x_plus_1 = N; true; f_x_plus_1++) {
            if (isFibonacci(f_x_plus_1))
                break;
        }

        System.out.println();
        System.out.println("Fx " + f_x + ", Fx+1 " + f_x_plus_1);
        System.out.println("1 Result is " + (f_x + f_x_plus_1 - 1)/2);
         */

        long N = 600851475143L;
        long largestPrimeFactor = 0;
        for (long l = 2; l <= N; l++) {
            while (N % l == 0) {
                System.out.println(l +"   " + N);
                N = N / l;
                if (l > largestPrimeFactor)
                    largestPrimeFactor = l;
            }
        }
        System.out.println(largestPrimeFactor);
        /*
        int array[] = new int[] {99, 76, 59, 600, 366, 13, 20, 786, 79, 21, 3000, 12, 11, 9, 200};
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
         */

        //NextPermutation np = new NextPermutation();
        //System.out.println(np.hIndex(new int[]{3,0,6,1,1}));
        //System.out.println(np.wordPattern("abba", "dog cat cat fish"));
        //System.out.println(np.isIsomorphic("axd", "egg"));
        //System.out.println(np.isHappy(18));
        //System.out.println(np.nextGreaterElement(1999999999));
        //np.nextPermutation(a);
        /*
        int a[] = np.int2array(1999999999);
        for (int i : a) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println(np.array2int(a));
         */
    }
}
