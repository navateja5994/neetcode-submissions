class WordDictionary {

    class Node {
        Node[] child = new Node[26];
        boolean end;
    }

    Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node curr = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (curr.child[index] == null) {
                curr.child[index] = new Node();
            }

            curr = curr.child[index];
        }

        curr.end = true;
    }

    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    private boolean dfs(Node node, String word, int index) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            return node.end;
        }

        char c = word.charAt(index);

        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.child[i] != null &&
                    dfs(node.child[i], word, index + 1)) {
                    return true;
                }
            }
            return false;
        }

        return dfs(node.child[c - 'a'], word, index + 1);
    }
}