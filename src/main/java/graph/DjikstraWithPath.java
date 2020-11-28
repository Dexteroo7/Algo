package graph;

import java.util.*;

class DjikstraWithPath {

    private static final Map<String, List<String>> WORD_GRAPH = new HashMap<>();
    private static final Map<String, List<List<String>>> WORD_RELS = new HashMap<>();
    private static final Map<String, Integer> NODE_DIST = new HashMap<>();
    private static final PriorityQueue<String> DJIKSTRA_QUEUE = new PriorityQueue<>(Comparator.comparingInt(x -> NODE_DIST.getOrDefault(x, Integer.MAX_VALUE)));
    private static final Set<String> VISITED_NODES = new HashSet<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (beginWord.equals(endWord))
            return Collections.emptyList();

        WORD_GRAPH.clear();
        NODE_DIST.clear();
        DJIKSTRA_QUEUE.clear();
        VISITED_NODES.clear();
        WORD_RELS.clear();

        if (!wordList.contains(beginWord))
            wordList.add(beginWord);
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (distance1(wordList.get(i), wordList.get(j))) {
                    WORD_GRAPH.computeIfAbsent(wordList.get(i), na -> new ArrayList<>()).add(wordList.get(j));
                    WORD_GRAPH.computeIfAbsent(wordList.get(j), na -> new ArrayList<>()).add(wordList.get(i));
                }
            }
        }

        if (!WORD_GRAPH.containsKey(endWord))
            return Collections.emptyList();

        DJIKSTRA_QUEUE.add(beginWord);
        NODE_DIST.put(beginWord, 0);

        while (!DJIKSTRA_QUEUE.isEmpty()) {

            String current = DJIKSTRA_QUEUE.poll();
            if (VISITED_NODES.contains(current))
                continue;
            VISITED_NODES.add(current);

            int currentDistance = NODE_DIST.get(current);
            if (current.equals(endWord))
                continue;

            for (String neighbour : WORD_GRAPH.getOrDefault(current, Collections.emptyList())) {

                int distance = NODE_DIST.computeIfAbsent(neighbour, na -> Integer.MAX_VALUE);
                if (distance > 1 + currentDistance) {

                    WORD_RELS.remove(neighbour);
                    for (List<String> strings : WORD_RELS.getOrDefault(current, defaultRels())) {

                        List<String> newRels = new ArrayList<>(strings);
                        newRels.add(current);
                        WORD_RELS.computeIfAbsent(neighbour, na -> new ArrayList<>()).add(newRels);
                    }

                    NODE_DIST.put(neighbour, 1 + currentDistance);
                    DJIKSTRA_QUEUE.add(neighbour);
                } else if (distance == 1 + currentDistance) {

                    for (List<String> strings : WORD_RELS.getOrDefault(current, defaultRels())) {

                        List<String> newRels = new ArrayList<>(strings);
                        newRels.add(current);
                        WORD_RELS.computeIfAbsent(neighbour, na -> new ArrayList<>()).add(newRels);
                    }
                }
            }
        }

        for (List<String> strings : WORD_RELS.getOrDefault(endWord, Collections.emptyList())) {
            strings.add(endWord);
        }

        return WORD_RELS.get(endWord);
    }

    public List<List<String>> defaultRels() {
        List<List<String>> toReturn = new ArrayList<>();
        toReturn.add(new ArrayList<>());
        return toReturn;
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

    public static void main(String[] args) {

        System.out.println(new DjikstraWithPath().findLadders(
                "a",
                "c",
                new ArrayList<>(Arrays.asList("a", "b", "c"))));
    }
}
