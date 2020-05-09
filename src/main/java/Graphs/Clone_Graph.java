package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clone_Graph {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        return cloneGraph(node, new HashMap<>());
    }

    public Node cloneGraph(Node current, Map<Integer, Node> nodes) {

        if (current == null)
            return null;

        Node fromCache = nodes.get(current.val);
        if (fromCache != null)
            return fromCache;

        Node cloned = new Node(current.val);
        nodes.put(current.val, cloned);

        if (current.neighbors != null) {
            for (Node neighbor : current.neighbors) {
                cloned.neighbors.add(cloneGraph(neighbor, nodes));
            }
        }
        return cloned;
    }
}
