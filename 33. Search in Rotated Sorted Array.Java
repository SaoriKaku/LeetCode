
/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/


// method 1: binary search
// time complexity: O(logn), space complexity: O(1)
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[left] < nums[mid]) { // left part is sorted
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            else { // right part is sorted
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        if(nums[left] == target) return left;
        if(nums[right] == target) return right;
        return -1;
    }
}






