package algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author fengcaiwen
 * @since 9/24/2019
 */
public class SymmetricTree {

    public static void main(String[] args) {
        System.out.println(match(Arrays.asList(4, null, null, 4)));
    }

    public boolean isSymmetric(TreeNode root) {
        return false;
    }

    public boolean test(List<TreeNode> nodes) {
        List<TreeNode> list = new ArrayList<>(nodes.size() * 2);
        List<Integer> ints = new ArrayList<>(nodes.size() * 2);
        for (TreeNode node : nodes) {
            if (node.left != null) {
                list.add(node.left);
                ints.add(node.left.val);
            }
            if (node.right != null) {
                list.add(node.right);
                ints.add(node.right.val);
            }
        }

        match(ints);
        return test(list);
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
