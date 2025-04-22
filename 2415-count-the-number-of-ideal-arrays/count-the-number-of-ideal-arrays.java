class Solution {
    static final int MOD = 1_000_000_007;
    long[] fact, invFact;

    public int idealArrays(int n, int maxValue) {
        int maxExp = 14 + n; // enough for constraints
        preprocessFactorials(maxExp);

        int[] spf = smallestPrimeFactor(maxValue); // sieve

        long res = 0;
        for (int i = 1; i <= maxValue; i++) {
            Map<Integer, Integer> factors = getPrimeFactors(i, spf);
            long ways = 1;
            for (int exp : factors.values()) {
                ways = (ways * nCr(n + exp - 1, exp)) % MOD;
            }
            res = (res + ways) % MOD;
        }
        return (int) res;
    }

    // Sieve to find smallest prime factors
    private int[] smallestPrimeFactor(int max) {
        int[] spf = new int[max + 1];
        for (int i = 2; i <= max; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= max; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
        return spf;
    }

    // Prime factorization using SPF
    private Map<Integer, Integer> getPrimeFactors(int num, int[] spf) {
        Map<Integer, Integer> map = new HashMap<>();
        while (num > 1) {
            int p = spf[num];
            map.put(p, map.getOrDefault(p, 0) + 1);
            num /= p;
        }
        return map;
    }

    // Precompute factorials and inverse factorials
    private void preprocessFactorials(int max) {
        fact = new long[max + 1];
        invFact = new long[max + 1];
        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[max] = modInverse(fact[max]);
        for (int i = max - 1; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    // Compute nCr modulo MOD
    private long nCr(int n, int r) {
        if (r > n) return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }

    // Modular inverse using Fermat's little theorem
    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    // Modular exponentiation
    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
}
