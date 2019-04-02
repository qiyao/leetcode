#include<vector>
#include <iostream>

using namespace std;

class R
{
public:
  int _num;
  int _count;


  R(int num, int count) : _num(num), _count(count)
  {}
};

class Solution {
public:
  vector<vector<int>> threeSum(vector<int>& nums) {
      vector<vector<int>> ret;

      std::sort(nums.begin(), nums.end());

      for (int i = 0; i < nums.size(); i++)
	{
	  int target = -nums[i];

	  int front = i + 1;
	  int back = nums.size() - 1;

	  while (front < back)
	    {
	      int sum = nums[front] + nums[back];

	      if (sum < target)
		front++;
	      else if (sum > target)
		back--;
	      else
		{
		  vector<int> triplet(3, 0);
		  triplet[0] = nums[i];
		  triplet[1] = nums[front];
		  triplet[2] = nums[back];

		  ret.push_back(triplet);

		  while (front < back && nums[front] == triplet[1]) front++;

		  while (front < back && nums[back] == triplet[2]) back--;
		}
	    }

	  while (i + 1 < nums.size() && nums[i+1] == nums[i])
	    i++;
	}

      return ret;
    }
};

int main()
{
  vector<int> v;

  //-1, 0, 1, 2, -1, -4
  // -4, -1, -1, 0, 1, 2,


  v.push_back(-1);
  v.push_back(0);
  v.push_back(1);
  v.push_back(2);
  v.push_back(-1);
  v.push_back(-4);


  Solution s;

  vector<vector<int>> ret = s.threeSum(v);

  for (auto& it1 : ret) {
    for (auto& it2 : it1) {
      std::cout << it2 << " ";
    }
    std::cout << std::endl;
  }

  return 0;
}
