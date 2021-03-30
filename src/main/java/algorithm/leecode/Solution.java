package algorithm.leecode;

/**
 * @author fengcaiwen
 * @since 11/6/2019
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursive(new ArrayList<>(Collections.singletonList(root)), list);
        return list;
    }

    private static void recursive(ArrayList<TreeNode> level, List<Integer> result) {
        if (level.isEmpty()) return;

        ArrayList<TreeNode> list = new ArrayList<>(level.size() * 2);
        Integer last = null;
        for (int i = level.size() - 1; i >= 0; i--) {
            TreeNode treeNode = level.get(i);
            if (treeNode != null) {
                if (last == null) last = treeNode.val;
                if (treeNode.right != null) {
                    list.add(treeNode.right);
                } else if (treeNode.left != null) {
                    list.add(treeNode.left);
                }
            }
        }
        if (last != null) result.add(last);
        recursive(list, result);
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
//        tree.left = new TreeNode(2);
        tree.left = new TreeNode(2);
//        tree.left.right = new TreeNode(5);
//        tree.right.right = new TreeNode(4);
        List<Integer> integers = rightSideView(tree);
        System.out.println(integers);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
