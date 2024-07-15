public class Main {
    public static void main(String[] args) {
        int nums[]=new int[]{2,7,9,3,1,11};
        System.out.println(maxRob(nums,6));
    }
    static int maxRob(int nums[], int n)
    {
        int dp[]=new int[n+1];
        dp[0]=0;
        for(int i=1;i<=n;i++)
        {
            dp[i]=Math.max(nums[i-1]+((i-2)>=0?dp[i-2]:0), dp[i-1]);
        }
        return dp[n];

    }
}