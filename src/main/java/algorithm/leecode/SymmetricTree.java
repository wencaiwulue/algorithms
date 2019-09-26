package algorithm.leecode;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 9/24/2019
 */
public class SymmetricTree {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.left = null;
        node.left.right = new TreeNode(3);
        node.right.left = null;
        node.right.right = new TreeNode(3);
        System.out.println(isSymmetric(node));
    }

    /*
    *
    1
   / \
  2   2
 / \ / \
3  4 4  3
*
*
*
*   1
   / \
  2   2
   \   \
   3    3
    *
    * */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
//        return test(Collections.singletonList(root));
        // todo should work
        return isTheSame(preorderReverse(root), suforderReverse(root));
    }

    public static List<Integer> preorderReverse(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            result.add(pop.val);
            if (pop.left != null) {
                stack.push(pop.left);
            } else {
//                result.add(null);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            } else {
//                result.add(null);
            }
        }
        System.out.println(result);
        return result;
    }

    public static List<Integer> suforderReverse(TreeNode node) {
        Queue<TreeNode> stack = new LinkedBlockingQueue<>();
        stack.add(node);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode pop = stack.poll();
            if (pop.left != null) {
                stack.add(pop.left);
            } else {
//                result.add(null);
            }
            if (pop.right != null) {
                stack.add(pop.right);
            } else {
//                result.add(null);
            }
            result.add(pop.val);
        }
        System.out.println(result);
        return result;
    }

    public static boolean isTheSame(List<Integer> list1, List<Integer> list2) {
        if (list1 == null && list2 == null) return true;
        if (list1 == null || list2 == null) return false;
        if (list1.size() != list2.size()) return false;
        boolean b = true;
        for (int i = 0; i < list1.size(); i++) {
            b &= Objects.equals(list1.get(i), list2.get(i));
        }
        return b;
    }

    public static boolean test(List<TreeNode> nodes) {
        if (nodes.isEmpty()) return true;
        List<TreeNode> list = new ArrayList<>(nodes.size() * 2);
        List<Integer> ints = new ArrayList<>(nodes.size() * 2);
        for (TreeNode node : nodes) {
            if (node.left != null) {
                list.add(node.left);
                ints.add(node.left.val);
            } else {
                ints.add(null);
            }
            if (node.right != null) {
                list.add(node.right);
                ints.add(node.right.val);
            } else {
                ints.add(null);
            }
        }
        System.out.println(ints);
        return test(list) && match(ints);
    }

    private static boolean match(List<Integer> list) {
        int size = list.size();
        boolean b = true;
        for (int i = 0, j = size - 1; i < size && j > i; i++, j--) {
            b = b && (Objects.equals(list.get(i), list.get(j)));
        }
        return b;
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
