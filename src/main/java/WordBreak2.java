import java.util.*;
import java.util.stream.Collectors;

class WordBreak2 {

    private static final class Trie {

        private static final class Node {

            final Map<Character, Trie.Node> connected = new HashMap<>();
            final char value;
            String word;

            private Node(char value) {
                this.value = value;
            }

            public void addWord(String word, int index) {

                if (index == word.length()) {
                    this.word = word;
                    return;
                }

                char current = word.charAt(index);
                connected.computeIfAbsent(current, na -> new Trie.Node(current))
                         .addWord(word, index + 1);
            }
        }

        final Trie.Node root = new Trie.Node('\0');

        public void addWord(String word) {

            root.addWord(word, 0);
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {

        Trie trie = new Trie();
        for (String s1 : wordDict) {
            trie.addWord(s1);
        }

        List<List<String>> sentences = new ArrayList<>();
        check(s, trie.root, trie, 0, new ArrayList<>(), sentences, new HashMap<>());
        return sentences.stream().map(x -> String.join(" ", x)).collect(Collectors.toList());
    }

    public List<List<String>> check(String word, Trie.Node currentNode, Trie trie, int index, List<String> sentence, List<List<String>> sentences, Map<Integer, List<List<String>>> memo) {

        if (index == word.length()) {
            //commit the sentence we are working on
            if (currentNode == trie.root) {
                sentences.add(sentence);
            }
            return sentences;
        }

        Trie.Node next = currentNode.connected.get(word.charAt(index));
        if (next == null)
            return sentences;

        if (next.word != null) {

            List<List<String>> returned;
            if (memo.get(index) != null)
                returned = memo.get(index);
            else {
                returned = check(word, trie.root, trie, index + 1, new ArrayList<>(), new ArrayList<>(), memo);
                memo.put(index, returned);
            }
            sentence.add(next.word);
            for (List<String> strings : returned) {
                List<String> toAdd = new ArrayList<>(sentence.size() + strings.size());
                toAdd.addAll(sentence);
                toAdd.addAll(strings);
                sentences.add(toAdd);
            }
            sentence.remove(next.word);
        }

        return check(word, next, trie, index + 1, sentence, sentences, memo);
    }

//    public static void main(String[] args) {
//
//        System.out.println(new Solution().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
//        new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
//    }
}
