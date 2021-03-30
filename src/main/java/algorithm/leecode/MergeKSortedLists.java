package algorithm.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 9/26/2019
 */
public class MergeKSortedLists {

    /*
    Input:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    Output: 1->1->2->3->4->4->5->6

    todo how about trans node to array, then use merge, transfer sorted array to node?
    todo this way is totally complex and not wise
    */
    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(2);

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(1);
        listNode1.next.next = new ListNode(2);
//
//        ListNode listNode2 = new ListNode(2);
//        listNode2.next = new ListNode(6);
        ListNode next = mergeKLists(new ListNode[]{listNode, listNode1});
        assert next != null;
        do {
            System.out.println(next.val);
            next = next.next;
        } while (next != null);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        List<Integer> ints = new ArrayList<>(lists.length);
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode list = lists[i];
            if (list==null) continue;
            ints.add(list.val);
            if (!hashMap.containsKey(list.val)) {
                hashMap.put(list.val, new ArrayList<>());
            }
            hashMap.get(list.val).add(i);
        }
        Collections.sort(ints);
        if (ints.isEmpty()) return null;
        int min = ints.get(0);
        if (min == Integer.MAX_VALUE) return null;

        ListNode root = new ListNode(min);
        ListNode temp = root;
        while (min != Integer.MAX_VALUE) {
            temp.next = new ListNode(min);
            List<Integer> should = hashMap.get(min);
            if (should == null) System.out.println(hashMap + "——" + min);
            if (should.size() > 1) {
                for (int i1 = 0; i1 < should.size(); i1++) {
                    Integer i = should.get(i1);
                    if (lists[i].next == null) lists[i].next = new ListNode(Integer.MAX_VALUE);
                    lists[i] = lists[i].next;
                    System.out.println(i1+"_"+lists[i].val);
                    ints.set(i1, lists[i].val);
                    if (!hashMap.containsKey(lists[i].val)) {
                        hashMap.put(lists[i].val, new ArrayList<>());
                    }
                    hashMap.get(lists[i].val).add(i1);
                }
                for (int i = 0; i < should.size(); i++) {
                    temp.next = new ListNode(min);
                    temp = temp.next;
                }
                Collections.sort(ints);
                min = ints.get(0);
//                continue;

            } else {
                int i = should.get(0);
                if (lists[i].next == null) lists[i].next = new ListNode(Integer.MAX_VALUE);
                lists[i] = lists[i].next;
                int val = lists[i].val;
                ints.set(0, val);
                if (!hashMap.containsKey(val)) {
                    hashMap.put(val, new ArrayList<>());
                }
                hashMap.get(val).add(i);
                Collections.sort(ints);
                min = ints.get(0);
                temp = temp.next;
            }
        }
        return root.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
