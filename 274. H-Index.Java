
/*
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:
Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.
*/


// method 1: sort 
// time complexity: O(nlogn), space complexity: O(1)
class Solution {
    /*  i starts from 0, value start from max = last index
    index  0  1  2  3  4
    value  0  1  4  5  6
                    i
              l-i
    */
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations, new Comparator<>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        // h papers = i, h citations = citations[last - i], h citations >= h papers
        int i = 0;
        for(int i = 0; i < citations.length; i++) {
            if(citations[i] > i + 1) {
                i++;
            }
        }
        return i;
    }
}





