class Solution {
    public int countSubarrays(int[] nums) {
        int ans = 0;
        for(int i=1; i<=nums.length-2; i++){
            if(2* (nums[i-1]+nums[i+1]) == nums[i]){
                ans++;
            }
        }
        return ans;
    }
}