//package algorithm;
//
///**
// * @author fengcaiwen
// * @since 5/28/2019
// */
//public class AddTwoNumbers01_copy {
//    public static void main(String[] args) {
////        (2 -> 4 -> 3) + (5 -> 6 -> 4)
//        ListNode l1 = new ListNode(1);
////        l1.next = new ListNode(9);
////        l1.next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(9);
////        l2.next.next = new ListNode(4);
////        l2.next.next.next = new ListNode(4);
//        ListNode listNode = addTwoNumbers(l1, l2);
//        for (; ; ) {
//            ListNode next = listNode;
//            if (next != null) {
//                System.out.println(next.val);
//                listNode = next.next;
//            } else {
//                break;
//            }
//        }
//
//    }
//
//    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode l11 = l1;
//        ListNode l21 = l2;
//        int whoIsLonger = -1;
//        for (; ; ) {
//            ListNode next1 = l1;
//            ListNode next2 = l2;
//            if (next1 != null || next2 != null) {
//                if (next1 == null) {
//                    whoIsLonger = 2;
//                    l2 = getListNode(l2);
//                } else if (next2 == null) {
//                    whoIsLonger = 1;
//                    l1 = getListNode(l1);
//                } else {
//                    int i = next1.val + next2.val;
//                    boolean need = false;
//                    if (i > 9) {
//                        i = i - 10;
//                        need = true;
//                    }
//                    next1.val = i;
//                    next2.val = i;
//                    ListNode temp1 = l1;
//                    l1 = l1.next;
//                    l2 = l2.next;
//                    if (need) {
//                        if (l1 != null) {
//                            l1.val += 1;
//                        } else if (l2 != null) {
//                            l2.val += 1;
//                        } else {
//                            l1 = new ListNode(1);
//                            temp1.next = l1;
//                        }
//                    }
//                }
//            } else {
//                System.out.println(whoIsLonger == 1 ? l11 : l21);
//                break;
//            }
//        }
//        return whoIsLonger == 1 ? l11 : l21;
//
//    }
//
//    private static ListNode getListNode(ListNode l2) {
//        boolean need = false;
//        if (l2.val > 9) {
//            l2.val -= 10;
//            need = true;
//        }
//        ListNode temp = l2;
//        l2 = l2.next;
//        if (need) {
//            if (l2 != null) {
//                l2.val += 1;
//            } else {
//                l2 = new ListNode(1);
//                temp.next = l2;
//            }
//        }
//        return l2;
//    }
//
//    public static class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int x) {
//            val = x;
//        }
//    }
//}
