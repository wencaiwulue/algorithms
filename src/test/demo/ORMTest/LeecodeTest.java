package demo.ORMTest;

import java.util.*;

import static collections.BitSetTest.isHighOrLow;
import static collections.BitSetTest.setRankReverse;

/**
 * @author fengcaiwen
 * @since 7/18/2019
 */
public class LeecodeTest {

    public static void main(String[] args) {
        int[] ints = new int[]{7, 5, 2, 4, 6, 1, 3};
        int[] test = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] s = new int[]{-3, -25, 20, 20, 1, -3, 6, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
//        maxProfit(ints);
        totalCandy(ints);
        System.out.println(NumberOf1(Integer.MAX_VALUE));

        System.out.println(Integer.toBinaryString(993));
        System.out.println(Integer.toBinaryString(992));
        System.out.println(Integer.toBinaryString(993 & 992));
        System.out.println((12 & 11));
        System.out.println(match("{}{}{}{}]"));
        maxSumOfNumSet(test);
    }


    public static boolean match(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (aChar == '{' || aChar == '(' || aChar == '[') {
                stack.push(aChar);
            } else if (aChar == '}' || aChar == ')' || aChar == ']') {
                if (stack.isEmpty()) return false;
                else stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static int maxProfit(int[] prices) {
        int m = 0;
        int p = 0;
        int l = prices.length - 1;
        for (int i = l; i >= 0; i--) {
            m = prices[i] > m ? prices[i] : m;
            int i1 = m - prices[i];
            p = p > i1 ? p : i1;
        }
        return p;
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

    public static void totalCandy(int[] n) {
        // assume n.length less than a;
        long a = 0;
        int sum = 0;
        Map<Integer, Integer> rankMap = pareOperate(n);
        for (int i = n.length - 1; i >= 0; i--) {
            Integer rank = rankMap.get(n[i]);
            if (isHighOrLow(a, rank) == 0)
                a = setRankReverse(a, rank);
            String s2 = "0" + Long.toBinaryString(a);
            String s = s2.substring(s2.length() - rank);
            System.out.println("s:" + s);
//            String s1 = s.replaceAll("1", "");
//            sum += (s.length() - s1.length());
            if (!s.equals(""))
                sum += NumberOf1(Integer.parseInt(s, 2));
        }
        System.out.println("total: " + sum);
    }

    /**
     * get rank of all elements
     */
    public static Map<Integer, Integer> pareOperate(int[] n) {
        int[] ints = Arrays.stream(n).sorted().toArray();
        Map<Integer, Integer> map = new HashMap<>(n.length);
        for (int i = 0; i < n.length; i++) {
            map.put(ints[i], i);
        }
        System.out.println(map);
        return map;
    }

    public static int NumberOf1(int m) {
        int n = m;
        n = (n & 0x55555555) + ((n >> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >> 16) & 0x0000ffff);
        return n;
    }


    public static int[] maxSumOfNumSet(int[] nums) {
        int[] s = new int[]{-1, 1, -3, 61, -16, -23, 0, -7, 1, -5, -22, 5, -4, 7};
//        int[] s = new int[]{1, -3, 6, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        nums = s;
        int start = 0;
        int lastIsP = 0;
        int pl = 0;
        int nl = 0;
        int ns = 0;
        int ps = 0;
        int total = 0;
        int totalMax = 0;
        int resultStart = 0;
        int resultEnd = 0;
        int leftP = 0;
        int leftPl = 0;
        int leftN = 0;
        int leftNl = 0;
        for (int i = 0; i < nums.length; i++) {
            boolean b = nums[i] > 0;
            if (b) {
                // p -> p
                if (lastIsP == 1) {
                    pl++;
                    ps += nums[i];
                    total += nums[i];
                } else {
                    leftN = ns;
                    // n->p
                    pl = 1;
                    ps = nums[i];
                    total += nums[i];
                }
            } else {
                // from p -> n
                leftP = ps;
                if (lastIsP == 1) {
                    // valuable
                    if ((ps + ns) > 0) {
                        ps = 0;
                        ns = nums[i];
                        pl = 0;
                        nl = 1;
                        total += nums[i];
                    } else {

                        {
                            if (totalMax < (ps + nums[i])) {
                                totalMax = ps + nums[i];
                                resultStart = start;
                                resultEnd = (i - nl - pl);
                            }
                            if (leftP + leftN < 0) {
                                total += -(leftN + lastIsP);
                                resultStart = start;
                                resultEnd = (i - nl - pl);
                            }
                        }

                        // no valuable
                        total = ps + nums[i];
                        start = i - pl;
                        ps = 0;
                        ns = nums[i];
                        pl = 0;
                        nl = 1;
                    }
                    // from n -> n
                } else {
                    nl++;
                    ns += nums[i];
                    total += nums[i];
                }
            }
            lastIsP = b ? 1 : 0;
        }

        System.out.println(totalMax);
        System.out.println(Arrays.toString(nums));
        System.out.println(String.format("from: %s ~ %s", resultStart, resultEnd));
        return null;
    }


}
