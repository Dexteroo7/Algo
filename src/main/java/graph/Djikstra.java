package graph;

import java.util.*;

class Djikstra {

    private static final class Node {

        final String nodeId;
        int destinationFromStart;

        private Node(String nodeId, int destinationFromStart) {
            this.nodeId = nodeId;
            this.destinationFromStart = destinationFromStart;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (beginWord.equals(endWord))
            return 0;

        Map<String, List<String>> wordGraph = new HashMap<>();

        for (int i = 0; i < wordList.size(); i++) {

            if (distance1(beginWord, wordList.get(i))) {
                wordGraph.computeIfAbsent(beginWord, na -> new ArrayList<>()).add(wordList.get(i));
                wordGraph.computeIfAbsent(wordList.get(i), na -> new ArrayList<>()).add(beginWord);
            }

            for (int j = i + 1; j < wordList.size(); j++) {
                if (distance1(wordList.get(i), wordList.get(j))) {
                    wordGraph.computeIfAbsent(wordList.get(i), na -> new ArrayList<>()).add(wordList.get(j));
                    wordGraph.computeIfAbsent(wordList.get(j), na -> new ArrayList<>()).add(wordList.get(i));
                }
            }
        }

        if (!wordGraph.containsKey(endWord))
            return 0;

        Map<String, Node> nodes = new HashMap<>();
        PriorityQueue<Node> djikstraQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x.destinationFromStart));
        Set<String> visitedNodes = new HashSet<>();
        djikstraQueue.add(new Node(beginWord, 0));
        nodes.put(beginWord, new Node(beginWord, 0));

        int minDistance = Integer.MAX_VALUE;
        while (!djikstraQueue.isEmpty()) {

            Node current = djikstraQueue.poll();
            if (visitedNodes.contains(current.nodeId))
                continue;
            visitedNodes.add(current.nodeId);

            if (current.nodeId.equals(endWord))
                minDistance = Integer.min(minDistance, current.destinationFromStart);

            for (String s : wordGraph.getOrDefault(current.nodeId, Collections.emptyList())) {

                Node node = nodes.computeIfAbsent(s, na -> new Node(s, 1 + current.destinationFromStart));
                node.destinationFromStart = Integer.min(node.destinationFromStart, 1 + current.destinationFromStart);
                djikstraQueue.add(node);
            }
        }

        if (minDistance == Integer.MAX_VALUE)
            return 0;

        return minDistance + 1;
    }

    public boolean distance1(String a, String b) {

        boolean diff1 = false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (diff1)
                    return false;
                diff1 = true;
            }
        }

        return diff1;
    }
}
