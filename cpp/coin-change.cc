#include <vector>
#include <algorithm>
#include <iostream>

class Solution {
public:
  int coinChange (std::vector<int>& coins, int amount) {
    /* 'dp[i]' is the solution for amount 'i'.  */
    std::vector<int> dp (amount + 1, -1);

    dp[0] = 0;

    for (int i = 1; i <= amount; i++)
      for (auto &c : coins)
	{
	  /* Given the amount 'i', we have a coin 'c'.  */
	  if (i >= c && dp[i - c] != -1)
	    {
	      if (dp[i] == -1)
		dp[i] = dp[i - c] + 1;
	      else
		dp[i] = std::min (dp[i], dp[i - c] + 1);
	    }
	}

    return dp[amount];
  }
};

int
main (void)
{
  Solution s;

  /*
  std::vector<int> coins = { 1, 2, 5 };

  std::cout << s.coinChange (coins, 11) << std::endl;
*/
  std::vector<int> coins = { 429,171,485,26,381,31,290 };

  std::cout << s.coinChange (coins, 8440) << std::endl;
  return 0;
}
