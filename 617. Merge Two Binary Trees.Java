
/*
Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:

Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
 

Note: The merging process must start from the root nodes of both trees.
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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) {
            return t2;
        }
        if(t2 == null) {
            return t1;
        }
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[] {t1, t2});
        
        while(!queue.isEmpty()) {
            TreeNode[] curr = queue.poll();
            if(curr[0] == null || curr[1] == null) {
                continue;
            }
            curr[0].val = curr[0].val + curr[1].val;
            if(curr[0].left == null) {
                curr[0].left = curr[1].left;
            }
            else {
                queue.offer(new TreeNode[] {curr[0].left, curr[1].left});
            }
            if(curr[0].right == null) {
                curr[0].right = curr[1].right;
            }
            else {
                queue.offer(new TreeNode[] {curr[0].right, curr[1].right});
            } 
        }
        return t1;
    }
}



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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // 先在当前层做点什么
        if(t1 == null) {
            return t2;
        }
        if(t2 == null) {
            return t1;
        }
        t1.val = t1.val = t2.val;
        // 如何往下走，左边的分支，右边的分支
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        // 在t1这一层对t1.left, t1.right做些什么，触底之后怎么反弹
        return t1;
    }
}






