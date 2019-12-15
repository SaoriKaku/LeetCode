
/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/


// method 1: DP 1D
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int[] ways = new int[s.length() + 1];
        //i = s.length(), ways[i] = ways[i-1] + ways[i-2]
        ways[0] = 1;
        ways[1] = s.charAt(0) == '0' ? 0 : 1;
        // if char curr == 0, ways[i] = ways[i-1];
        // else if int(substring(i-1, i+1)) > 26, ways[i] = ways[i-2];
        // else, ways[i] = ways[i-1] + ways[i-2]
        for(int i = 2; i < ways.length; i++) {
            int one = Integer.valueOf(s.substring(i-1, i));
            int two = Integer.valueOf(s.substring(i-2, i));
            //System.out.println("one = " + one + ", two = " + two);
            if(one != 0) {
                ways[i] += ways[i-1];
            }
            if(two >= 10 && two <= 26) {
                ways[i] += ways[i-2];
            }
        }
        //System.out.println(Arrays.toString(ways));
        return ways[ways.length - 1];
    }
}
