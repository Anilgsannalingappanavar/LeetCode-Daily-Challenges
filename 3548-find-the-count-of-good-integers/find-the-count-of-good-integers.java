import java.util.*;

class Solution {
    Set<String> vis;

    public long countGoodIntegers(int n, int k) {
        vis = new HashSet<>();
        int target = (n + 1) / 2; // Fix: n=1 is wrong, should be (n + 1) / 2
        return solve("", target, n, k);
    }

    long solve(String str, int target, int n, int k) {
        if (str.length() == target) {
            String fullPalindrome;
            if (n % 2 == 0) {
                fullPalindrome = str + reverse(str);
            } else {
                fullPalindrome = str + reverse(str.substring(0, str.length() - 1));
            }

            long number = Long.valueOf(fullPalindrome);
            if (number % k == 0) {
                if (check_if_saved(fullPalindrome)) return 0;
                save(fullPalindrome);
                return calculate(fullPalindrome);
            }
            return 0;
        }

        long ans = 0;
        for (int i = 0; i <= 9; i++) {
            if (str.isEmpty() && i == 0) continue; // Skip leading 0
            ans += solve(str + i, target, n, k);
        }
        return ans;
    }

    String reverse(String a) {
        return new StringBuilder(a).reverse().toString(); // Fix: input variable was named wrong
    }

    long calculate(String s) {
        long ans = factorial(s.length());
        int[] count = new int[10];
        for (char c : s.toCharArray()) {
            count[c - '0']++;
        }
        for (int i : count) {
            ans /= factorial(i);
        }

        // Exclude permutations with leading zero
        if (count[0] == 0) return ans;

        count[0]--;
        long zeroPerm = factorial(s.length() - 1);
        for (int i : count) {
            zeroPerm /= factorial(i);
        }
        return ans - zeroPerm;
    }

    boolean check_if_saved(String s) {
        char[] arr = s.toCharArray(); // Fix: incorrect variable name and array usage
        Arrays.sort(arr);
        String sorted = new String(arr);
        return vis.contains(sorted);
    }

    void save(String s) {
        char[] arr = s.toCharArray(); // Fix: incorrect variable name and array usage
        Arrays.sort(arr);
        String sorted = new String(arr);
        vis.add(sorted);
    }

    long factorial(int x) {
        long ans = 1;
        for (int i = 1; i <= x; i++) {
            ans *= i;
        }
        return ans;
    }
}
