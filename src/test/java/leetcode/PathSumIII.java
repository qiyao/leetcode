package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


public class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;

        Map<TreeNode, Integer> preSum = new HashMap<>();
        buildPreSum(root, preSum, 0);

        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Set<TreeNode>> seenPaths = new HashMap<>();
        return forEachPath(root, stack, preSum, sum, seenPaths);
    }

    private void buildPreSum(TreeNode node, Map<TreeNode, Integer> preSum, int sum) {
        if (node == null) return;


        sum += node.val;
        preSum.put(node, sum);
        buildPreSum(node.left, preSum, sum);
        buildPreSum(node.right, preSum, sum);
    }

    private int forEachPath(TreeNode node, Stack<TreeNode> stack, Map<TreeNode, Integer> preSum, int sum, Map<TreeNode, Set<TreeNode>> seenPaths) {
        if (node == null) return 0;

        stack.push(node);
        int ret = 0;
        if (node.left == null && node.right == null) {
            for (int i = 0; i < stack.size(); i++) {
                for (int j = i; j < stack.size(); j++) {
                    if (preSum.get(stack.get(j)) - preSum.get(stack.get(i)) + stack.get(i).val == sum) {
                        //System.out.println(stack.get(i).val + "(" + i + ")" + " -> " + stack.get(j).val + "(" + j);
                        Set<TreeNode> set = seenPaths.get(stack.get(i));
                        if (set == null) {
                            set = new HashSet<>();
                            seenPaths.put(stack.get(i), set);
                        }
                        if (!set.contains(stack.get(j))) {
                            set.add(stack.get(j));
                            ret++;
                        }
                    }
                }
            }
        } else {
            ret += forEachPath(node.left, stack, preSum, sum, seenPaths);
            ret += forEachPath(node.right, stack, preSum, sum, seenPaths);
        }
        stack.pop();

        return ret;
    }

    private TreeNode build(Integer input[]) {
        TreeNode nodes[] = new TreeNode[input.length];
        nodes[0] = new TreeNode(input[0]);
        for (int i = 0; i < input.length; i++) {
            int leftIndex = 2*i+1;
            if (leftIndex < input.length && input[leftIndex] != null) {
                nodes[leftIndex] = new TreeNode(input[leftIndex]);
                nodes[i].left = nodes[leftIndex];
            }

            int rightIndex = 2*i+2;
            if (rightIndex < input.length && input[rightIndex] != null) {
                nodes[rightIndex] = new TreeNode(input[rightIndex]);
                nodes[i].right = nodes[rightIndex];
            }
        }
        return nodes[0];
    }

    @Test
    public void test() {
        PathSumIII ps = new PathSumIII();
        assertEquals(3, ps.pathSum(build(new Integer[]{10,5,-3,3,2,null,11,3,-2,null,1}),8));
        assertEquals(1, ps.pathSum(build(new Integer[]{1,-2,-3,1,3,-2,null,-1}), 3));
    }
}
