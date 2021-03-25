package leetcode;

import org.junit.Test;

import java.util.*;

class TrieNode {
    TrieNode children[];
    boolean isLeaf;

    public TrieNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        children = new TrieNode[26];
    }

    public void addWordFromDict(String dic) {
        TrieNode n = this;
        for (int i = 0; i < dic.length(); i++) {
            TrieNode child = n.children[dic.charAt(i) - 'a'];
            if (child == null) {
                child = new TrieNode(i == dic.length() - 1);
                n.children[dic.charAt(i) - 'a'] = child;
            }
            if (!child.isLeaf)
                child.isLeaf = (i == dic.length() - 1);
            n = child;
        }
    }
};

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.size() == 0)
            return false;

        /*
        TrieNode root = new TrieNode(false);
        for (String dict : wordDict)
            root.addWordFromDict(dict);
         */
        Set<String> dict = new HashSet<>();
        for (String d : wordDict)
            dict.add(d);

        boolean dp[] = new boolean[s.length() + 1];
        dp[s.length()] = true;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j <= s.length(); j++) {
                // s[i,j) is found in dict.
                if (dict.contains(s.substring(i, j)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0];
        //return match(s, 0, root, root, 0);
    }

    boolean match(String s, int index, TrieNode node, TrieNode root, int level) {
        for (int i = 0; i < level; i++)
            System.out.print(" ");
        System.out.println("index " + index + " level " + level);
        if (index == s.length()) {
            return node.isLeaf;
        }

        if (node.isLeaf) {
            if (match(s, index, root, root, 0))
                return true;
        }

        TrieNode child = node.children[s.charAt(index) - 'a'];
        return child == null ? false : match(s, index + 1, child, root, level+1);
    }


        public int romanToInt(String s) {
            int ret = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // s has i+1.
                char cNext = 0;
                if (i+1 < s.length())
                    cNext = s.charAt(i+1);

                if (c == 'I') {
                    if (cNext == 'V') {
                        ret +=4;
                        i++;
                    } else if (cNext == 'X') {
                        ret += 9;
                        i++;
                    } else {
                        ret +=1;
                    }
                } else if (c == 'V')
                    ret +=5;
                else if (c == 'X') {
                    if (cNext == 'L') {
                        ret += 40;
                        i++;
                    } else if (cNext == 'C') {
                        ret += 90;
                        i++;
                    } else {
                        ret += 10;
                    }
                } else if (c == 'L')
                    ret += 50;
                else if (c == 'C') {
                    if (cNext == 'D') {
                        ret += 400;
                        i++;
                    } else if (cNext == 'M') {
                        ret += 900;
                        i++;
                    } else {
                        ret += 100;
                    }
                } else if (c == 'D')
                    ret += 500;
                else if (c == 'M')
                    ret += 1000;
            }
            return ret;
        }

        public String intToRoman(int num) {
            StringBuffer sb = new StringBuffer();
            int numThousands = num / 1000;
            for (int i = 0; i < numThousands; i++)
                sb.append('M');
            num = num % 1000;
            // hundred.
            int j = num / 100;
            if (j == 4)
                sb.append("CD");
            else if (j == 9)
                sb.append("CM");
            else if (j < 4) {
                for (int i = 0; i < j; i++)
                    sb.append('C');
            } else {
                // 5 <= j < 9
                sb.append("D");
                for (int i = 5; i < j; i++)
                    sb.append('C');
            }

            // ten.
            num = num % 100;
            j = num / 10;
            if (j == 4)
                sb.append("XL");
            else if (j == 9)
                sb.append("XC");
            else if (j < 4) {
                for (int i = 0; i < j; i++)
                    sb.append('X');
            } else {
                // 5 <= j < 9
                sb.append("L");
                for (int i = 5; i < j; i++)
                    sb.append('X');
            }

            // one.
            num = num % 10;
            j = num;
            if (j == 4)
                sb.append("IV");
            else if (j == 9)
                sb.append("IX");
            else if (j < 4) {
                for (int i = 0; i < j; i++)
                    sb.append('I');
            } else {
                // 5 <= j < 9
                sb.append("V");
                for (int i = 5; i < j; i++)
                    sb.append('I');
            }

            return sb.toString();
        }

        // Concatenated Words
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Set<String> dict = new HashSet<String>();
            for (String w : words) {
                if (w.length() > 0)
                    dict.add(w);
            }
            List<String> l = new ArrayList<>();
            for (String w : words) {
                if (w.length() > 1 && isConcatenatedInDict(w, dict))
                    l.add(w);
            }
            return  l;
        }

        private boolean isConcatenatedInDict (String w, Set<String> dict) {
            // dp[i] is true when w[i...end] are from dict.
            boolean dp[] = new boolean[w.length() + 1];
            dp[w.length()] = true;

            for (int i = w.length() - 1; i >= 0; i--) {
                for (int j = i; j <= w.length(); j++) {
                    if (dp[j]
                            && (i != 0 || j != w.length()) // dict contains w.
                            && dict.contains(w.substring(i, j)) /* w[i,j) is found in dict.  */) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[0];
        }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Key is the element from NUMS, value is last index.
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer index = map.get(nums[i]);
                if (i - index <= k)
                    return true;
                else
                    map.put(nums[i], i);
            } else {
                map.put(nums[i], i);
            }
        }
        return false;

    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length == 0 || k < 1 || t < 0)
            return false;
        Map<Integer, Integer> bucket = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int bucketId = bucketize(nums[i], t);

            if (bucket.containsKey(bucketId)
            || (bucket.containsKey(bucketId - 1) && ((long) nums[i] - (long) bucket.get(bucketId - 1) <= t))
            || (bucket.containsKey(bucketId + 1) && ((long) bucket.get(bucketId + 1) - (long) nums[i]) <= t))
                return true;

            if (i >= k) {
                int firstId = bucketize(nums[i - k], t);
                bucket.remove(firstId);
            }
            bucket.put(bucketId, nums[i]);
        }
        return false;
    }

    private int bucketize(int value, int t) {
        if (value >= 0)
            return value / (t+1);
        else
            return (value / (t+1)) - 1;
    }
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int i = 0; i < nums1.length; i++)
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);

        for (int i = 0; i < nums2.length; i++)
            map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);

        Set<Integer> s = map1.keySet();
        s.retainAll(map2.keySet());
        Map<Integer, Integer> map3 = new HashMap<>();
        int count = 0;
        for (Integer i : s) {
            int vs = Math.min(map1.get(i), map2.get(i));
            map3.put(i, vs);
            count += vs;
        }
        int result[] = new int[count];
        int k = 0;
        for (Integer i : map3.keySet()) {
            for (int j = 0; j < map3.get(i); j++) {
                result[k++] = i;
            }
        }

        return result;
    }

    public void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroIndex = i;
                int nonZeroIndex = -1;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        nonZeroIndex = j;
                        break;
                    }
                }
                if (nonZeroIndex != -1) {
                    // swap nums[zeroIndex] and nums[nonZeroIndex].
                    int tmp = nums[zeroIndex];
                    nums[zeroIndex] = nums[nonZeroIndex];
                    nums[nonZeroIndex] = tmp;
                }
            }
        }
    }

    @Test
    public void test() {
        WordBreak wb = new WordBreak();
        List<String> dict = new ArrayList<>();
        dict.add("aa");
        dict.add("aaaa");
        //dict.add("b");
        //dict.add("cd");
        //dict.add("and");
        //System.out.println(wb.wordBreak("aaaaaaa", dict));
        //System.out.println(wb.intToRoman(1994));
        /*
        List<String> l = wb.findAllConcatenatedWordsInADict(new String[]{""});
        for (String s : l)
            System.out.println("(" + s + ")");
         */

        //System.out.println(wb.containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
        int nums[] = new int[]{0, 0, 0};
        wb.moveZeroes(nums);
        for (int i : nums) {
            System.out.print(i + ", ");
        }
    }
}
