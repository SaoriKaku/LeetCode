
/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        return constructHelper(nums, 0, nums.length - 1);
    }
    public TreeNode constructHelper(int[] nums, int left, int right) {
        if(left > right) {
            return null;
        }
        int max = findMax(nums, left, right);
        TreeNode root = new TreeNode(nums[max]);
        root.left = constructHelper(nums, left, max - 1);
        root.right = constructHelper(nums, max + 1, right);
        return root;
    }
    // find max value in array, return its index
    public int findMax(int[] nums, int left, int right) {
        int index = left;
        int max = nums[index];
        for(int i = left + 1; i <= right; i++) {
            if(nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }
}





