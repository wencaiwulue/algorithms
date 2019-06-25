package algorithm;

/**
 * @author fengcaiwen
 * @since 5/28/2019
 */
public class AddTwoNumbers01 {
    public static void main(String[] args) {
//        (2 -> 4 -> 3) + (5 -> 6 -> 4)
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(2);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//        l2.next.next.next = new ListNode(4);

        // calculate
        ListNode result = new ListNode(0);
        ListNode backup = result;


        addTwoNumbers(l1, l2, result);

        while (true) {
            ListNode next = backup;
            if (next != null) {
                System.out.println(next.val);
                backup = next.next;
            } else {
                break;
            }
        }

    }


    private static void addTwoNumbers(ListNode l1, ListNode l2, ListNode result) {

        while (true) {
            int i = l1.val + l2.val;
            if (i > 9) {
                i = i % 10;
                if (l1.next == null) l1.next = new ListNode(0);
                l1.next.val += 1;
            }
            result.val += i;

            // exit
            if (l1.next == null && l2.next == null) {
                break;
            }

            if (l1.next == null) l1.next = new ListNode(0);
            if (l2.next == null) l2.next = new ListNode(0);
            if (result.next == null) result.next = new ListNode(0);
            l1 = l1.next;
            l2 = l2.next;
            result = result.next;
        }
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
