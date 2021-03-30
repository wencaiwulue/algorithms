package algorithm.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 9/24/2019
 */
public class FlattenBinaryTreeToLinkedList {

    /*
    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
    */

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(5);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(4);
        node.right.right = new TreeNode(6);
        flatten(node);
        System.out.println(node);
        System.out.println(iterator(node));

    }

    private static void flatten(TreeNode root) {
        if (root == null) return;
        List<Integer> list = new ArrayList<>();
        recursive(root, list);
        if (list.isEmpty()) {
            root.left = null;
            root.right = null;
        } else {
            TreeNode temp = root;
            // jump the first
            for (int i = 1; i < list.size(); i++) {
                temp.right = new TreeNode(list.get(i));
                temp.left = null;
                temp = temp.right;
            }
        }
    }

    private static List<Integer> iterator(TreeNode node) {
        if (node == null) return Collections.emptyList();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
        return list;
    }

    private static void recursive(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        if (node.left != null) recursive(node.left, list);
        if (node.right != null) recursive(node.right, list);
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
