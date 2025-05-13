import java.util.Arrays;

class Solution {
    public int lengthAfterTransformations(String s, int t) {
        final int MOD = 1_000_000_007;
        int[] count = new int[26];

        // Count the occurrences of each character in the input string
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Perform t transformations
        while (t-- > 0) {
            int[] newCount = new int[26];

            // Update counts for characters 'a' to 'y' (each shifts to the next character)
            for (int i = 0; i < 25; i++) {
                newCount[i + 1] = count[i];
            }

            // Update counts for 'z' (it transforms into 'ab')
            newCount[0] = (newCount[0] + count[25]) % MOD;
            newCount[1] = (newCount[1] + count[25]) % MOD;

            // Move to the next transformation
            count = newCount;
        }

        // Calculate the total length of the resulting string
        return Arrays.stream(count).reduce(0, (a, b) -> (a + b) % MOD);
    }
}
