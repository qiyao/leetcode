package leetcode;

import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class RemoveDuplicatedLetters {
    public String removeDuplicateLetters(String s) {
        boolean visited[] = new boolean[26];
        Stack<Character> stack = new Stack<>();
        int count[] = new int[26]; // count the number of the occurence of each char.
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']--;
            if (visited[c - 'a'])
                continue;

            while (!stack.isEmpty() && c < stack.peek() && count[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            visited[c-'a'] = true;


        }
        StringBuffer sb = new StringBuffer();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Test
    public void test() {
        RemoveDuplicatedLetters r = new RemoveDuplicatedLetters();
        assertEquals("abc", r.removeDuplicateLetters("bcabc"));
        assertEquals("acdb", r.removeDuplicateLetters("cbacdcbc"));
    }
}
