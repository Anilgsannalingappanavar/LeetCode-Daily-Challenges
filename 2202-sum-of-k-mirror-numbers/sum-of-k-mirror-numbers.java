class Solution {
    public long kMirror(int k, int n) {
        long sum = 0;
        int found = 0;
        
        for (int len = 1; ; len++) {
            int start = (int) Math.pow(10, (len - 1) / 2);
            int end = (int) Math.pow(10, (len + 1) / 2);
            
            for (int i = start; i < end; i++) {
                long pal = makePalindrome(i, len % 2 == 0);
                if (isBaseKPalindrome(pal, k)) {
                    sum += pal;
                    if (++found == n) return sum;
                }
            }
        }
    }
    
    private long makePalindrome(int x, boolean even) {
        long pal = x;
        int t = even ? x : x / 10;
        while (t > 0) {
            pal = pal * 10 + (t % 10);
            t /= 10;
        }
        return pal;
    }
    
    private boolean isBaseKPalindrome(long num, int base) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % base);
            num /= base;
        }
        String s = sb.toString();
        return s.equals(sb.reverse().toString());
    }
}
