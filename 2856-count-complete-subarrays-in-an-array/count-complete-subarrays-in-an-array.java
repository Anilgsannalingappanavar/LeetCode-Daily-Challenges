import java.util.*;

public class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int total = 0;
        Set<Integer> uniqueElements = new HashSet<>();
        
        // Step 1: Count total unique elements in the array
        for (int num : nums) {
            uniqueElements.add(num);
        }

        int k = uniqueElements.size();  // Total unique elements
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        int left = 0;

        // Step 2: Sliding window to count subarrays with all k elements
        for (int right = 0; right < n; right++) {
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            // Shrink the window from left to maintain only subarrays with all unique elements
            while (freqMap.size() == k) {
                total += (n - right);  // all subarrays ending at right and starting from left
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
                left++;
            }
        }

        return total;
    }
}
