package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumMatrix {

    static class NumMatrix1 {

        // dp[i][j] is the sum from (0,0) to (i,j).
        private int[][] dp;

        public NumMatrix1(int[][] matrix) {
            int row = matrix.length;
            int column = matrix[0].length;

            dp = new int[row][column];

            dp[0][0] = matrix[0][0];
            // init the first row.
            for (int i = 1; i < column; i++) {
                dp[0][i] = dp[0][i-1] + matrix[0][i];
            }
            // init the first column.
            for (int i = 1; i < row; i++) {
                dp[i][0]=dp[i-1][0] + matrix[i][0];
            }
            // init the rest.
            for (int i = 1; i < row; i++){
                for (int j = 1; j < column; j++) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2][col2]
                    - (row1 == 0? 0:dp[row1 - 1][col2])
                    - (col1 == 0? 0 : dp[row2][col1-1])
                    + ((row1 == 0 || col1 == 0) ? 0:dp[row1-1][col1-1]);
        }
    }

    @Test
    public void test() {
        int[][] matrix = new int[][] {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix1 numMatrix = new NumMatrix1(matrix);
        assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
        assertEquals(11, numMatrix.sumRegion(1, 1, 2, 2));
        assertEquals(12, numMatrix.sumRegion(1, 2, 2, 4));
        assertEquals(1, numMatrix.sumRegion(0, 1, 0, 2));

        matrix = new int[][] {{-1}};
        numMatrix = new NumMatrix1(matrix);
        assertEquals(-1, numMatrix.sumRegion(0, 0, 0, 0));
    }
}
