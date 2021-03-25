package leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ZigZag
{
	public String convert(String s, int numRows) {
		if (numRows == 1)
			return s;

		StringBuffer sb = new StringBuffer();
		int n = s.length();
		int numColumns = (n - 1) / ((numRows - 1) * 2) + 1;
		
		for (int row = 0; row < numRows; row++) {
			for (int column = 0; column <=numColumns; column++) {
				int index = 0;

				if (row != 0 && row != numRows - 1) {
					index = column * 2 * (numRows - 1) - row;
					if (index < n && index >= 0)
						sb.append(s.charAt(index));
					}

				index = column * 2 * (numRows - 1) + row;
				if (index < n && index >= 0)
					sb.append(s.charAt(index));
			}
		}
		
		return sb.toString();
	}
	
	
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i]))
                return false;
        }
        
        return true;
    }
	@Test
	public void test() {
		ZigZag zigzag = new ZigZag();
		assertEquals("PAHNAPLSIIGYIR", zigzag.convert("PAYPALISHIRING", 3));
		assertEquals("PINALSIGYAHRPI", zigzag.convert("PAYPALISHIRING", 4));
		assertEquals("A", zigzag.convert("A", 1));
	}

}
