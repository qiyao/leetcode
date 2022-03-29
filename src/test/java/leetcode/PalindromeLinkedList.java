package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

class ListNode {
     int val;
     ListNode next;

     ListNode() {
     }

     ListNode(int val) {
         this.val = val;
     }

     ListNode(int val, ListNode next) {
         this.val = val;
         this.next = next;
     }
 }

public class PalindromeLinkedList {

     protected ListNode reverse(ListNode forward) {
         ListNode head1 = forward;
         ListNode newHead = null;
         while (head1 != null) {
             ListNode next = head1.next;

             head1.next = newHead;
             newHead = head1;

             head1 = next;
         }
         return newHead;
     }

    public boolean isPalindrome(ListNode head) {
        ListNode forward = head;
        ListNode forwardFaster = head;

        while (forwardFaster.next != null
                && forwardFaster.next.next != null) {
            forward = forward.next;
            forwardFaster = forwardFaster.next.next;
        }

        if (forwardFaster.next == null) {
            // Odd number of nodes.  forward is the middle node, move it
            // fowrad one step more.
            forward = forward.next;
        } else {
            // Even number of nodes.
        }

        // Reverse list from forward.  newHead is the head of the reversed list.
        ListNode newHead = reverse(forward);

        // Compare them.
        ListNode n1 = head;
        ListNode n2 = newHead;
        while (n1 != null && n2 != null) {
            if (n1.val != n2.val)
                return false;
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }

    @Test
    public void test() {
        PalindromeLinkedList p = new PalindromeLinkedList();

        ListNode n = new ListNode(1);
        n = new ListNode(2, n);
        n = new ListNode(2, n);
        n = new ListNode(1, n);
        assertTrue(p.isPalindrome(n));

        n = new ListNode(1);
        n = new ListNode(2, n);
        n = new ListNode(1, n);
        assertTrue(p.isPalindrome(n));

        n = new ListNode(1);
        assertTrue(p.isPalindrome(n));
    }
}
