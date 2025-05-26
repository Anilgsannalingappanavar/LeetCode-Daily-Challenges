import java.util.*;

public class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];
        
        // Initialize graph
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        
        // Build graph and indegree
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        // Color count for each node: colorCount[node][color] = max count of color 'a'+color at node
        int[][] colorCount = new int[n][26];

        // Queue for topological sort
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int processed = 0;
        int maxColorCount = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            processed++;

            // Update color count of current node
            int colorIndex = colors.charAt(node) - 'a';
            colorCount[node][colorIndex]++;
            maxColorCount = Math.max(maxColorCount, colorCount[node][colorIndex]);

            // Traverse neighbors
            for (int neighbor : graph.get(node)) {
                for (int c = 0; c < 26; c++) {
                    colorCount[neighbor][c] = Math.max(colorCount[neighbor][c], colorCount[node][c]);
                }

                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If all nodes are not processed, there’s a cycle
        return processed == n ? maxColorCount : -1;
    }
}
