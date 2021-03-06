
/*
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix
Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

Example 1:
Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.

Example 2:
Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

Example 3:
Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

Example 4:
Input: "/a/./b/../../c/"
Output: "/c"

Example 5:
Input: "/a/../../b/../c//.//"
Output: "/c"

Example 6:
Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
*/


// method 1: 
// time complexity: O(n), space complexity: O(n)
class Solution {
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0) {
            return "";
        }
        int i = 0;
        Deque<String> stack = new ArrayDeque<>();
        while(i < path.length()) {
            while(i < path.length() && path.charAt(i) == '/') {
                i++;
            }
            StringBuilder data = new StringBuilder();
            while(i < path.length() && path.charAt(i) != '/') {
                data.append(path.charAt(i));
                i++;
            }
            String name = String.valueOf(data);
            if(name.length() == 0 || name.equals(".")) {
                continue;
            }
            else if(name.equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else { // name != "."
                stack.push(name);
            }
        }
        if(stack.isEmpty()) {
            return "/";
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            StringBuilder curr = new StringBuilder(stack.pop());
            result.append(curr.reverse() + "/");
        }
        return String.valueOf(result.reverse());
    }
}


// method 2: stack
// time complexity: O(n), space complexity: O(n)
class Solution {
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0) {
            return "";
        }
        String[] steps = path.split("/");
        //System.out.println(Arrays.toString(steps));
        Deque<String> stack = new ArrayDeque<>();
        for(int i = 0; i < steps.length; i++) {
            if(steps[i].equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else if(steps[i].equals(".") || steps[i].length() == 0) {
                continue;
            }
            else {
                stack.push(steps[i]);
            }
            //System.out.println(stack);
        }
        if(stack.isEmpty()) return "/";
        String result = "";
        while(!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }
        return result;
    }
}



