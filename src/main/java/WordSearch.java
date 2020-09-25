import java.util.*;

//https://leetcode.com/problems/word-search-ii
class WordSearch {

    public static class Trie {

        final Node root;

        public Trie(String[] words) {

            this.root = new Node('0', null);

            for (String word : words) {
                root.add(word, 0);
            }
        }

        public static class Node {
            final char nodeChar;
            final Map<Character, Node> forwardConnect = new HashMap<>();
            String word;

            public Node(char nodeChar) {
                this.nodeChar = nodeChar;
                this.word = null;
            }

            public Node(char nodeChar, String word) {
                this.nodeChar = nodeChar;
                this.word = word;
            }

            public void add(String word, int index) {

                char toAdd = word.charAt(index);
                Node next = forwardConnect.computeIfAbsent(toAdd, Node::new);
                if (index == word.length() - 1)
                    next.word = word;
                else
                    next.add(word, index + 1);
            }

            public boolean isWord() {
                return this.word != null;
            }
        }
    }

    public static class Board {

        final char[][] board;
        final int[][] indexedBoard;
        final List<int[]> reverseIndexed;

        public Board(char[][] board) {
            this.board = board;
            //quick mapping from 1D to 2D
            this.indexedBoard = new int[board.length][];
            this.reverseIndexed = new ArrayList<>();

            int counter = 0;
            for (int row = 0; row < board.length; row++) {
                indexedBoard[row] = new int[board[row].length];
                for (int column = 0; column < board[row].length; column++) {
                    indexedBoard[row][column] = counter;
                    reverseIndexed.add(new int[]{row, column});
                    counter++;
                }
            }
        }

        public int[] to2D(int index) {
            return reverseIndexed.get(index);
        }

        public int from2D(int row, int column) {
            return indexedBoard[row][column];
        }

        public boolean safe(int row, int column) {
            return row >= 0 && row < board.length && column >= 0 && column < board[row].length;
        }

        public char charAt(int row, int column) {
            return board[row][column];
        }
    }

    public List<String> findWords(char[][] charBoard, String[] words) {

        Board board = new Board(charBoard);
        BitSet visited = new BitSet();
        Trie trie = new Trie(words);

        Set<String> foundWords = new HashSet<>();
        for (int row = 0; row < board.board.length; row++) {
            for (int column = 0; column < board.board[row].length; column++) {
                dfs(row, column, board, visited, trie.root, foundWords);
            }
        }

        return new ArrayList<>(foundWords);
    }

    public void dfs(int row, int column, Board board, BitSet visited, Trie.Node currentNode, Set<String> foundWords) {

        if (!board.safe(row, column))
            return;
        int index = board.from2D(row, column);
        if (visited.get(index))
            return;

        visited.set(index);
        char currentChar = board.charAt(row, column);
        Trie.Node nextNode = currentNode.forwardConnect.get(currentChar);
        if (nextNode != null) {
            //check if a word is matched already
            if (nextNode.isWord())
                foundWords.add(nextNode.word);

            dfs(row, column - 1, board, visited, nextNode, foundWords);
            dfs(row, column + 1, board, visited, nextNode, foundWords);
            dfs(row - 1, column, board, visited, nextNode, foundWords);
            dfs(row + 1, column, board, visited, nextNode, foundWords);
        }
        visited.clear(index);
    }

    public static void main(String[] args) {

//        char[][] board = new char[][]{
//                new char[]{'o', 'a', 'a', 'n'},
//                new char[]{'e', 't', 'i', 'e'},
//                new char[]{'i', 'h', 'k', 'r'},
//                new char[]{'p', 'e', 'a', 't'},
//        };
//        String[] words = new String[]{"oath", "pea", "eat", "rain"};
//        System.out.println(new Solution().findWords(board, words));

        char[][] board = new char[][]{
                new char[]{'a', 'b'},
                new char[]{'a', 'a'}
        };
        String[] words = new String[]{"aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"};
        System.out.println(new WordSearch().findWords(board, words));
    }
}
