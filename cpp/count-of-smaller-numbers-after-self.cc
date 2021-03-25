#include <iostream>
#include <vector>

class Solution {
public:

  class TreeNode
  {
  public:
    TreeNode (int val_)
      : val (val_)
    {}

    TreeNode *left = NULL;
    TreeNode *right = NULL;

    int val;

    int count = 1;
  };

  std::vector<int> countSmaller(std::vector<int>& nums) {
    std::vector<int> ret;

    if (nums.size () == 0)
      return ret;

    ret.resize (nums.size ());

    if (nums.size () == 1)
      return ret;

    TreeNode root (nums.back ());

    for (int i = nums.size () - 2; i >= 0; i--)
      ret[i] = insertTreeNode (&root, nums[i], 0);

    return ret;
  }

  int
  insertTreeNode (TreeNode *node, int val, int retCount)
  {
    if (val <= node->val)
      {
	node->count++;
	if (node->left == NULL)
	  node->left = new TreeNode (val);
	else
	  return insertTreeNode (node->left, val, retCount);
      }
    else
      {
	if (node->right == NULL)
	  {
	    node->right = new TreeNode (val);
	    retCount += node->count;
	  }
	else
	  return insertTreeNode (node->right, val, retCount + node->count);
      }

    return retCount;
  }

};


int
main (void)
{
  Solution s;

  std::vector<int> v = { 5, 2, 1, 2 };

  for (auto & r : s.countSmaller (v))
    std::cout << r << " ";

  std::cout << std::endl;
  return 0;
}
