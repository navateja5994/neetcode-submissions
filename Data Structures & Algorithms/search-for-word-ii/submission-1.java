public class Solution {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;

        TrieNode() {
        }
    }

    TrieNode root;
    java.util.List<String> result;
    int rows, cols;

    public java.util.List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        result = new java.util.ArrayList<>();

        rows = board.length;
        cols = board[0].length;

        // Build Trie
        for (String word : words) {
            TrieNode node = root;

            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.word = word;
        }

        // Search board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node) {
        if (r < 0 || c < 0 || r >= rows || c >= cols) {
            return;
        }

        char ch = board[r][c];

        if (ch == '#') {
            return;
        }

        TrieNode next = node.children[ch - 'a'];

        if (next == null) {
            return;
        }

        // Found a word
        if (next.word != null) {
            result.add(next.word);

            // Avoid duplicate words
            next.word = null;
        }

        // Mark visited
        board[r][c] = '#';

        dfs(board, r + 1, c, next);
        dfs(board, r - 1, c, next);
        dfs(board, r, c + 1, next);
        dfs(board, r, c - 1, next);

        // Restore
        board[r][c] = ch;
    }
}