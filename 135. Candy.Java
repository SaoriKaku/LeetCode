
/*
There are N children standing in a line. Each child is assigned a rating value.
You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:
Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2:
Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.
*/


// method 1: array
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0) {
            return 0;
        }
        int n = ratings.length, sum = 0;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);
        // from left to right, check [i-1] < [i]
        for(int i = 1; i < n; i++) {
            if(ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1] + 1;
            }
        }
        // from right to left, check [i] > [i+1] 
        for(int i = n - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) {
                candy[i] = Math.max(candy[i], candy[i+1] + 1);
            }
        }
        for(int curr: candy) {
            sum += curr;
        }
        return sum;
    }
}




