
/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 
Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/


// method 1: recursive
// time complexity: O(n), space complexity: O(height of tree)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isValidBSTHelper(root, null, null);
    }
    public boolean isValidBSTHelper(TreeNode root, Integer min, Integer max) {
        if(root == null) {
            return true;
        }
        if(max != null && root.val >= max  || min != null && root.val <= min) {
            return false;
        }
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }
}


// method 2: iterative
// time complexity: O(n), space complexity: O(n)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode nextNode = root;
        Integer prevNodeVal = null;
        while(nextNode != null || !stack.isEmpty()) {
            if(nextNode != null) {
                stack.push(nextNode);
                nextNode = nextNode.left;
            }
            else {
                nextNode = stack.pop();
                if(prevNodeVal != null && nextNode.val <= prevNodeVal) {
                    return false;
                }
                //System.out.println("popNode = " + nextNode.val);
                prevNodeVal = nextNode.val;
                nextNode = nextNode.right;
            }
        }
        return true;
    }
}
