package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/13 18:27<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 *    	[剑指 Offer II 103]最少的硬币数目
 */
public class II103coinChange {
    //贪心算法
    //重点是 i+count<res 优化了一些不必要的循环
    int res = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount){
        if(amount==0){
            return 0;
        }
        Arrays.sort(coins);
        mincoin(coins,amount,coins.length-1,0);
        return res==Integer.MAX_VALUE? -1:res;
    }
    private void mincoin(int[] coins,int amount, int index, int count){
        if(amount==0){
            res = Math.min(res,count);
            return;
        }
        if(index<0){
            return;
        }
        for(int i = amount/coins[index];i>=0 && i+count<res; i--){
            mincoin(coins,amount - (i*coins[index]), index-1, count+i);
        }
    }
    public int coinChange2(int[] coins, int amount) {
        int arr[]=new int[amount+1];
        Arrays.fill(arr, amount+1);
        arr[0] = 0;
        for (int i = 0; i < amount+1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    arr[i] = Math.min(arr[i], arr[i - coins[j]] + 1);
                }
            }
        }
        return arr[amount] > amount ? -1 : arr[amount];
    }
}
