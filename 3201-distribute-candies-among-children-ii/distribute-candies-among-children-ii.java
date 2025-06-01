public class Solution {
    public long distributeCandies(int n, int limit) {
        if (n > 3 * limit) {
            return 0;
        }

        return combination(n + 2, 2)
                - 3 * combination(n - limit + 1, 2)
                + 3 * combination(n - 2 * limit, 2)
                - combination(n - 3 * limit - 1, 2);
    }

    private long combination(int n, int k) {
        if (n < 0 || k < 0 || n < k) {
            return 0;
        }
        long res = 1;
        for (int i = 1; i <= k; ++i) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }
}
