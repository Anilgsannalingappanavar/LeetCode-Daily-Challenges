public class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long total = 0;
        int countBeneficial = 0;
        int minGain = Integer.MAX_VALUE;

        for (int num : nums) {
            int xor = num ^ k;
            if (xor > num) {
                countBeneficial++;
                total += xor;
                minGain = Math.min(minGain, xor - num);
            } else {
                total += num;
                minGain = Math.min(minGain, num - xor);
            }
        }

        // If count is even, keep all beneficial XORs
        // If odd, skip the least gain to make it even
        if (countBeneficial % 2 == 0) {
            return total;
        } else {
            return total - minGain;
        }
    }
}
