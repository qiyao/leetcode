package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class ReorderList {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            next = null;
        }
    }



    private ListNode findMiddle(ListNode head) {
        ListNode oneStep = head;
        ListNode twoStep = head;
        while(twoStep != null) {
            twoStep = twoStep.next;
            if (twoStep != null) {
                twoStep = twoStep.next;
                oneStep = oneStep.next;
            }
        }
        return oneStep;
    }

    public void reorderList(ListNode head) {
        ListNode middle = findMiddle(head);
        //System.out.println("middle is " + middle.val);
        if (head == middle) {
            // Only one element
            ;
        } else {
            // Remove the 2nd half from list.
            ListNode cur = head;
            ListNode preCur = null;
            while (cur != middle) {
                preCur = cur;
                cur = cur.next;
            }
            if (preCur != null) {
                preCur.next = null;
                //System.out.println("set preCur.next " + preCur.val);
            }

            // Push the 2nd half of the list stack.
            Stack<ListNode> stack = new Stack<>();
            for (ListNode nn = middle; nn != null; nn = nn.next) {
                //System.out.println("push " + nn.val);
                stack.push(nn);
            }
            // Iterate the 1st half.
            ListNode nn = head;
            ListNode last = null;
            for (;nn != null; nn = nn.next) {
                // System.out.println("nn " + nn.val);
                last = null;
                last = stack.pop();
                //System.out.println("pop " + last.val);
                // Insert LAST after NN.
                last.next = nn.next;
                nn.next = last;
                nn = last;
            }

            if (!stack.isEmpty()) {
                nn = stack.pop();
                // NN is the last one, insert it after LAST.
                nn.next = null;
                last.next = nn;
            }

        }
    }

    private ListNode build(int[] input) {
        ListNode ret = null;
        ListNode cur = null;
        for (int i = 0; i < input.length; i++) {
            ListNode n = new ListNode(input[i]);
            if (cur == null) {
                ret = n;
                cur = n;
            } else {
                cur.next = n;
                cur = n;
            }
            cur = n;
        }
        return ret;
    }
    @Test
    public void test1() {
        ReorderList rl = new ReorderList();
        int[] a = {1,2,3,4};

        ListNode result = rl.build(a);
        ListNode nn = result;
        assertEquals(1, nn.val);
        nn = nn.next;
        assertEquals(2, nn.val);
        ListNode middle = rl.findMiddle(result);
        assertEquals(3, middle.val);
        rl.reorderList(result);
        // [1,4,2,3]
        assertEquals(1, result.val);
        result = result.next;
        assertEquals(4, result.val);
        result = result.next;
        assertEquals(2, result.val);
        result = result.next;
        assertEquals(3, result.val);
        result = result.next;
        assertNull(result);


        System.out.println("xxxx");
        int[] b = {1,2,3,4, 5};
        result = rl.build(b);
        rl.reorderList(result);
        // [1,5,2,4,3]
        assertEquals(1, result.val);
        result = result.next;
        assertEquals(5, result.val);
        result = result.next;
        assertEquals(2, result.val);
        result = result.next;
        assertEquals(4, result.val);
        result = result.next;
        assertEquals(3, result.val);
        result = result.next;
        assertNull(result);
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int ret = 0;
        // Key is the sum of nums1 and nums2, and value is the frequency.
        HashMap<Integer, Integer> pairCountBySum = new HashMap<>();
        for (int var1 : nums1) {
            for (int var2 : nums2) {
                int sum = var1 + var2;
                pairCountBySum.put(sum, pairCountBySum.getOrDefault(sum, 0) + 1);
            }
        }

        for (int var3 : nums3)
            for(int var4: nums4) {

                ret += pairCountBySum.getOrDefault(-(var3+var4), 0);
            }
        return ret;
    }

    @Test
    public void test4() {
        ReorderList rl = new ReorderList();
        assertEquals(2, rl.fourSumCount(new int[]{1,2}, new int[]{-2,-1}, new int[]{-1,2}, new int[]{0,2}));
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Set<Integer> excludedIndex = new HashSet<>();
        HashMap<Integer, Integer> valueFreq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueFreq.put(nums[i], valueFreq.getOrDefault(nums[i], 0) + 1);
            int freq = valueFreq.get(nums[i]);
            if (freq > 4)
                excludedIndex.add(i);
        }

        if (nums == null || nums.length < 4)
            return ret;
        // Key is the sum of nums[i] and nums[j], and value is a set
        // of i + j * nums.length, and j < i;
        Map<Integer, Set<Integer>> twoSumIndices = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {
            if (excludedIndex.contains(i))
                continue;
            for (int j = 0; j < i; j++) {
                if (excludedIndex.contains(j))
                    continue;
                int sum = nums[i] + nums[j];
                Set<Integer> s = twoSumIndices.get(sum);
                if (s == null){
                    s = new HashSet<>();
                    twoSumIndices.put(sum, s);
                }
                s.add(i * nums.length + j);
            }
        }
        //System.out.println(twoSumIndices);
        Set<Long> resultHash = new HashSet<>();
        for (int i = 1; i < nums.length; i++) {
            if (excludedIndex.contains(i))
                continue;
            for (int j = 0; j < i; j++) {
                if (excludedIndex.contains(j))
                    continue;
                int sum = nums[i] + nums[j];
                Set<Integer> s = twoSumIndices.get(target - sum);
                if (s != null) {
                    for (Integer preSum : s) {
                        int pi = preSum / nums.length;
                        int pj = preSum % nums.length;
                        // j < i < pj < pi

                        if (i < pj) {
                            //System.out.println(i + ", " + j + ", " + pi + ", " + pj);
                            int result[] = new int[4];
                            result[0] = nums[j];
                            result[1] = nums[i];
                            result[2] = nums[pj];
                            result[3] = nums[pi];
                            Arrays.sort(result);
                            long hash = 0;
                            for (int k = 0; k < 4; k++) {
                                hash += result[k] + hash * nums.length;
                            }
                            if (!resultHash.contains(hash)) {
                                resultHash.add(hash);
                                ret.add(Arrays.asList(nums[j], nums[i], nums[pj], nums[pi]));
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    @Test
    public void test5() {
        ReorderList rl = new ReorderList();
        List<List<Integer>> r = rl.fourSum(new int[] {1,0,-1,0,-2,2}, 0);
        assertEquals(3 , r.size());

        r = rl.fourSum(new int[] {2, 2, 2, 2 ,2, 2, 2, 2, 2 ,2}, 8);
        assertEquals(1 , r.size());

        r = rl.fourSum(new int[] {2, 2, 2}, 6);
        assertEquals(0 , r.size());
    }

    /**
     *
     * @param index
     * @param buf
     * @param stack, of left parenthesis
     * @param ret
     */
    private  void generateParenthesis(int left, int right, char buf[], List<String> ret) {
        System.out.println(left + ", " + right);
        if ((left == buf.length / 2) && (right == buf.length / 2)) {
            ret.add(new String(buf));
            //System.out.println(buf);
        } else {
            if (left < buf.length / 2) {
                buf[left + right] = '(';
                generateParenthesis(left + 1, right, buf, ret);
            }

            if (left > right) {
                buf[left + right] = ')';
                generateParenthesis(left, right + 1, buf, ret);
            }
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        char[] buf = new char[n * 2];
        Stack<Character> stack = new Stack<>();
        generateParenthesis(0, 0, buf, ret);
        return ret;
    }

    @Test
    public void test6() {
        ReorderList rl = new ReorderList();
        List<String> l = rl.generateParenthesis(3);
        System.out.println(l);
    }

    private void letterCombinations(char[] digits, int index, char[]buf, List<String> result) {
        if (index == digits.length) {
            result.add(new String(buf));
        } else {
            char digit = digits[index];
            int length = (digit == '7' || digit == 9) ? 4 : 3;
            char fistLetter = (char) ((digit - '2') * 3 + ((digit > '8') ? 1:0) + 'a');

            for (int i = 0; i < length; i++) {
                buf[index] = (char) (fistLetter + i);
                letterCombinations(digits, index + 1, buf, result);
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        if (digits.length() == 0)
            return ret;
        char[] buf = new char[digits.length()];
        letterCombinations(digits.toCharArray(), 0, buf, ret);
        return ret;
    }

    @Test
    public void test9 () {
        ReorderList rl = new ReorderList();
        System.out.println(rl.letterCombinations("23"));
    }


    private int findMinIndex(ListNode[] curr) {
        int ret = -1;
        for (int i = 0; i < curr.length; i++) {
            if (curr[i] != null) {
                if (ret == -1 || curr[i].val < curr[ret].val)
                    ret = i;
            }
        }
        // System.out.println("find min index " + ret);
        return ret;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode[] curr = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++)
            curr[i] = lists[i];

        ListNode headNode = null;
        ListNode currNode = null;

        int index = findMinIndex(curr);
        while (index != -1) {

            ListNode n = curr[index];
            // Insert curr[index] to head.
            if (headNode == null) {
                headNode = n;
            }
            if (currNode != null) {
                currNode.next = n;
            }

            // Move curr[index] forward.
            curr[index] = n.next;

            currNode = n;
            currNode.next = null;

            index = findMinIndex(curr);
        };
        return headNode;
    }

    @Test
    public void test10() {
        ReorderList rl = new ReorderList();
        ListNode[] lns = new ListNode[3];
        lns[0] = new ListNode(1);
        lns[0].next = new ListNode(4);
        lns[0].next.next = new ListNode(5);
        lns[1] = new ListNode(1);
        lns[1].next = new ListNode(3);
        lns[1].next.next = new ListNode(4);
        lns[2] = new ListNode(2);
        lns[2].next = new ListNode(6);
        ListNode ret = rl.mergeKLists(lns);
        assertEquals(1, ret.val);
        ret = ret.next;
        assertEquals(1, ret.val);
        ret = ret.next;
        assertEquals(2, ret.val);
        ret = ret.next;
        assertEquals(3, ret.val);
        ret = ret.next;
        assertEquals(4, ret.val);
        ret = ret.next;
        assertEquals(4, ret.val);

        lns = new ListNode[0];
        ret = rl.mergeKLists(lns);
        assertNull(ret);

        lns = new ListNode[1];
        lns[0] = null;
        ret = rl.mergeKLists(lns);
        assertNull(ret);
    }

    public long minimalKSum(int[] nums, int k) {
        Set<Long> set = new HashSet<>();
        long ret = 0;
        for (int i : nums)
            set.add((long) i);
        for (long i = 0, j = 1; i < k; j++) {
            if (!set.contains(j)) {
                ret += j;
                i++;
            }
        }
        return ret;
    }

    @Test
    public void test11() {
        ReorderList rl = new ReorderList();
        assertEquals(5, rl.minimalKSum(new int[] {1,4,25,10,25}, 2));
    }
}
