import java.util.*;

public class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        Map<Integer, Long> prefixCount = new HashMap<>();
        prefixCount.put(0, 1L);  // Base case

        int current = 0;
        long result = 0;

        for (int num : nums) {
            // Increase current if num % modulo == k
            if (num % modulo == k) {
                current++;
            }

            // Calculate the key we're looking for
            int needed = (current - k + modulo) % modulo;

            // Add the number of times this "needed" value has occurred
            result += prefixCount.getOrDefault(needed, 0L);

            // Store current % modulo in map
            int currentMod = current % modulo;
            prefixCount.put(currentMod, prefixCount.getOrDefault(currentMod, 0L) + 1);
        }

        return result;
    }
}

