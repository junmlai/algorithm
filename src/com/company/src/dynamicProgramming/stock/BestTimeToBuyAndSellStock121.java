package com.company.src.dynamicProgramming.stock;

/**
 * 股票系列一：
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock121 {
    //解法一：暴力解法，双重循环。
    // 有个超级长的测试用例超时了。
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len==1)
            return 0;
        int max = 0;
        for (int i = 0; i < len-1; i++) {
            for (int j = i+1; j <len ; j++) {
                if (prices[j]>prices[i]){
                    max = Math.max(prices[j]-prices[i], max);
                }
            }
        }

        return max;
    }

    //解法二：保存历史最低点，每天假设自己卖出股票赚x钱，求最大值
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len==1)
            return 0;
        int minPrice = prices[0];
        int profit = 0;
        for (int i = 1; i <len ; i++) {
            if (prices[i]<minPrice){
                minPrice = prices[i];
                continue;
            }
            profit = Math.max(profit, prices[i]-minPrice);
        }
        return profit;
    }


    // 解法三：套模版，动态规划。https://labuladong.github.io/algo/3/28/96/
    //最大交易次数k=1，状态：天数、是否持有股票、限制交易次数
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        if (len==1)
            return 0;
        int[][] dp = new int[len][2];  // 数组可以进行空间优化，只保存两个状态值，前一天 持有0/1只股票的最大收益
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i <len ; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]); // 不操作/卖出
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]); // 不操作/买入（只能一次买入，所以必定是-price[i]）
        }

        return dp[len-1][0];
    }

}
