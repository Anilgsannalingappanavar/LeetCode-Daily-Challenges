class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long count = 0;
        int n = nums.length;
        int leftBound = -1;
        int lastMin = -1;
        int lastMax = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                leftBound = i;
            }
            if (nums[i] == minK) {
                lastMin = i;
            }
            if (nums[i] == maxK) {
                lastMax = i;
            }
            count += Math.max(0, Math.min(lastMin, lastMax) - leftBound);
        }
        return count;
    }
}