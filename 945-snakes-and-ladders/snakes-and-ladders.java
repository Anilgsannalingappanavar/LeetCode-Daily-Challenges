import java.util.*;

public class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0}); // {current square, moves}

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int s = curr[0];
            int moves = curr[1];

            if (s == n * n) return moves;

            for (int i = 1; i <= 6 && s + i <= n * n; i++) {
                int next = s + i;
                int[] pos = getCoordinates(next, n);
                int row = pos[0], col = pos[1];

                if (board[row][col] != -1) {
                    next = board[row][col];
                }

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, moves + 1});
                }
            }
        }

        return -1;
    }

    private int[] getCoordinates(int s, int n) {
        int quot = (s - 1) / n;
        int rem = (s - 1) % n;
        int row = n - 1 - quot;
        int col = row % 2 != n % 2 ? rem : n - 1 - rem;
        return new int[]{row, col};
    }
}
