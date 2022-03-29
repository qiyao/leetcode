package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowStart = 0;
        int rowEnd = matrix.length - 1;

        // Looking the row.
        while (rowStart < rowEnd) {
            int row = (rowStart + rowEnd) / 2;
            if (target < matrix[row][0]) {
                rowEnd = row - 1;
            } else if (target > matrix[row][matrix[row].length - 1]) {
                rowStart = row + 1;
            } else {
                rowStart = row;
                rowEnd = row;
            }
        }

        int columnStart = 0;
        int columnEnd = matrix[0].length - 1;

        while(columnStart < columnEnd) {
            int column = (columnStart + columnEnd) / 2;
            if (target < matrix[rowStart][column]) {
                columnEnd = column - 1;
            } else if (target > matrix[rowStart][column]) {
                columnStart = column + 1;
            } else {
                columnStart = column;
                columnEnd = column;
            }
        }
        return (target == matrix[rowStart][columnStart]);
    }

    public boolean searchMatrixII(int[][] matrix, int target) {
        return searchMatrix(matrix, 0, 0, matrix.length -1 , matrix[0].length - 1, target);
    }

    /**
     * [startX, startY] to [endX, endY].
     * (startX, startY)  |
     *                   |      (B)
     *                   |(x, y)
     *______________________________________
     *                   |
     *      (A)          |
     *                   |               (endX, endY)
     */
    private boolean searchMatrix(int[][] matrix, int startX, int startY, int endX, int endY, int target) {
        if (startX > endX || startY > endY) return false;
        if (endY < 0 || endX < 0) return false;
        if (startX >= matrix.length || startY >= matrix[0].length) return false;

        int x = (startX + endX) / 2;
        int y = (startY + endY) / 2;
        if (matrix[x][y] == target)
            return true;
        else if (startX == endX && startY == endY) {
            return false;
        } else {
            if (searchMatrix(matrix, x+1, startY, endX, y - 1, target) || // A
                    searchMatrix(matrix, startX, y+1, x-1, endY, target)) // B
                return true;

            if (matrix[x][y] > target) { // top left
                return searchMatrix(matrix, startX, startY, x, y - 1, target) ||
                        searchMatrix(matrix, startX, y, x - 1, y, target);
            } else {
                // bottom right.
                return searchMatrix(matrix, x, y + 1, endX, endY, target) ||
                        searchMatrix(matrix, x + 1, y, endX, y, target);
            }
        }
    }

    @Test
    public void test() {
        SearchMatrix sm = new SearchMatrix();

        assertFalse(sm.searchMatrixII(new int[][]{{1,3,5,7}, {10,11,16,20},{23,30,34,60}}, 13));
        assertTrue(sm.searchMatrixII(new int[][]{{1}, {2}}, 1));
        assertTrue(sm.searchMatrixII(new int[][]{{1, 3}}, 3));
        assertTrue(sm.searchMatrixII(new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}}, 5));

        assertTrue(sm.searchMatrixII(new int[][]{
                {1,3,5,7,9},
                {2,4,6,8,10},
                {11,13,15,17,19},
                {12,14,16,18,20},
                {21,22,23,24,25}}, 13));


    }
}
