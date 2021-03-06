
/*
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
*/

// method 1: shuffling
// time complexity: O(n), space complexity: O(1)
class Solution {
    
    private int[] array;
    private Random random;

    public Solution(int[] nums) {
        array = nums;
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return array;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] result = Arrays.copyOf(array, array.length);
        // 每次洗牌，洗出随机一张，换到所有还未洗牌的最后一张
        // 每次选择一张牌，洗好的牌就多了一张，未洗的牌就少了一张
        for(int i = result.length - 1; i >= 0; i--) {
            // [0, i]是需要洗牌的index
            // nextInt(i + 1)，洗出的牌是[0, i+1)
            int j = random.nextInt(i + 1);
            swap(result, j, i);
        }
        return result;
    }
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

