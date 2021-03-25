package leetcode;

import org.junit.Test;

public class Atoi
{
	private int returnValue(long ret) {
		if (ret >= Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else if (ret <= Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		else
			return (int) ret;
	}

	public int myAtoi(String str) {
		long result = 0;
		int i = 0;
		// Skip spaces
		for (; i < str.length() && str.charAt(i) == ' '; i++);
		
		if (i == str.length())
			return 0;
		char c = str.charAt(i);
		if ((c > '9' || c < '0') && c != '+' && c != '-')
			return 0;
		
		boolean negative = false;
		if (c == '+')
			i++;
		if (c == '-') {
			i++;
			negative = true;
		}
		
		if (i == str.length())
			return 0;
		
		for (int j = 0; j + i < str.length(); j++) {
			c = str.charAt(j + i);
			if (c > '9' || c < '0') {
				// non digits.
				if (j == 0)
					return 0;
				else
					return returnValue(negative ? -result:result);
			} else {
				result = result * 10 + c - '0';
			}
			if (!negative && result >= Integer.MAX_VALUE)
				return Integer.MAX_VALUE;
			else if (negative && -result <= Integer.MIN_VALUE)
				return Integer.MIN_VALUE;
		}

		if (result > 0 && negative) {
			result = -1 * result;
		}
		return returnValue(result);
    }
	
    public String longestCommonPrefix(String[] strs) {
    	if (strs.length == 0)
    		return "";
        for (int j = 0; ; j++) {
            for (int i = 0; i < strs.length; i++) {
            	if (j >= strs[i].length())
            		return strs[i];
                if (strs[i].charAt(j) != strs[0].charAt(j)) {
                	if (j == 0)
                		return "";
                	else
                		return strs[0].substring(0, j);
                }
            }
        }
    }

	@Test
	public void test()
	{
		Atoi a = new Atoi();
//		System.out.println(a.myAtoi("+"));
//		System.out.println(a.myAtoi("-"));
//		System.out.println(a.myAtoi("+0"));
//		System.out.println(a.myAtoi("+w"));
//		System.out.println(a.myAtoi("-w"));
//		System.out.println(a.myAtoi("   -001"));
		System.out.println(a.longestCommonPrefix(new String[] {"abc", "abd", "abc"}));
	}

}
