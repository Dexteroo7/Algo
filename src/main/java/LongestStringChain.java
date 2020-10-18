import java.util.*;

import static java.util.Collections.emptyList;

class LongestStringChain {

    public static class Word {
        final String word;
        List<Word> connected;
        BitSet letterLookup;
        int maxChain = 0;

        public Word(String word) {
            this.word = word;
        }

        public void addConnection(Word word) {
            if (connected == null)
                connected = new ArrayList<>();
            connected.add(word);
        }

        public List<Word> getConnected() {
            return this.connected == null ? Collections.emptyList() : this.connected;
        }

        public boolean hasLettersFrom(String word) {

            if (letterLookup == null) {
                this.letterLookup = new BitSet(this.word.length());
                for (int i = 0; i < this.word.length(); i++) {
                    letterLookup.set(this.word.charAt(i) - 'a');
                }
            }

            for (int i = 0; i < word.length(); i++) {
                if (!letterLookup.get(word.charAt(i) - 'a'))
                    return false;
            }
            return true;
        }
    }

    public int longestStrChain(String[] words) {

        Map<Integer, List<Word>> byLength = new HashMap<>();

        for (String word : words) {
            Word asWord = new Word(word);
            byLength.computeIfAbsent(word.length(), na -> new ArrayList<>()).add(asWord);
        }

        for (List<Word> wordList : byLength.values()) {
            for (Word current : wordList) {

                List<Word> nextWords = byLength.getOrDefault(current.word.length() + 1, emptyList());
                for (Word nextWord : nextWords) {
                    if (nextWord.hasLettersFrom(current.word))
                        current.addConnection(nextWord);
                }
            }
        }

        int max = 0;
        for (List<Word> wordList : byLength.values()) {
            for (Word current : wordList) {
                max = Integer.max(max, maxChain(current));
            }
        }
        return max;
    }

    public int maxChain(Word current) {

        if (current.maxChain > 0)
            return current.maxChain;

        int max = 1;
        for (Word word : current.getConnected()) {
            word.maxChain = maxChain(word);
            max = Integer.max(max, 1 + word.maxChain);
        }

        return max;
    }
}
