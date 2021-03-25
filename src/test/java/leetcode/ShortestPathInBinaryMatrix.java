package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        ShortestPathInBinaryMatrix ff = new ShortestPathInBinaryMatrix();
        ff.hIndex(new int[]{0,1});

    }

    public int hIndex(int[] citations) {

        int i = 0;
        for (i = 0; i < citations.length; i++ ) {
            if (citations[citations.length - 1 - i] < (i + 1))
                break;
        }

        System.out.println(i);
        return i;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m-1][n-1] == 1)
            return -1;

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        int ret = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int []pop = queue.remove();

                if (pop[0] == m-1 && pop[1] == n-1)
                    return ret + 1;

                for (int k=0; k<8; k++) {
                    for (int deltaX = -1; deltaX <= 1; deltaX++) {
                        for (int deltaY = -1; deltaY <=1; deltaY++) {
                            if (deltaX == 0 && deltaY == 0)
                                continue;
                            int nextX = deltaX + pop[0];
                            int nextY = deltaY + pop[1];

                            if (nextX >=0 && nextY >=0 && nextX < m && nextY < n && !visited[nextX][nextY] && grid[nextX][nextY] == 0) {
                                queue.add(new int[]{nextX, nextY});
                                visited[nextX][nextY] = true;
                            }
                        }
                    }


                }
            }
            ret++;
        }
        return ret;
    }
}
