#include <string>
#include <iostream>
#include <unordered_map>

class Solution {
public:
  int lengthOfLongestSubstring (std::string s) {
    std::unordered_map<char, int> unique = {};
    int start = 0;
    auto max = unique.size ();

    for (int i = 0; i < s.size (); i++)
      {
	auto got = unique.find (s[i]);

	if (got != unique.end ())
	  {
	    for (auto it = unique.begin(); it != unique.end();)
	      {
		if (it->second <= got->second)
		  it = unique.erase (it);
		else
		  it++;
	      }
	  }
	
	unique.emplace (s[i], i);

	max = std::max (max, unique.size ());
      }
    
    return max;
  }
};

int
main (void)
{
  Solution s;

  std::string s1 ("abcabcbb");
  std::string s2 ("bbbb");
  std::string s3 ("pwwkew");

  std::cout << s.lengthOfLongestSubstring (s1) << std::endl;
  std::cout << s.lengthOfLongestSubstring (s2) << std::endl;
  std::cout << s.lengthOfLongestSubstring (s3) << std::endl;
  return 0;
}  
