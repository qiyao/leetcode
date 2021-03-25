package leetcode;

import org.junit.Test;

import java.util.*;

public class QuickSelect {
    // K-th largest number.
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    int quickSelect(int[] nums, int low, int high, int k) {
        int pivot = low;

        // PIVOT = nums[high]
        // nums [low, pivot) <= PIVOT
        // nums [pivot, high) > PIVOT
        // nums [high] == PIVOT
        // [LOW,      ,PIVOT,       ,HIGH]
        // <----------><----------->
        for (int j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, pivot, j);
                pivot++;
            }
        }
        swap(nums, pivot, high);
        // [LOW,      ,HIGH,       ,PIVOT]
        // <----------><----------->
        // <---------------><----------->

        int count = high - pivot + 1;

        if (count == k)
            return nums[pivot];
        if (count > k)
            return quickSelect(nums, pivot + 1, high, k);
        // pivot is too big, it must be on the left.
        return quickSelect(nums, low, pivot - 1, k - count);
    }


    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    // Top K frequent words.
    class FrequentComparator implements Comparator<Map.Entry<String, Integer>>{
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (!o1.getValue().equals(o2.getValue()))
                return o1.getValue().compareTo(o2.getValue());
            else
                return o2.getKey().compareTo(o1.getKey());
        }
    };

    private void swap(Map.Entry<String, Integer> [] nums, int i, int j) {
        Map.Entry<String, Integer> t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    void quickSelect(Map.Entry<String, Integer> [] nums, int low, int high, int k) {
        int pivotIndex = low;
        Map.Entry<String, Integer> pivot = nums[high];

        for (int j = low; j < high; j++) {
            if ((nums[j].getValue() < pivot.getValue())
                    || (nums[j].getValue().equals(pivot.getValue()) && nums[j].getKey().compareTo(pivot.getKey()) > 0)) {
                System.out.print(nums[j].getKey());
                System.out.println(nums[j].getValue());
                swap(nums, j, pivotIndex);
                pivotIndex++;
            }
        }
        swap(nums, pivotIndex, high);

        int count = high - pivotIndex + 1;
        if (count == k)
            return;
        else if (count > k)
            quickSelect(nums, pivotIndex + 1, high, k);
        else
            quickSelect(nums, low, pivotIndex - 1, k - count);
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        Map.Entry<String, Integer> array[] = (Map.Entry<String, Integer> []) map.entrySet().toArray(new Map.Entry[map.size()]);
        map.entrySet().toArray();
        // O(N * logK)
        quickSelect(array, 0, array.length - 1, k);

        Arrays.sort(array, array.length - k, array.length, new FrequentComparator());

        List<String> ret = new ArrayList<>();
        for (int i = 0; i <k ; i++) {
            ret.add(array[array.length - 1 - i].getKey());
        }

        return ret;
    }

    @Test
    public void test() {
        QuickSelect cc = new QuickSelect();
        //int array[] = new int[] {3,2,3,1,2,4,5,5,6};
        //System.out.println(cc.findKthLargest(array, 4));
        String input[] = new String[]{"i", "love", "leetcode", "i", "love", "coding", "coding"};
        for (String s : cc.topKFrequent(input, 1))
            System.out.println(s);
    }
}
