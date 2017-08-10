#include <iostream>
#include <vector>
#include <math.h>
#include <limits>

class Solution {
public:
    int numSquares(int n) {
      std::vector<int> solution (n + 1, -1);

      solution[0] = 0;

      for (int i = 1; i <= n; i++)
	for (int sq = 1; sq < sqrt (n) + 1; sq++)
	  if (i >= sq * sq && solution[i - sq * sq] != -1)
	    {
	      if (solution[i] == -1)
		solution[i] = solution[i - sq * sq] + 1;
	      else
		solution[i] = std::min (solution[i], solution[i - sq * sq] + 1);
	    }

      return solution[n];
    }
};


int
main (void)
{
  Solution s;

  std::cout << s.numSquares (13) << std::endl;
}
