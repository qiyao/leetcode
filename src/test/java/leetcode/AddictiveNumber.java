package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddictiveNumber {
    public boolean isAdditiveNumber(String num) {
        // Three parts, 0-i, i+1 - j, j+1 - N.
        for (int i = 0; i < num.length()/2 + 1; i++) {
            if (num.charAt(0) == '0' && i > 0) // A doesn't start with '0'.
                continue;

            long A = Long.valueOf(num.substring(0, i + 1));
            for (int j = i + 1; j <= (num.length() - i - 2) && j <= (num.length() - 1 + i)/2; j++) {
                if (num.charAt(i+1) == '0' && j > i+1) // B doesn't start with '0'.
                    continue;

                long B = Long.valueOf(num.substring(i+1, j+1));
                if (isAdditiveNumber(num.substring(j+1), A, B))
                    return true;
            }
        }

        return false;
    }

    protected boolean isAdditiveNumber(String num, long A, long B) {
        if (num.equals("")) return true;

        String sum = Long.toString(A + B);
        if (num.startsWith(sum)) {
            return isAdditiveNumber(num.substring(sum.length()), B, A + B);
        } else {
            return false;
        }
    }

    @Test
    public void test(){
        AddictiveNumber an = new AddictiveNumber();
        assertTrue(an.isAdditiveNumber("112358"));
        assertTrue(an.isAdditiveNumber("199100199"));
        assertFalse(an.isAdditiveNumber("0235813"));
        assertFalse(an.isAdditiveNumber("1"));
        assertTrue(an.isAdditiveNumber("101"));
        assertTrue(an.isAdditiveNumber("00000"));
    }
}
