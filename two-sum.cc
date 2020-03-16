#include<vector>

class Solution {
public:
  std::vector<int> twoSum(std::vector<int>& nums, int target) {
    for (int i = 0; i < nums.size(); i++)
      for (int j = i + 1; j < nums.size(); j++)
	{
	  if (nums[i] + nums[j] == target)
	    return std::vector<int>({i, j});
	}
    return std::vector<int>();
  }
};

#include <iostream>

int
main (void)
{
  Solution s;

  std::vector<int> v;
  v.push_back(2);
  v.push_back(7);
  v.push_back(2);
  v.push_back(15);

  v = s.twoSum(v, 4);

  for (auto vv : v)
    std::cout << vv << " ";

  std::cout << std::endl;
};
