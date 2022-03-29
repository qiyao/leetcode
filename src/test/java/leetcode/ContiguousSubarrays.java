package leetcode;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;




public class ContiguousSubarrays {

    class Node {
        int data;
        Node next;
        Node(int x) {
            data = x;
            next = null;
        }
    }

    Node reverse(Node head) {
        // Write your code here

        // Add an odd number before HEAD.
        Node newHead = new Node(1);
        newHead.next = head;
        // The previous odd node.
        Node preOdd = newHead;
        Node preCur = newHead;
        Node cur = head;
        while (cur != null) {
            if (cur.data % 2 != 0) {
                // odd number.
                preCur = cur;
                preOdd = cur;

                cur = cur.next;
            } else {
                // even number.
                if (preOdd == preCur) {
                    preCur = cur;
                    cur = cur.next;
                }
                else {
                    // preOdd -> ... preCur -> cur -> X
                    preCur.next = cur.next;
                    Node X = cur.next;
                    // preOdd -> ... preCur -> X
                    cur.next = preOdd.next;
                    //    cur ->
                    preOdd.next = cur;
                    // preOdd -> cur ... preCur -> X
                    cur = X;
                }
            }
        }
        return newHead.next;
    }

    int[] countSubarrays(int[] arr) {
        // Write your code here
        int[] result = new int[arr.length];
        //for (int i = 0; i < )
        return result;
    }

    boolean isBalanced(String s) {
        // Write your code here
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    char cc = stack.pop();
                    if (cc != '(')
                        return false;
                    break;
                case '}':
                    cc = stack.pop();
                    if (cc != '{')
                        return false;
                    break;
                case ']':
                    cc = stack.pop();
                    if (cc != '[')
                        return false;
                    break;
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void test() {
        ContiguousSubarrays cs = new ContiguousSubarrays();
        int[] test_1 = {3, 4, 1, 6, 2};
        int[] output_1 = countSubarrays(test_1);
        assertEquals(1, output_1[0]);
        assertEquals(3, output_1[1]);
        assertEquals(1, output_1[2]);
        assertEquals(5, output_1[3]);
        assertEquals(1, output_1[4]);
    }

    Node createLinkedList(int[] arr) {
        Node head = null;
        Node tempHead = head;
        for (int v : arr) {
            if (head == null) {
                head = new Node(v);
                tempHead = head;
            } else {
                head.next = new Node(v);
                head = head.next;
            }
        }
        return tempHead;
    }

    @Test
    public void test2() {
        ContiguousSubarrays cs = new ContiguousSubarrays();
        int[] arr_1 = {1, 2, 8, 9, 12, 16};
        int[] expected1 = {1, 8, 2, 9, 16, 12};
        Node n1 = cs.reverse(cs.createLinkedList(arr_1));
        assertEquals(1, n1.data);
        n1 = n1.next;
        assertEquals(8, n1.data);
        n1 = n1.next;
        assertEquals(2, n1.data);
        n1 = n1.next;
        assertEquals(9, n1.data);
        n1 = n1.next;
        assertEquals(16, n1.data);
        n1 = n1.next;
        assertEquals(12, n1.data);
    }
}
