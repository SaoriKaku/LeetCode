
/*
Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.

Example 1:
Input: root = [4,2,5,1,3]
Output: [1,2,3,4,5]

Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

Example 2:
Input: root = [2,1,3]
Output: [1,2,3]

Example 3:
Input: root = []
Output: []
Explanation: Input is an empty tree. Output is also an empty Linked List.

Example 4:
Input: root = [1]
Output: [1]
 
Constraints:
-1000 <= Node.val <= 1000
Node.left.val < Node.val < Node.right.val
All values of Node.val are unique.
0 <= Number of Nodes <= 2000
*/


// method 1: inorder
// time complexity: O(n), space complexity: O(height of tree)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    Node first = null;
    Node last = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return null;
        }
        treeToDoublyListHelper(root);
        first.left = last;
        last.right = first;
        return first;
    }
    public void treeToDoublyListHelper(Node root) {
        if(root == null) {
            return;
        }
        treeToDoublyList(root.left);
        if(first == null) {
            first = root;
        }
        else { // last = prev, root = curr; 2.right = 3; 3.left = 2
            last.right = root;
            root.left = last;
        }
        last = root;
        treeToDoublyList(root.right);
    }
}


// method 2: inorder
// time complexity: O(n), space complexity: O(height of tree)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    Node prev = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return root;
        }
        Node dummy = new Node(0, null, null);
        prev = dummy;
        helper(root);
        dummy.right.left = prev;
        prev.right = dummy.right;
        return dummy.right;
    }
    public void helper(Node root) {
        if(root == null) {
            return;
        }
        helper(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;
        helper(root.right);
    }
}

