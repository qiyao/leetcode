package leetcode;

import org.junit.Test;

public class RotateString
{

    public RotateString()
    {
        // TODO Auto-generated constructor stub
    }
    
    public boolean rotateString(String A, String B) {
        String B2 = B + B;
        return B2.contains(A);
    }

    @Test
    public void test()
    {

    }

}
