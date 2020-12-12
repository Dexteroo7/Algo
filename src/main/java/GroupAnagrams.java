import java.util.*;

class GroupAnagrams {

    public static final class AnagramKey {

        final int size;
        final int[] setChars = new int['z' - 'a' + 1];

        public AnagramKey(String word) {
            for (int i = 0; i < word.length(); i++) {
                this.setChars[word.charAt(i) - 'a']++;
            }
            this.size = word.length();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AnagramKey that = (AnagramKey) o;
            return size == that.size &&
                    Arrays.equals(setChars, that.setChars);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(size);
            result = 31 * result + Arrays.hashCode(setChars);
            return result;
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<AnagramKey, List<String>> byKey = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {

            AnagramKey key = new AnagramKey(strs[i]);
            byKey.computeIfAbsent(key, na -> new ArrayList<>()).add(strs[i]);
        }

        return new ArrayList<>(byKey.values());
    }
}
