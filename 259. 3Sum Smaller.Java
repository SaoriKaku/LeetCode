
/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:
Input: nums = [-2,0,1,3], and target = 2
Output: 2 
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?
*/


// method 1: sort + two pointers
// time complexity: O(nlogn + n^2), space complexity: O(1)
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if(nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int n = nums.length, result = 0;
        for(int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum < target) {
                    result += right - left;
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        return result;
    }
}




