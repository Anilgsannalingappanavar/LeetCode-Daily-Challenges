public class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parent = new int[26];  // For 'a' to 'z'

        // Initially, each character is its own parent
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Union operation - always link larger char to smaller to preserve lex order
        for (int i = 0; i < s1.length(); i++) {
            int a = s1.charAt(i) - 'a';
            int b = s2.charAt(i) - 'a';
            union(parent, a, b);
        }

        // Replace each character in baseStr with its smallest equivalent
        StringBuilder sb = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            sb.append((char)(find(parent, c - 'a') + 'a'));
        }

        return sb.toString();
    }

    // Find with path compression
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    // Union by always keeping the lexicographically smaller one as parent
    private void union(int[] parent, int x, int y) {
        int px = find(parent, x);
        int py = find(parent, y);
        if (px != py) {
            if (px < py) {
                parent[py] = px;
            } else {
                parent[px] = py;
            }
        }
    }
}
