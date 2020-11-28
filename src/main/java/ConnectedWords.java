import java.util.*;

//https://leetcode.com/problems/concatenated-words/
class ConnectedWords {

    private static final class Trie {

        private static final class Node {

            final Map<Character, Node> connected = new HashMap<>();
            final char value;
            boolean isWord;

            private Node(char value) {
                this.value = value;
            }

            public void addWord(String word, int index) {

                if (index == word.length()) {
                    this.isWord = true;
                    return;
                }

                char current = word.charAt(index);
                Node node = connected.computeIfAbsent(current, na -> new Node(current));
                node.addWord(word, index + 1);
            }
        }

        final Node root = new Node('\0');

        public void addWord(String word) {

            root.addWord(word, 0);
        }

        public Node navigate(char a) {
            return root.connected.get(a);
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        TreeMap<Integer, List<String>> byLength = new TreeMap<>();
        for (String word : words) {
            byLength.computeIfAbsent(word.length(), na -> new ArrayList<>()).add(word);
        }

        List<String> toReturn = new ArrayList<>();
        Trie root = new Trie();
        for (List<String> currentBatch : byLength.values()) {

            for (String word : currentBatch) {
                if (check(word, root.root, root, 0, 0) >= 2)
                    toReturn.add(word);
            }

            for (String word : currentBatch) {
                root.addWord(word);
            }
        }

        return toReturn;
    }

    public int check(String word, Trie.Node currentNode, Trie trie, int index, int counter) {

        if (index == word.length())
            return (currentNode.isWord || currentNode == trie.root) ? counter : 0;

        char current = word.charAt(index);
        Trie.Node next = currentNode.connected.get(current);
        if (next == null)
            return 0;

        if (next.isWord) {
            int a = check(word, trie.root, trie, index + 1, counter + 1);
            if (a >= 2)
                return a;
        }

        return check(word, next, trie, index + 1, counter);
    }
}
