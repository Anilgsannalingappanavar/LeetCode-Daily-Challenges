import java.util.*;

public class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasBox = new boolean[n];     // Tracks boxes we have
        boolean[] opened = new boolean[n];     // Tracks boxes we've opened
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> waiting = new HashSet<>(); // Boxes we have but can't open yet
        int totalCandies = 0;

        // Initialize with initial boxes
        for (int box : initialBoxes) {
            hasBox[box] = true;
            if (status[box] == 1) {
                queue.offer(box);
                opened[box] = true;
            } else {
                waiting.add(box);
            }
        }

        while (!queue.isEmpty()) {
            int box = queue.poll();
            totalCandies += candies[box];

            // Process keys found in the current box
            for (int key : keys[box]) {
                if (status[key] == 0) {
                    status[key] = 1; // Unlock the box
                    if (hasBox[key] && !opened[key]) {
                        queue.offer(key);
                        opened[key] = true;
                        waiting.remove(key);
                    }
                }
            }

            // Process boxes contained in the current box
            for (int contained : containedBoxes[box]) {
                if (!hasBox[contained]) {
                    hasBox[contained] = true;
                    if (status[contained] == 1 && !opened[contained]) {
                        queue.offer(contained);
                        opened[contained] = true;
                    } else {
                        waiting.add(contained);
                    }
                }
            }

            // Check if any waiting boxes can now be opened
            Iterator<Integer> it = waiting.iterator();
            while (it.hasNext()) {
                int b = it.next();
                if (status[b] == 1 && !opened[b]) {
                    queue.offer(b);
                    opened[b] = true;
                    it.remove();
                }
            }
        }

        return totalCandies;
    }
}
