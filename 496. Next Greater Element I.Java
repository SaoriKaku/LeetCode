
/*
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
*/

// method 1: brute force
// time complexity: O(m * n)
// space complexity: O(m)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[] {};
        }
        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                boolean flag = false;
                if(nums1[i] == nums2[j]) {
                    for(int k = j + 1; k < nums2.length; k++) {
                        if(nums2[k] > nums2[j]) {
                            result[i] = nums2[k];
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) {
                        result[i] = -1;
                    }
                    break;
                }
            }
            
        }
        return result;
    }
}

// method 1: brute force 另一种写法，但是速度更慢
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[] {};
        }
        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            boolean flag = false;
            int j = 0;
            for(j = 0; j < nums2.length; j++) {
                if(nums1[i] == nums2[j]) {
                   flag = true;
                }
                if(flag && nums2[j] > nums1[i]) {
                    result[i] = nums2[j];
                    break;
                }
            }
            if(j == nums2.length) {
                result[i] = -1;
            }   
        }
        return result;
    }
}


/*
method 2: 
We use a stack to help us to solve this problem.
Use a map to record the result temporarily.
Scan nums2 from the right to left:
for a new scanning element i
while stack is not empty & nums2[i] >= stack.peek();
	keep popping elements from the stack
if stack is empty:
	the output of element nums2[i] is -1;
	
else (nums2[i] < stack.peek());
	the output of element nums2[i] is stack.peek()

stack.push(nums2[i]);
*/
// time complexity: O(m + n)
// space complexity: O(m + n)


class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[] {};
        }
        // map<key = number in nums2, value = next greater number in nums2>
        HashMap<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        for(int i = nums2.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums2[i] > stack.peek()) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                map.put(nums2[i], -1);
            }
            // nums2 < stack.peek()
            else {
                map.put(nums2[i], stack.peek());
            }
            stack.push(nums2[i]);
        }
        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }
}


// method 2: optimized monotonic stack
// time complexity: O(m + n), space complexity: O(m + n) 
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0) {
            return nums1;
        }
        // key = currNum, value = right num bigger than currNum
        Map<Integer, Integer> map = new HashMap<>();
        // stack: find the first right num bigger than curr
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < nums2.length; i++) {
            while(!stack.isEmpty() && stack.peek() < nums2[i]) {
                int currNum = stack.pop();
                map.put(currNum, nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}
