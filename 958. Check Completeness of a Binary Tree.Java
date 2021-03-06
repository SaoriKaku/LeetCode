
/*
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
 
Note: The tree will have between 1 and 100 nodes.
*/


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
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left == null && curr.right != null) {
                return false;
            }
            if(curr.left == null) {
                break;
            }
            else {
                queue.offer(curr.left);
            }
            if(curr.right == null) {
               break;
            }
            else {
                queue.offer(curr.right);
            }
        }
        
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left != null || curr.right != null) {
                return false;
            }
        }
        return true;
    }
}


// method 2: another version of method 1
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
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            if(currNode.left != null && currNode.right != null) {
                queue.offer(currNode.left);
                queue.offer(currNode.right);
            }
            else if(currNode.right != null) { // left = null, right != null
                return false;
            }
            else if(currNode.left != null) {
                queue.offer(currNode.left);
                break;
            }
            else {
                break;
            }
        }
        while(!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            if(currNode.left != null || currNode.right != null) {
                return false;
            }
        }
        return true;
    }
}



