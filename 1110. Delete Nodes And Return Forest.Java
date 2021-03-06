
/*
Given the root of a binary tree, each node in the tree has a distinct value.
After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
Return the roots of the trees in the remaining forest.  You may return the result in any order.

Example 1:
     1
    / \
   2   3
  / \ / \ 
 4  5 6  7 
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 
Constraints:
The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// method 1: dfs
// time complexity: O(n), space complexity: O(height of tree)
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if(root == null || to_delete.length == 0) {
            return new ArrayList<>();
        }
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> values = new HashSet<>();
        for(int value: to_delete) {
            values.add(value);
        }
        TreeNode newRoot = dfs(root, values, result);
        if(newRoot != null) {
            result.add(root);
        }
        return result;
    }
    public TreeNode dfs(TreeNode root, Set<Integer> values, List<TreeNode> result) {
        if(root == null) {
            return root;
        }
        root.left = dfs(root.left, values, result);
        root.right = dfs(root.right, values, result);
        if(values.contains(root.val)) {
            if(root.left != null) {
                result.add(root.left);
            }
            if(root.right != null) {
                result.add(root.right);
            }
            return null;
        }
        return root;
    }
}





