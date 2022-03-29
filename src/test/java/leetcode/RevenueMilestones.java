package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class RevenueMilestones {
    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        // Write your code here
        HashMap<Integer, Integer> value2Index = new HashMap<>();
        for (int i = 0; i < milestones.length; i++) {
            value2Index.put(milestones[i], i);
        }
        Arrays.sort(milestones);
        int[] ret = new int[milestones.length];
        Arrays.fill(ret, -1);

        int total = 0;
        for (int revenuesIndex = 0, milestonesIndex = 0; revenuesIndex < revenues.length && milestonesIndex < milestones.length; revenuesIndex++) {
            total += revenues[revenuesIndex];
            while (milestonesIndex < milestones.length && total >= milestones[milestonesIndex]) {
                ret[value2Index.get(milestones[milestonesIndex])] = revenuesIndex + 1;
                milestonesIndex++;
            }
        }
        return  ret;
    }

    @Test
    public void test1() {
        RevenueMilestones rm = new RevenueMilestones();
        int[] ret = rm.getMilestoneDays(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}, new int[]{100, 200, 500});
        assertEquals(3, ret.length);
        assertEquals(4, ret[0]);
        assertEquals(6, ret[1]);

        ret = rm.getMilestoneDays(new int[]{100, 200, 300, 400, 500}, new int[]{300, 800, 1000, 1400});
        assertEquals(4, ret.length);
        assertEquals(2, ret[0]);
        assertEquals(4, ret[1]);
        assertEquals(4, ret[2]);
        assertEquals(5, ret[3]);

        ret = rm.getMilestoneDays(new int[]{700, 800, 600, 400, 600, 700}, new int[]{3100, 2200, 800, 2100, 1000});
        assertEquals(5, ret.length);
        assertEquals(5, ret[0]);
        assertEquals(4, ret[1]);
    }


    class Sides{
        int a;
        int b;
        int c;
        Sides(int a,int b,int c){
            int[] array = new int[] {a, b, c};
            Arrays.sort(array);
            this.a = array[0];
            this.b = array[1];
            this.c = array[2];
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Sides))
                return false;
            Sides other = (Sides) o;
            return (a == other.a && b == other.b && c == other.c);
        }

        @Override
        public final int hashCode() {
            return a * 31 + b * 17 + c;
        }
    }

    int countDistinctTriangles(ArrayList<Sides> arr) {
        // Write your code here
        Set<Sides> s = new HashSet<>();
        for (Sides sides : arr) {
            s.add(sides);
        }
        return s.size();
    }

    @Test
    public void test2() {
        RevenueMilestones rm = new RevenueMilestones();
        ArrayList<Sides> sides = new ArrayList<>();
        sides.add(new Sides(7, 6, 5));
        sides.add(new Sides(5, 7, 6));
        sides.add(new Sides(8, 2, 9));
        sides.add(new Sides(2, 3, 4));
        sides.add(new Sides(2, 4, 3));
        assertEquals(3, rm.countDistinctTriangles(sides));
    }

    int numberOfWays(int[] arr, int k) {
        // Write your code here
        HashMap<Integer, Integer> value2Freq = new HashMap<>();
        for (int i : arr) {
            value2Freq.put(i, value2Freq.getOrDefault(i, 0) + 1);
        }
        int ret = 0;
        for (int i : value2Freq.keySet()) {
            if (i == k - i) {
                int n = value2Freq.get(i);
                if (n != 0) {
                    System.out.println(i + ", " + n);
                    ret += n * (n - 1) / 2;
                }
            } else if (i < k - i)  {
                System.out.println(i + " - " + value2Freq.getOrDefault(k - i, 0));
                ret += value2Freq.getOrDefault(k - i, 0);
            }
        }
        return ret;
    }

    @Test
    public void test3() {
        RevenueMilestones rm = new RevenueMilestones();
        //assertEquals(2, rm.numberOfWays(new int[]{1, 2, 3, 4, 3}, 6));

        assertEquals(4, rm.numberOfWays(new int[] {1, 5, 3, 3, 3}, 6));
    }
}
