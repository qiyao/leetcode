package leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class AddDigits
{
	public int addDigits(int num) {
		/*
		while(num >= 10) {
			num = (num/10) + num%10;
		}
		return num;
		*/
		if (num == 0)
			return num;

		if (num % 9 == 0)
			return 9;
		else
			return num%9; 
    }
	
	public int countPrimes(int n) {
		boolean notPrime[] = new boolean[n];
		
		// Assume every number is prime number.
		for (int i = 0; i < n; i++)
			notPrime[i] = false;

        int count = 0;
		for (int i = 2; i < n; i++) {
			if (!notPrime[i]) {
				count++;
				// If i is a prime number, i * j is NOT prime number.
				for (int j = 1; i * j < n; j++) {
					notPrime[i*j] = true;
				}
			}
		}
		return count;
    }
	
	public boolean isUgly(int num) {
		
		if (num == 0)
			return false;
		
		while (num != 1) {
			if (num % 2 == 0) {
				num = num / 2;
				continue;
			}
			if (num % 3 == 0) {
				num = num / 3;
				continue;
			}
			
			if (num % 5 == 0) {
				num = num / 5;
				continue;
			}
			
			break;
		}
        
		return num == 1;
    }
	
	
    public int nthUglyNumber(int n) {
    	int dp[] = new int[n+1]; // dp[i] holds the 's ugly number.
    	
    	dp[1] = 1;
    	int p2=1, p3=1, p5=1;
    	for (int i = 2; i < n+1; i++) {
    		dp[i] = Math.min(2*dp[p2], Math.min(3*dp[p3], 5*dp[p5]));
    		if (dp[i]==2*dp[p2]) p2++;
    		if (dp[i]==3*dp[p3]) p3++;
    		if (dp[i]==5*dp[p5]) p5++;
    	}
    	return dp[n];
    }

    @Test
	public void test()
	{
		AddDigits a = new AddDigits();
		System.out.println(a.nthUglyNumber(1352));
	}

}
