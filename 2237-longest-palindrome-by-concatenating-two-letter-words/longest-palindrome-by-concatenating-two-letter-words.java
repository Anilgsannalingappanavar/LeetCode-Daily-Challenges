import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> count = new HashMap<>();
        int length = 0;
        boolean hasCentral = false;

        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        for (String word : count.keySet()) {
            String reversed = new StringBuilder(word).reverse().toString();

            if (word.equals(reversed)) {
                // Palindromic word like "aa"
                int freq = count.get(word);
                length += (freq / 2) * 4;
                if (freq % 2 == 1) {
                    hasCentral = true;
                }
            } else if (word.compareTo(reversed) < 0 && count.containsKey(reversed)) {
                // Pair with its reverse (to avoid double-counting)
                int pairs = Math.min(count.get(word), count.get(reversed));
                length += pairs * 4;
            }
        }

        if (hasCentral) {
            length += 2; // one central palindromic word like "gg"
        }

        return length;
    }
}
