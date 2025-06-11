class Solution {
    public int maxDifference(String s, int k) {
        int n = s.length();
        int ans = Integer.MIN_VALUE;

        for (char a = '0'; a <= '4'; ++a) {
            for (char b = '0'; b <= '4'; ++b) {
                if (a == b) continue;

                int[][] minDiff = new int[2][2];
                for (int i = 0; i < 2; i++) {
                    Arrays.fill(minDiff[i], Integer.MAX_VALUE / 2);
                }

                int pa = 0, pb = 0;
                int l = 0;
                int[] prefixA = new int[n + 1];
                int[] prefixB = new int[n + 1];

                for (int i = 0; i < n; i++) {
                    prefixA[i + 1] = prefixA[i] + (s.charAt(i) == a ? 1 : 0);
                    prefixB[i + 1] = prefixB[i] + (s.charAt(i) == b ? 1 : 0);
                }

                for (int r = 1; r <= n; r++) {
                    pa = prefixA[r];
                    pb = prefixB[r];

                    while (r - l >= k) {
                        int pa_l = prefixA[l];
                        int pb_l = prefixB[l];
                        if (pa_l < pa && pb_l < pb) {
                            minDiff[pa_l % 2][pb_l % 2] = Math.min(minDiff[pa_l % 2][pb_l % 2], pa_l - pb_l);
                            l++;
                        } else {
                            break;
                        }
                    }

                    int curDiff = pa - pb;
                    int targetAParity = 1 - (pa % 2);
                    int targetBParity = pb % 2;
                    int cand = curDiff - minDiff[targetAParity][targetBParity];
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}
