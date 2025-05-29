import java.util.*;

public class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n1 = edges1.length + 1;
        int n2 = edges2.length + 1;

        List<List<Integer>> adj1 = buildGraph(edges1, n1);
        List<List<Integer>> adj2 = buildGraph(edges2, n2);

        int[] depth1 = new int[n1];
        int[] depth2 = new int[n2];
        int[] countDepth1 = new int[2];
        int[] countDepth2 = new int[2];

        dfs(adj1, 0, -1, 0, depth1, countDepth1);
        dfs(adj2, 0, -1, 0, depth2, countDepth2);

        int maxCountDepth2 = Math.max(countDepth2[0], countDepth2[1]);
        int[] result = new int[n1];
        for (int i = 0; i < n1; i++) {
            result[i] = countDepth1[depth1[i]] + maxCountDepth2;
        }
        return result;
    }

    private List<List<Integer>> buildGraph(int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    private void dfs(List<List<Integer>> graph, int node, int parent, int depth, int[] depths, int[] countDepth) {
        depths[node] = depth % 2;
        countDepth[depths[node]]++;
        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                dfs(graph, neighbor, node, depth + 1, depths, countDepth);
            }
        }
    }
}
