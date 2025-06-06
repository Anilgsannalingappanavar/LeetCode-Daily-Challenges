import java.util.*;

public class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        char[] minCharRight = new char[n];
        minCharRight[n - 1] = s.charAt(n - 1);

        // Precompute the minimum character to the right of each position
        for (int i = n - 2; i >= 0; i--) {
            minCharRight[i] = (char) Math.min(s.charAt(i), minCharRight[i + 1]);
        }

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            stack.push(s.charAt(i));

            // Determine the minimum character in the remaining string
            char minChar = (i + 1 < n) ? minCharRight[i + 1] : 'z' + 1;

            // Write characters to the paper if they are <= minChar
            while (!stack.isEmpty() && stack.peek() <= minChar) {
                result.append(stack.pop());
            }
        }

        // Append any remaining characters in the stack to the result
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
