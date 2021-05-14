package 高级动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/5/14
 **/
//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
// 注意：你不能在买入股票前卖出股票。
// 示例 1:
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 示例 2:
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
// Related Topics 数组 动态规划
// 👍 1203 👎 0
/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *        买卖股票的最佳时机
 *        动态规划
 */

/**
 * 分析：
 * 事例： 输入: [7,1,5,3,6,4] 输出: 5 1与6 如果是暴力解法的话，就得去遍历两次这个数组进行相减
 * 如果相减是负数的就以当前索引为第一个值
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int max=0;
        int minIndex=0;
        for (int i = 0; i < prices.length; i++) {
            minIndex=prices[minIndex]>prices[i]?i:minIndex;
            max=Math.max(max,prices[i]-prices[minIndex]);
        }
        return  max;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock b=new BestTimeToBuyAndSellStock();
        b.maxProfit(new int[]{2,5,1,3});
    }
}
