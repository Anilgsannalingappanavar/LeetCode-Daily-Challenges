import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int totalRabbits = 0;

        for (int answer : answers) {
            if (answer == 0) {
                totalRabbits += 1;
            } else {
                countMap.put(answer, countMap.getOrDefault(answer, 0) + 1);
                if (countMap.get(answer) == answer + 1) {
                    totalRabbits += answer + 1;
                    countMap.put(answer, 0);
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 0) {
                totalRabbits += entry.getKey() + 1;
            }
        }

        return totalRabbits;
    }
}
