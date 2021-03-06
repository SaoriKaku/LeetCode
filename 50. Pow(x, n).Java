
/*
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:
Input: 2.00000, 10
Output: 1024.00000

Example 2:
Input: 2.10000, 3
Output: 9.26100

Example 3:
Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25

Note:
-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
*/


// method 1: time complexity: O(logn), space complexity: O(logn)
class Solution {
    /*
    -100.0 < x < 100.0
    n is a 32-bit signed integer, within the range [−231, 231 − 1]  
    */
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1.0;
        }
        if(x == 0.0) {
            return 0.0;
        }
        double result = myPowHelper(x, n);
        if(n < 0) {
            return 1 / result;
        }
        return result;
    }
    public double myPowHelper(double x, int n) {
        if(n == 0) {
            return 1.0;
        }
        double half_result = myPowHelper(x, n/2);
        if(n % 2 == 0) {
            return half_result * half_result;
        }
        else {
            return half_result * half_result * x;
        }
    }
}


// method 2: time complexity: O(logn), space complexity: O(1)
class Solution {
    /*
    -100.0 < x < 100.0
    n is a 32-bit signed integer, within the range [−231, 231 − 1]  
    */
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1.0;
        }
        if(x == 0.0) {
            return 0.0;
        }
        long N = n;
        if(n < 0) {
            N = N * -1;
            x = 1 / x;
        }
        double result = 1;
        double curr = x;
        for(long i = N; i > 0; i /= 2) {
            if(i % 2 == 1) {
                result = result * curr;
            }
            curr = curr * curr;
            //System.out.println("i = " + i + ", result = " + result + ", curr = " + curr);
        }
        return result;
    }
}


// method 2: time complexity: O(logn), space complexity: O(1)
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1;
        }
        long N = n;
        if(n < 0) {
            N = -1 * N;
            x = 1 / x;
        }
        /*
        result number power
        1      2      10
        1      4      5
        4      16     2
        4      256    1
        
        result number power
        1      2      5
        2      4      2
        2      16     1
        */
        double result = 1;
        double number = x;
        //System.out.println("N = " + N);
        while(N > 1) {
            if(N % 2 == 1) {
                result *= number;
            }
            number *= number;
            N /= 2;
            //System.out.println("n = " + n + ", result = " + result);
        }
        result *= number;
        return result;
    }
}


