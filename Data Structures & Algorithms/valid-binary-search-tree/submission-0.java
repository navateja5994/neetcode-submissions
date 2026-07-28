/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Solution {
    static boolean left_check(int val, int limit) {
        return val < limit;
    }

    static boolean right_check(int val, int limit) {
        return val > limit;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValid(root.left, root.val, Solution::left_check) ||
            !isValid(root.right, root.val, Solution::right_check)) {
            return false;
        }

        return isValidBST(root.left) && isValidBST(root.right);
    }

    public boolean isValid(TreeNode root, int limit, CheckFunction check) {
        if (root == null) {
            return true;
        }
        if (!check.apply(root.val, limit)) {
            return false;
        }
        return isValid(root.left, limit, check) &&
               isValid(root.right, limit, check);
    }

    interface CheckFunction {
        boolean apply(int val, int limit);
    }
}