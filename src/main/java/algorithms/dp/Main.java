package algorithms.dp;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{2, 2, 3, 5}));
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        int c = sum / 2 + 1;
        boolean[][] dp = new boolean[n][c + 1];
        for (boolean[] d : dp) {
            Arrays.fill(d, false);
        }

        for (int j = 0; j <= c; j++) {
            dp[0][j] = nums[0] == j;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= c; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i] ) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[n - 1][c];
    }
}
