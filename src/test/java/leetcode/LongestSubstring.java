package leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LongestSubstring
{	
	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		Set<Character> seen = new HashSet<Character>();
		int result = 0, i = 0, j = 0;
		
		while (i < n && j < n) {
			if (seen.contains(s.charAt(j))) {
				seen.remove(s.charAt(i));
				i++;
			} else {
				seen.add(s.charAt(j));
				j++;
				result = Math.max(result, j - i);
			}
		}
		return result;
	}

	@Test
	public void test()
	{
		LongestSubstring solution = new LongestSubstring();
		assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
		assertEquals(0, solution.lengthOfLongestSubstring(""));
	}

}
