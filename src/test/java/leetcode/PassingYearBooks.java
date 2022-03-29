package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PassingYearBooks {

    /**
     * Build cycle from student STUDENT.
     * @param student
     * @param arr
     */
    private Set<Integer> buildCycle(int student, int [] arr) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(student)) {
            set.add(student);
            student = arr[student - 1];
        }
        return set;
    }

    int[] findSignatureCounts(int[] arr) {
        // Write your code here
        int [] result = new int[arr.length];
        Set<Integer> processedStudents = new HashSet<>();
        while (processedStudents.size() < arr.length) {
            for (int i = 0; i < arr.length; i++) {
                if (!processedStudents.contains(i + 1)) {
                    Set<Integer> steps = buildCycle(i + 1, arr);
                    for (Integer x : steps) {
                        result[x - 1] = steps.size();
                    }
                    processedStudents.addAll(steps);
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        PassingYearBooks pyb = new PassingYearBooks();
        int[] arr_1 = {2, 1};
        int[] output_1 = findSignatureCounts(arr_1);
        assertEquals(output_1.length, 2);
        assertEquals(2, output_1[0]);
        assertEquals(2, output_1[0]);

        int[] arr_2 = {1, 2};
        int[] output_2 = findSignatureCounts(arr_2);
        assertEquals(output_2.length, 1);
        assertEquals(1, output_2[0]);
        assertEquals(1, output_2[0]);
    }
}
