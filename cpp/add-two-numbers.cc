#include <cstddef>

struct ListNode {
  int val;
  ListNode *next;
  ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
  ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    ListNode *head = NULL;
    ListNode *curr1 = l1;
    ListNode *curr2 = l2;
    ListNode *curr = NULL;
    int add = 0;

    while (curr1 != NULL || curr2 != NULL)
      {
      int x = (curr1 != NULL) ? curr1->val : 0;
      int y = (curr2 != NULL) ? curr2->val : 0;
      int val = add + x + y;

      add = val / 10;

      ListNode *ln = new ListNode (val % 10);

      if (curr == NULL)
	{
	  curr = ln;
	  head = ln;
	}
      else
	{
	  curr->next = ln;
	  curr = ln;
	}

      if (curr1 != NULL)
	curr1 = curr1->next;
      if (curr2 != NULL)
	curr2 = curr2->next;
    }

    if (add != 0)
      {
	ListNode *ln = new ListNode (add);

	curr->next = ln;
	curr = ln;
      }

    return head;
  }
};

#include <iostream>

int
main (void)
{
  Solution s;

  ListNode l11 (3);
  ListNode l12 (0);
  ListNode l13 (3);

  l11.next = &l12;
  l12.next = &l13;

  ListNode l21 (5);
  //ListNode l22 (6);
  //ListNode l23 (4);

  //l21.next = &l22;
  //l22.next = &l23;

  ListNode *l = s.addTwoNumbers (&l11, &l21);

  for (ListNode *ln = l; ln != NULL; ln = ln->next)
    {
      std::cout << ln->val;
    }

  std::cout << "\n";
}
