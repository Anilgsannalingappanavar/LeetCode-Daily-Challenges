import java.util.*;

public class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(i, n, result);
        }
        return result;
    }

    private void dfs(int current, int n, List<Integer> result) {
        if (current > n) return;
        result.add(current);
        for (int i = 0; i <= 9; i++) {
            if (current * 10 + i > n) return;
            dfs(current * 10 + i, n, result);
        }
    }
}
