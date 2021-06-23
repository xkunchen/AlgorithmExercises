package 回溯;

import java.util.HashMap;
import java.util.Map;

//给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
// 示例:
// 输入: 2
//输出: 91
//解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
// Related Topics 数学 动态规划 回溯算法
// 👍 106 👎 0

/**
 * 计算各个位数不同的数字个数
 *         https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 *         回溯
 */
public class CountNumbersWithUniqueDigits {
    /**
     * 分析，如何判断数字相等，
     */
    //暴力法
    public int countNumbersWithUniqueDigits(int n) {
        int result=0;
        for (Integer i = 0; i < (int)Math.pow(10,n); i++) {
            if (judge(i.toString().length(),i)){
                result++;
            }
        }
        return result;
    }
    //判断数字是否符合要求
    private boolean judge(int n,int number){
        if (n==1){
            return true;
        }
        Map<Integer,Object> remainderMap=new HashMap<Integer, Object>();
        int remainder=number;
        int remainder1=0;
        for (int i=n-1;i>=0;i--){
            //除数
            remainder1=remainder / (int) Math.pow(10,i);
            //余数
            remainder=remainder % (int) Math.pow(10,i);
            if (remainderMap.containsKey(remainder1)){
                return false;
            }
            remainderMap.put(remainder1,null);
        }
        return true;
    }
    //迭代法
    public int countNumbersWithUniqueDigits2(int n) {
        if (n==0){
            return 1;
        }
        n=Math.min(n,10);
        int ans = 10, base = 9, sum = 9;
        for(int i = 1; i < n; ++i){
            sum *= base;
            ans += sum;
            base--;
        }
        return ans;
    }
    //回溯
    /**
     * 唯一的解释是先排最小的个位，再排第一位，最后排中位，
     */
    public int countNumbersWithUniqueDigits3(int n) {
        if (n == 0) return 1;
        return dfs(Math.min(10, n), 0, new boolean[10]);
    }

    private int dfs(int n, int idx, boolean[] used) {
        int count = 0;
        if (idx != n) {
            for (int i = 0; i < 10; i++) {
                // 剪枝：多位数时，第一个数字不能为0
                if (i == 0 && n > 1 && idx == 1) {
                    continue;
                }
                // 剪枝：不能使用用过的数字
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                count += dfs(n, idx + 1, used) + 1;
                used[i] = false;
            }
        }
        return count;
    }
    //动态规划
    public int countNumbersWithUniqueDigits4(int n) {
        int result[]=new int[n+1];
        for(int i = 2; i <= n; ++i){
            result[i] =  (result[i-1]*10 + (int)(9*Math.pow(10, i-2) - result[i-1])*(i-1));
        }
        int sum = 0;
        for(int one: result){
            sum += one;
        }
        return (int) Math.pow(10, n)-sum;
    }
    public static void main(String[] args) {
        CountNumbersWithUniqueDigits c=new CountNumbersWithUniqueDigits();
        System.out.println(c.countNumbersWithUniqueDigits4(3));
    }
}
