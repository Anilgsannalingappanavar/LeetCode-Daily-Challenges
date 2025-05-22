import java.util.*;

public class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length;

        PriorityQueue<Integer> activeQueries = new PriorityQueue<>(); // min-heap for end indices of used queries
        PriorityQueue<Integer> availableQueries = new PriorityQueue<>(Collections.reverseOrder()); // max-heap for end indices

        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));

        int queryIndex = 0;
        int appliedCount = 0;

        for (int i = 0; i < n; i++) {
            // Add available queries starting at i
            while (queryIndex < queries.length && queries[queryIndex][0] == i) {
                availableQueries.offer(queries[queryIndex][1]);
                queryIndex++;
            }

            // Decrement nums[i] by number of currently active queries
            nums[i] -= activeQueries.size();

            // Apply more queries if needed
            while (nums[i] > 0 && !availableQueries.isEmpty() && availableQueries.peek() >= i) {
                activeQueries.offer(availableQueries.poll());
                nums[i]--;
                appliedCount++;
            }

            // If we can't zero out nums[i], it's impossible
            if (nums[i] > 0) {
                return -1;
            }

            // Remove expired queries (those ending at i)
            while (!activeQueries.isEmpty() && activeQueries.peek() == i) {
                activeQueries.poll();
            }
        }

        return queries.length - appliedCount;
    }
}
