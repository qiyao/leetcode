package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MinimumHeightTrees {
    public void foo () {
        Map<Character, Integer> unique1 = null;
        char c = 'c';
        unique1.getOrDefault(c, null);
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> ret = new ArrayList<>();
            ret.add(0);
            return ret;
        }
        Map<Integer, Set<Integer>> edgesMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            edgesMap.put(i, new HashSet<>());
        }
        for (int i = 0; i < edges.length; i++) {
            Set<Integer> set = edgesMap.get(edges[i][0]);
            set.add(edges[i][1]);

            set = edgesMap.get(edges[i][1]);
            set.add(edges[i][0]);
        }

        Set<Integer> leaves = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (edgesMap.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n = n - leaves.size();

            Set<Integer> newLeaves = new HashSet<>();
            for (Integer leaf : leaves) {
                // [j, leaf] is an edge.
                Set<Integer> set = edgesMap.get(leaf);
                int j = set.iterator().next();

                edgesMap.remove(leaf);
                set = edgesMap.get(j);
                set.remove(leaf);

                if (set.size() == 1) {
                    newLeaves.add(j);
                }
            }
            leaves = newLeaves;
        }

        return new ArrayList<>(leaves);
    }

    @Test
    public void test() {
        MinimumHeightTrees mht = new MinimumHeightTrees();
        List<Integer> ret = mht.findMinHeightTrees(4, new int[][]{
                {1,0},
                {1,2},
                {1,3}
        });
        assertEquals(1, ret.size());
        assertEquals(Integer.valueOf(1), ret.get(0));

        ret = mht.findMinHeightTrees(6, new int[][]{
                {3,0},{3,1},{3,2},{3,4},{5,4}
        });
        assertEquals(2, ret.size());
        assertEquals(Integer.valueOf(3), ret.get(0));
        assertEquals(Integer.valueOf(4), ret.get(1));

        ret = mht.findMinHeightTrees(2, new int[][]{{0,1}});
        assertEquals(2, ret.size());
        assertEquals(Integer.valueOf(0), ret.get(0));
        assertEquals(Integer.valueOf(1), ret.get(1));

        ret = mht.findMinHeightTrees(1, new int[][]{});
        assertEquals(1, ret.size());
        assertEquals(Integer.valueOf(0), ret.get(0));
    }
}
