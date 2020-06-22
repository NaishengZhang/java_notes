package algorithms.dp;

import java.util.Arrays;
import java.util.Scanner;

/*
* https://www.acwing.com/problem/content/description/2/
input
4 5
1 2
2 4
3 4
4 5
Expected result
8
* */
public class Q01Backpack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();

        int[] w = new int[N];
        int[] v = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        System.out.println("w:" + Arrays.toString(w));
        System.out.println("v" + Arrays.toString(v));
//
//        int[][] memo = new int[N][C + 1];
//        for (int[] m : memo) {
//            Arrays.fill(m, -1);
//        }
//        System.out.println(knapsack3(w, v, N - 1, C, memo)); // 将从0 - n-1所有物品装入容量为C的背包

//        // 优化空间复杂度 N -> 2
//        int[][] dp = new int[2][C + 1];
//        for (int[] m : dp) {
//            Arrays.fill(m, -1);
//        }
//        System.out.println(knapsack4(w, v, N - 1, C, dp)); // 将从0 - n-1所有物品装入容量为C的背包

//        int[] dp = new int[C + 1];
//        Arrays.fill(dp, -1);
//        System.out.println(knapsack5(w, v, N - 1, C, dp)); // 将从0 - n-1所有物品装入容量为C的背包


        int[][] memo = new int[N][C + 1];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        System.out.println("记忆化搜索");
        System.out.println("result:" + knapsack2(w, v, N - 1, C, memo)); // 将从0 - n-1所有物品装入容量为C的背包
        System.out.println("Memo after search:");
        for (int[] m : memo) {
            System.out.println(Arrays.toString(m));
        }


        int[][] memo1 = new int[N][C + 1];
        for (int[] m : memo1) {
            Arrays.fill(m, -1);
        }
        System.out.println("DP");
        System.out.println("result:" + knapsack3(w, v, N - 1, C, memo1)); // 将从0 - n-1所有物品装入容量为C的背包
        System.out.println("Memo after search:");
        for (int[] m : memo1) {
            System.out.println(Arrays.toString(m));
        }


    }

    // Solution1, Brute force, time O(n^2), f(index, c) 由index和c组成的对 可能有重复计算。
    private static int knapsack1(int[] w, int[] v, int index, int c) {
        if (index < 0 || c <= 0) {//没有物品或者背包没有空间
            return 0;
        }
        int res = knapsack1(w, v, index - 1, c);
        if (c >= w[index]) {
            res = Math.max(res, v[index] + knapsack1(w, v, index - 1, c - w[index]));
        }
        return res;
    }


    // Solution2,记忆化搜索 int[][] memo = new int[N + 1][C + 1];
    private static int knapsack2(int[] w, int[] v, int index, int c, int[][] memo) {
        if (index < 0 || c <= 0) {//没有物品或者背包没有空间
            return 0;
        }
        if (memo[index][c] == -1) {
            memo[index][c] = knapsack2(w, v, index - 1, c, memo);
            if (c >= w[index]) {
                memo[index][c] = Math.max(memo[index][c],
                        v[index] + knapsack2(w, v, index - 1, c - w[index], memo));
            }
        }
        return memo[index][c];
    }

    // Solution3, DP, Time and Space O(n * c)
    private static int knapsack3(int[] w, int[] v, int index, int c, int[][] dp) {

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= c; j++) {
                if (i == 0) {
                    dp[i][j] = j >= w[i] ? v[i] : 0;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= w[i]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]);
                    }
                }
            }
        }
        int n = dp.length;
        return dp[n - 1][c];
    }

    // Solution4, Optimize DP, Space O(2C)
    private static int knapsack4(int[] w, int[] v, int index, int C, int[][] memo) {
        int n = w.length;
        if (n == 0 || C == 0)
            return 0;
        for (int j = 0; j <= C; j++)
            memo[0][j] = (j >= w[0] ? v[0] : 0);
        for (int i = 1; i < n; i++)
            for (int j = 0; j <= C; j++) {
                memo[i % 2][j] = memo[(i - 1) % 2][j];
                if (j >= w[i])
                    memo[i % 2][j] = Math.max(memo[i % 2][j], v[i] + memo[(i - 1) % 2][j - w[i]]);
            }

        return memo[(n - 1) % 2][C];
    }

    // Solution5, Optimize DP. Space O(C)
    private static int knapsack5(int[] w, int[] v, int index, int C, int[] memo) {
        int n = w.length;
        if (n == 0 || C == 0)
            return 0;
        for (int j = 0; j <= C; j++)
            memo[j] = (j >= w[0] ? v[0] : 0);

        for (int i = 1; i < n; i++)
            for (int j = C; j >= w[i]; j--) {
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);

            }
        return memo[C];
    }
}
