package com.CK;

public class Main {

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        Solution solution = new Solution();
        System.out.println(solution.maxCoins(nums));
    }
}

class Solution {
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