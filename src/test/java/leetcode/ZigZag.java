package leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

class ZigZag1 extends ZigZag {
	private int index2Row(int i, int numRows) {
		/*
		0         2(N-1)
		1
		2
		.  N-2
		N-1       3(N-1)
		*/
		int l = i / (numRows - 1);
		if (l % 2 == 0) {
			// On the vertical line.
			return i - (numRows - 1) * l;
		} else {
			return (numRows - 1) - (i - (numRows - 1) * l);
		}
	}

	@Override
	public String convert(String s, int numRows) {
		if (numRows == 1)
			return s;

		StringBuffer array[] = new StringBuffer[numRows];
		for (int i = 0; i < s.length(); i++) {
			int row = index2Row(i, numRows);
			//System.out.println(i + " " + row);
			StringBuffer sb = array[row];
			if (sb == null) {
				sb = new StringBuffer();
				array[row] = sb;
			}
			sb.append(s.charAt(i));
		}
		StringBuffer sb = new StringBuffer();
		for (StringBuffer ssbb : array) {
			if (ssbb != null)
				sb.append(ssbb);
		}
		return sb.toString();
	}
}

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

	@Test
	public void test() {
		ZigZag zigzag = new ZigZag1();
		assertEquals("PAHNAPLSIIGYIR", zigzag.convert("PAYPALISHIRING", 3));
		assertEquals("PINALSIGYAHRPI", zigzag.convert("PAYPALISHIRING", 4));
		assertEquals("A", zigzag.convert("A", 1));
		assertEquals("A", zigzag.convert("A", 2));
	}

}
