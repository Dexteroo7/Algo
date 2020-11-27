import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/merge-k-sorted-lists
class MergeKSortedLinkedLists {
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

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0)
            return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        for (ListNode list : lists) {
            if (list != null)
                queue.add(list);
        }

        if (queue.size() == 0)
            return null;

        ListNode toReturn = new ListNode();
        ListNode current = toReturn;

        ListNode smallest = queue.poll();
        current.val = smallest.val;
        smallest = smallest.next;
        if (smallest != null)
            queue.add(smallest);

        while (!queue.isEmpty()) {

            smallest = queue.poll();
            current.next = new ListNode();
            current = current.next;
            current.val = smallest.val;
            smallest = smallest.next;
            if (smallest != null)
                queue.add(smallest);
        }

        return toReturn;
    }
}
