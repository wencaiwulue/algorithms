package algorithm;

/**
 * @author fengcaiwen
 * @since 8/5/2019
 */
public class PathSumTest {

    public static void main(String[] args) {

    }

    public static int pathSum(TreeNode root, int sum) {
        int pathNum = 0;
        TreeNode currentL = root;
        TreeNode current = root;
        while (current != null) {
            int sumT = 0;
            reverse(current.left, sumT, sum, pathNum);
            reverse(current.right, sumT, sum, pathNum);
            current = current.left;
        }

        while (currentL != null) {
            int sumT = 0;
            reverse(currentL.left, sumT, sum, pathNum);
            reverse(currentL.right, sumT, sum, pathNum);
            currentL = currentL.right;
        }
        return pathNum;
    }

    public static void reverse(TreeNode child, int sum, int terminal, int counter) {
        if (child != null) {
            sum += child.val;
            if (sum == terminal) {
                counter += 1;
            } else {
                reverse(child.left, sum, terminal, counter);
                reverse(child.right, sum, terminal, counter);
            }
        }

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
