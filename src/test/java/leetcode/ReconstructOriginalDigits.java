package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ReconstructOriginalDigits {
    public String originalDigits(String s) {
        String digit2String[] = new String[] {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine",
        };

        Map<Character, Integer> unique1 = new HashMap<>();
        unique1.put('z', 0);
        unique1.put('w', 2);
        unique1.put('u', 4);
        unique1.put('x', 6);
        unique1.put('g', 8);

        int result[] = new int[10];
        int buckets[] = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            buckets[c - 'a']++;

            Integer num = unique1.getOrDefault(c, null);
            if (num != null) {
                String numString = digit2String[num];
                for (int j = 0; j < numString.length(); j++) {
                    buckets[numString.charAt(j) - 'a']--;
                }
                result[num]++;
            }
        }

        Map<Character, Integer> unique2 = new HashMap<>();
        unique2.put('o', 1); // one
        unique2.put('t', 3); // three
        unique2.put('f', 5); // five
        unique2.put('s', 7); // seven

        for (char c : unique2.keySet()) {
            int val = unique2.get(c);
            result[val] += buckets[c - 'a'];
            if (val == 1 || val == 7) {
                buckets['n' - 'a'] -= buckets[c - 'a'];
            }
        }
        // nine
        result[9] += buckets['n' - 'a'] / 2;

        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i]; j++) {
                ret.append(i);
            }
        }

        return ret.toString();
    }

    @Test
    public void test() {
        ReconstructOriginalDigits r = new ReconstructOriginalDigits();
        assertEquals("012", r.originalDigits("owoztneoer"));
        assertEquals("7", r.originalDigits("esnve"));
        assertEquals("9", r.originalDigits("nnei"));
    }
}
