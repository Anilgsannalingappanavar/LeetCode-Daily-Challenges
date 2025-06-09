public class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k - 1;

        while (k > 0) {
            long steps = calculateSteps(n, curr, curr + 1);
            if (steps <= k) {
                curr++;       // Move to next prefix
                k -= steps;   // Skip all under curr
            } else {
                curr *= 10;   // Go deeper in trie
                k--;          // Count current node
            }
        }

        return curr;
    }

    private long calculateSteps(int n, long first, long last) {
        long steps = 0;
        while (first <= n) {
            steps += Math.min(n + 1L, last) - first;
            first *= 10;
            last *= 10;
        }
        return steps;
    }
}
