package com.CK;

public class Main {

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        Solution solution = new Solution();
        System.out.println(solution.maxCoins(nums));
    }
}

//Bottom-up
class Solution2 {
    public int maxCoins(int[] nums) {
        if (nums.length == 0) return 0;
        int[] coins = new int[nums.length + 2];
        int n = 1;
        for (int c : nums) {
            if (c != 0) coins[n++] = c;
        }
        coins[0] = coins[n++] = 1;
        int[][] maxCoin = new int[n][n];
        for (int dis = 2; dis < n; dis++) {
            for (int left = 0; left + dis < n; left++) {
                int right = left + dis;
                for (int i = left + 1; i <= right - 1; i++) {
                    maxCoin[left][right] = Math.max(maxCoin[left][right],
                            coins[i] * coins[left] * coins[right] + maxCoin[left][i] + maxCoin[i][right]);
                }
            }
        }
        return maxCoin[0][n - 1];
    }
}

//Top-down
class Solution {
    public int maxCoins(int[] nums) {

        // reframe the problem
        int n = nums.length + 2;
        int[] new_nums = new int[n];

        for(int i = 0; i < nums.length; i++){
            new_nums[i+1] = nums[i];
        }

        new_nums[0] = new_nums[n - 1] = 1;

        // cache the results of dp
        int[][] memo = new int[n][n];

        // find the maximum number of coins obtained from adding all balloons from (0, len(nums) - 1)
        return dp(memo, new_nums, 0, n - 1);
    }

    public int dp(int[][] memo, int[] nums, int left, int right) {
        // no more balloons can be added
        if (left + 1 == right) return 0;

        // we've already seen this, return from cache
        if (memo[left][right] > 0) return memo[left][right];

        // add each balloon on the interval and return the maximum score
        int ans = 0;
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                    + dp(memo, nums, left, i) + dp(memo, nums, i, right));
        // add to the cache
        memo[left][right] = ans;
        return ans;
    }
}