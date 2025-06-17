class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MAX = 100_000; // maximum constraints

    private long[] fact = new long[MAX + 1], invFact = new long[MAX + 1];

    public Solution() {
        initFactorials();
    }

    private void initFactorials() {
        fact[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[MAX] = modPow(fact[MAX], MOD - 2);
        for (int i = MAX; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }
    }

    private long modPow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) != 0) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }

    private long comb(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    public int countGoodArrays(int n, int m, int k) {
        long ways = comb(n - 1, k);
        ways = ways * m % MOD;
        ways = ways * modPow(m - 1, n - k - 1) % MOD;
        return (int) ways;
    }
}
