/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
//https://leetcode.com/problems/intersection-of-two-linked-lists/
public class IntersectionOfLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null)
            return null;

        ListNode lastA = null, lastB = null;

        ListNode currentA = headA;
        ListNode currentB = headB;
        while (true) {

            if (currentA == currentB)
                return currentA;

            if (currentA.next == null) {
                if (lastA == null)
                    lastA = currentA;
                currentA = headB;
            } else
                currentA = currentA.next;


            if (currentB.next == null) {
                if (lastB == null)
                    lastB = currentB;
                currentB = headA;
            } else
                currentB = currentB.next;

            if (lastA != null && lastB != null && lastA != lastB)
                return null;
        }
    }
}
