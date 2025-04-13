import java.util.*;

class Solution {
    public long countGoodIntegers(int n, int k) {
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        long ans = 0;
        Set<String> seen = new HashSet<>();
        int base = (int) Math.pow(10, (n - 1) / 2);

        for (int i = base; i < base * 10; i++) {
            String s = String.valueOf(i);
            StringBuilder sb = new StringBuilder(s).reverse();
            s += sb.substring(n % 2);
            if (Long.parseLong(s) % k != 0) {
                continue;
            }

            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String t = new String(arr);
            if (seen.contains(t)) {
                continue;
            }
            seen.add(t);
            int[] cnt = new int[10];
            for (char c : arr) {
                cnt[c - '0']++;
            }

            long res = (n - cnt[0]) * factorial[n - 1];
            for (int x : cnt) {
                res /= factorial[x];
            }
            ans += res;
        }

        return ans;
    }
}
