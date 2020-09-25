class RemoveNthNodeFromEnd {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode headActual = head;

        ListNode current = head;

        int currentCount = 0;

        ListNode possibleToRemove = current;
        ListNode possibleToRemovePrev = null;

        while (current.next != null) {

            current = current.next;
            currentCount++;
            if (currentCount >= n) {
                //start moving toRemove
                possibleToRemovePrev = possibleToRemove;
                possibleToRemove = possibleToRemove.next;
            }
        }

        //first node
        if (possibleToRemovePrev == null)
            headActual = possibleToRemove.next;
        else
            possibleToRemovePrev.next = possibleToRemove.next;

        return headActual;
    }
}
