package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) return true;

        int deltaX = coordinates[1][0] - coordinates[0][0];
        int deltaY = coordinates[1][1] - coordinates[0][1];

        if (deltaX != 0 && deltaY != 0) {
            int xx = gcd(Math.abs(deltaX), Math.abs(deltaY));
            deltaX = deltaX / xx;
            deltaY = deltaY / xx;
        }


        return false;
    }

    private int gcd(int a, int b) {
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }

    @Test
    public void test() {
        StraightLine sl = new StraightLine();
        assertEquals(3, sl.gcd(3, 9));
        assertEquals(6, sl.gcd(48, 18));
    }
}
