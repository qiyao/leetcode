#include <string>
#include <iostream>

class Solution {
public:
  std::string longestPalindrome(std::string s) {
    int max_length = 0;
    int max_index = 0;

    if (s.size() == 0)
      return s;

    for (int i = 0; i < s.size(); i++)
    {
      int length = 0;

      /* abcba, i = 2, */
      for (length = 1;
	   ((i + length) < s.size()) && (i - length >= 0) && s[i + length] == s[i - length];
	   length++)
	;
      length--;

      if ((length * 2 + 1)> max_length)
	{
	  max_length = length * 2 + 1;
	  max_index = i;
	}

      /* abccba, i = 2 */
      for (length = 0;
	   ((i + length + 1) <= s.size()) && (i - length >= 0) && s[i + length + 1] == s[i - length];
	   length++)
	;
      length--;

      if ((length + 1) * 2 > max_length)
	{
	  max_length = (length + 1) * 2;
	  max_index = i;
	}
    }

    if (max_length % 2 == 0)
      return s.substr(max_index - (max_length / 2 - 1), max_length);
    else
      return s.substr(max_index - max_length / 2, max_length);
  }
};

int
main (void)
{
  Solution s;

  std::cout << s.longestPalindrome("") << std::endl;
}
