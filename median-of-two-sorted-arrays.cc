#include <vector>
#include <iostream>
#include <limits>

class Solution {
public:
  double findMedianSortedArrays(std::vector<int>& nums1, std::vector<int>& nums2) {
    int total = nums1.size () + nums2.size ();

    if (total % 2 == 0)
      {
	/* Even number */
	return (findKth (total/2, nums1, 0, nums2, 0, 0)
		+ findKth (total/2 + 1, nums1, 0, nums2, 0, 0)) * 0.5;
      }
    else
      {
	/* Odd number */
	return findKth (total/2 + 1, nums1, 0, nums2, 0, 0);
      }
  }

  /* Find the Kth number starting from NUMS1[START1] and NUMS2[START2].
     Both NUMS1 and NUMS2 are in ascending order.  */
  int findKth (int k, std::vector<int>& nums1, int start1,
	       std::vector<int>& nums2, int start2, int depth)
  {
#ifdef DEBUG
    for (int i = 0; i <= depth; i++)
      std::cout << " ";
    std::cout << "k " << k << ", start1 " << start1 << ", start2 " << start2 << " ";
#endif

     if (start1 >= nums1.size ())
       return nums2[start2 + k - 1];
 
    if (start2 >= nums2.size ())
      return nums1[start1 + k - 1];
    
    if (k == 1)
      return std::min (nums1[start1], nums2[start2]);

    int m1 = start1 + k/2 - 1;
    int m2 = start2 + k/2 - 1;
    
    int middle1 = m1 < nums1.size () ? nums1[m1] : std::numeric_limits<int>::max ();
    int middle2 = m2 < nums2.size () ? nums2[m2] : std::numeric_limits<int>::max ();

#ifdef DEBUG
    std::cout << "middle1 " << middle1 << ", middle2 " << middle2;
    std::cout << "" << std::endl;
#endif

    depth++;
    if (middle1 < middle2)
      {
	/* Continue find the right half of NUMS1 and the left half of
	   NUMS2.  */
	return findKth (k - (k)/2, nums1, m1 + 1, nums2, start2, depth);
      }
    else
      {
	/* Continue find the left half of NUMS1 and the right half of
	   NUMS2.  */
	return findKth (k - (k)/2, nums1, start1, nums2, m2 + 1, depth);
      }

  }
};

int
main (void)
{
  Solution s;

  std::vector<int> v1 { 1, 2 };
  std::vector<int> v2 { 3, 4 };
  
  std::cout << std::endl << s.findMedianSortedArrays (v1, v2) << std::endl;
  return 0;
}
