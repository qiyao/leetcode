package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class ThreeSumClosest
{	
    public int threeSumClosest(int[] nums, int target) {
    	Arrays.sort(nums);
    	int result = nums[0] + nums[1] + nums[2];
    	for (int x = 0; x < nums.length - 2; x++) {
    		int y = x + 1;
    		int z = nums.length - 1;
    		while (y < z) {
    			int sum = nums[x] + nums[y] + nums[z];
    			if (sum > target)
    				z--;
    			else
    				y++;
    			
    			if (Math.abs(sum - target) < Math.abs(result - target))
    				result = sum;
    		}
    	}
    	return result;
    }
    
    public boolean isValid(String s) {
    	Stack<Character> stack = new Stack<Character>();
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (c == '(' || c == '[' || c == '{')
    			stack.push(c);
    		else {
    			if (stack.isEmpty())
    				return false;
    			char top = stack.pop();
    			if ((c == ')' && top == '(')
    					|| (c == ']' && top == '[')
    					|| (c == '}' && top == '{'))
    				;
    			else
    				return false;
    		}
    	}
        return stack.isEmpty();
    }

	@Test
	public void test()
	{
		ThreeSumClosest s = new ThreeSumClosest();
//		System.out.println(s.threeSumClosest(new int[] {0, 2, 1, -3}, 1));
		System.out.println(s.isValid("["));
	}

}
