
/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:
Input: [5,2,6,1]
Output: [2,1,1,0] 

Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
*/


// method 1: build tree
// time complexity: O(nlogn + n), space complexity: O(n)
class Solution {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int duplicate;
        int leftNodeCount;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        int n = nums.length;
        result.add(0);
        TreeNode root = new TreeNode(nums[n - 1]);
        root.duplicate++;
        for(int i = n - 2; i >= 0; i--) {
            result.add(insert(root, nums[i]));
        }
        Collections.reverse(result);
        return result;
    }

    public int insert(TreeNode root, int key) {
        int sum = 0;
        while(root.val != key) {
            if(root.val > key) { // go root's left
                if(root.left == null) {
                    root.left = new TreeNode(key);
                }
                root.leftNodeCount++;
                root = root.left;
            }
            else { // go root's right
                if(root.right == null) {
                    root.right = new TreeNode(key);
                }
                sum += root.leftNodeCount + root.duplicate;
                root = root.right;
            }
        }
        root.duplicate++;
        return sum + root.leftNodeCount;
    }
}


// method 2: merge sort
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    class Item {
        int value;
        int index;
        public Item(int index, int value) {
            this.value = value;
            this.index = index;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Item[] items = new Item[nums.length];
        for(int i = 0; i < nums.length; i++) {
            items[i] = new Item(i, nums[i]);
        }
        Item[] helper = new Item[nums.length];
        int[] counts = new int[nums.length];
        mergesort(items, helper, counts, 0, nums.length - 1);
        List<Integer> result = new ArrayList<>();
        for(int count: counts) {
            result.add(count);
        }
        return result;
    }
    public void mergesort(Item[] items, Item[] helper, int[] counts, int left, int right) {
        if(left >= right) return;
        int mid = left + (right - left) / 2;
        mergesort(items, helper, counts, left, mid);
        mergesort(items, helper, counts, mid + 1, right);
        merge(items, helper, counts, left, mid, right);
    }
    public void merge(Item[] items, Item[] helper, int[] counts, int left, int mid, int right) {
        for(int i = left; i <= right; i++) {
            helper[i] = items[i];
        }
        int smallerCount = 0, leftIndex = left, rightIndex = mid + 1;
        while(leftIndex <= mid && rightIndex <= right) {
            if(helper[leftIndex].value <= helper[rightIndex].value) {
                counts[helper[leftIndex].index] += smallerCount;
                items[left++] = helper[leftIndex++];
            }
            else {
                /* 
                有多少right part排在当前left part某个数字的前面，就表明有多少数字比这个数字小
                所以每当右边数字比左边小的时候，count++
                1. 当左边数字小的时候，添加左边数字，更新左边数字的count[i] += curr count
                2. 当右边数字小的时候，添加右边数字，count++
                */
                smallerCount++;
                items[left++] = helper[rightIndex++];
            }
        }
        while(leftIndex <= mid) {
            counts[helper[leftIndex].index] += smallerCount;
            items[left++] = helper[leftIndex++];
        }
    }
}



