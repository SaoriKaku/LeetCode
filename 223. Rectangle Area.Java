
/*
Find the total area covered by two rectilinear rectangles in a 2D plane.
Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
Rectangle Area

Example:
Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
Output: 45

Note:
Assume that the total area is never beyond the maximum possible value of int.
*/


// method 1: Math
// time complexity: O(1), space complexity: O(1)
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);
        long width = (long)Math.min(C, G) - (long)Math.max(A, E);
        long height = (long)Math.min(D, H) - (long)Math.max(B, F);
        if(width > 0 && height > 0) {
            return area1 + area2 - (int)(width * height);
        }
        return area1 + area2;
    }
}





