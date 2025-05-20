public class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];

        // Apply the difference array technique
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            diff[l] += 1;
            if (r + 1 < n) {
                diff[r + 1] -= 1;
            }
        }

        int current = 0;
        for (int i = 0; i < n; i++) {
            current += diff[i];
            if (nums[i] > current) {
                return false;
            }
        }

        return true;
    }
}
