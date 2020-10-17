//https://leetcode.com/problems/linked-list-cycle-ii/
public class LinkedListCycle {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode jump(ListNode current) {
        if (current == null)
            return null;
        return current.next;
    }

    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null)
            return null;

        ListNode jumpOnce = head, jumpTwice = head;
        ListNode cycleNode = null;
        while (true) {

            //keep jumping once and twice
            jumpOnce = jump(jumpOnce);
            jumpTwice = jump(jumpTwice);
            jumpTwice = jump(jumpTwice);

            if (jumpOnce == null || jumpTwice == null)
                break;

            if (jumpOnce == jumpTwice) {
                cycleNode = jumpOnce;
                break;
            }
        }

        if (cycleNode == null)
            return null;

        //we need to find the jump node
        ListNode a = head, b = jumpOnce;
        while (true) {
            if (a == b)
                return a;
            a = a.next;
            b = b.next;
        }
    }
}
