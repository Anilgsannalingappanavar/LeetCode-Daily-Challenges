class Solution {
    public int maxDifference(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int maxOdd = 0;
        int minEven = s.length();  // initialize to upper bound
        
        for (int freq : count) {
            if (freq == 0) continue;
            if (freq % 2 == 1) {
                maxOdd = Math.max(maxOdd, freq);
            } else {
                minEven = Math.min(minEven, freq);
            }
        }
        
        return maxOdd - minEven;
    }
}
