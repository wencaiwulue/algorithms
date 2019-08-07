package algorithm;

/**
 * @author fengcaiwen
 * @since 5/28/2019
 */
public class ReverseTest {
    public static void main(String[] args) throws InterruptedException {

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(4);
        ListNode node = reverse(l2);
        System.out.println(node.value);

    }

    private static ListNode reverse(ListNode current) {
        ListNode nextNode = null;
        ListNode previousNode = null;
        while (current != null) {
            nextNode = current.next;
            current.next = previousNode;

            previousNode = current;
            current = nextNode;

        }
        return previousNode;
    }

    public static ListNode dec(ListNode header){
        while (header!=null){

        }
        return null;
    }


//    public static ListNode reverse0(ListNode current) {
//        //initialization
//        ListNode previousNode = null;
//        ListNode nextNode = null;
//
//        while (current != null) {
//            //save the next node
//            nextNode = current.next;
//            //update the isLeaf of "next"
//            current.next = previousNode;
//            //shift the pointers
//            previousNode = current;
//            current = nextNode;
//        }
//        return previousNode;
//    }

    public static ListNode reverseRec(ListNode current)
    {
        if (current == null || current.next == null) return current;
        ListNode nextNode = current.next;
        current.next = null;
        ListNode reverseRest = reverse(nextNode);
        nextNode.next = current;
        return reverseRest;
    }


    public static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }


}
