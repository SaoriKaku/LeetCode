
/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
*/

class Solution {
    public boolean isValid(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(!map.containsKey(curr)) {
                stack.push(curr);
            }
            else {
                if(stack.peek() != map.get(curr)) {
                    return false;
                }
                else {
                    stack.pop();
                }
            }
        }
        
        return stack.isEmpty();
    }
}