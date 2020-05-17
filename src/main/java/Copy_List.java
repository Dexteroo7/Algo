import java.util.IdentityHashMap;
import java.util.Map;

class Copy_List {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {

        if (head == null)
            return null;

        Map<Node, Node> nodeMap = new IdentityHashMap<>();

        //go over once
        Node clonedHead = new Node(head.val);
        Node clonedCurrent = clonedHead;
        nodeMap.put(head, clonedCurrent);
        Node current = head.next;
        while (current != null) {
            clonedCurrent.next = new Node(current.val);
            clonedCurrent = clonedCurrent.next;
            nodeMap.put(current, clonedCurrent);
            current = current.next;
        }

        current = head;
        clonedCurrent = clonedHead;
        while (current != null) {

            clonedCurrent.random = nodeMap.get(current.random);
            current = current.next;
            clonedCurrent = clonedCurrent.next;
        }

        return clonedHead;
    }
}
