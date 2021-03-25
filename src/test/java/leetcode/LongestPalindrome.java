package leetcode;

import org.junit.Test;

public class LongestPalindrome
{

	public String longestPalindrome(String s) {
		int n = s.length();
		// dp[i][j] is true if s substring from i to j is palindromic.
		boolean dp[][] = new boolean[n][n];
		String ret = null;
		
		for (int i = n -1 ; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = ((s.charAt(i) == s.charAt(j)) && ((i == j) || (j == i + 1) || dp[i+1][j-1]));
				if (dp[i][j]) {
					if (ret == null || (j - i + 1 > ret.length()))
						ret = s.substring(i, j + 1);
				}
			}
		}
		
		if (ret == null)
			ret = new String("");
		return ret;
	}

	@Test
	public void test() {
		LongestPalindrome lp = new LongestPalindrome();
		System.out.println(lp.longestPalindrome(""));
	}
}
