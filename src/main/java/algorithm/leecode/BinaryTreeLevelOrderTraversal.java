package algorithm.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 9/23/2019
 */
public class BinaryTreeLevelOrderTraversal {
    /*

                3
               / \
              9  20
                /  \
               15   7
            return its level order traversal as:
            [
              [3],
              [9,20],
              [15,7]
            ]

    */

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        List<List<Integer>> lists = levelOrder(node);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        list.add(Collections.singletonList(root.val));
        recursive(Collections.singletonList(root), list);
        return list;
    }

    private static void recursive(List<TreeNode> node, List<List<Integer>> list) {
        if (node.isEmpty()) return;

        List<TreeNode> l = new ArrayList<>(node.size() * 2);
        List<Integer> ints = new ArrayList<>(node.size() * 2);
        for (TreeNode treeNode : node) {
            if (treeNode != null) {
                if (treeNode.left != null) {
                    l.add(treeNode.left);
                    ints.add(treeNode.left.val);
                }
                if (treeNode.right != null) {
                    l.add(treeNode.right);
                    ints.add(treeNode.right.val);
                }
            }
        }
        if (!ints.isEmpty()) list.add(ints);
        recursive(l, list);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}




