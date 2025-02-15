class Solution {
    public boolean isPunishable(int num,int target)
    {
        if(target<0 || num < target)
        {
            return false;
        }
        if(target ==num)
        {
            return true;
        }
        return(
            isPunishable(num/10,target-num%10)||
            isPunishable(num/100,target-num%100)||
            isPunishable(num/1000,target-num%1000)
        );

    }
    public int punishmentNumber(int n) {
        int sum = 0;
        for(int i=1;i<=n;i++)
        {
           int sq = i*i;
           if(isPunishable(sq,i))
           {
            sum+=sq;
           } 
        }
        return sum;
    }
}