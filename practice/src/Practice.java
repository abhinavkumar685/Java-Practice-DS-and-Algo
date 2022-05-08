import java.util.*;

public class Practice {
    public static void main(String[] args) {
        int[] coins = {2, 3, 5, 6};
        int target = 10;

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i=1; i< dp.length; i++) {
            for(int coin : coins) {
                if(i-coin >= 0) {
                    dp[i] += dp[i-coin];
                }
            }
        }
        System.out.println(dp[dp.length-1]);
    }
}