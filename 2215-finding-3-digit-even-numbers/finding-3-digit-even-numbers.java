import java.util.*;

public class Solution {
    public int[] findEvenNumbers(int[] digits) {
        List<Integer> result = new ArrayList<>();
        int[] count = new int[10];

        // Count the occurrences of each digit
        for (int digit : digits) {
            count[digit]++;
        }

        // Iterate through all 3-digit even numbers
        for (int num = 100; num <= 999; num += 2) {
            int[] tempCount = Arrays.copyOf(count, 10);
            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            // Decrease the count for each digit used
            tempCount[a]--;
            tempCount[b]--;
            tempCount[c]--;

            // Check if all digits are available
            if (tempCount[a] >= 0 && tempCount[b] >= 0 && tempCount[c] >= 0) {
                result.add(num);
            }
        }

        // Convert the result list to an array
        return result.stream().mapToInt(i -> i).toArray();
    }
}
