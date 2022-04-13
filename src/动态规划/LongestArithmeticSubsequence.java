package 动态规划;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/1/22 15:04<br/>
 *
 * @author xkunchen<br />
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  最长等差数列
 *  https://leetcode-cn.com/problems/longest-arithmetic-subsequence/
 */
public class LongestArithmeticSubsequence {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        Set<Integer> diffs = new HashSet<>();
        for(int i = 0; i < n; i++){
            for(int j = i+1 ; j < n; j++){
                diffs.add(nums[j]-nums[i]);
            }
        }
        int res = 2;
        for(int diff : diffs){
            res = Math.max(res,LS(nums,diff));
        }
        return res;
    }
    public int longestArithSeqLength2(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                dp[i][j] = 2;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                int target = 2 * nums[i] - nums[j];
                if(map.containsKey(target)) {
                    dp[i][j] = dp[map.get(target)][i] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
            map.put(nums[i], i);
        }
        return max;
    }

    public int LS(int[] arr, int difference) {
        Map<Integer,Integer> map = new HashMap<>();
        int res = 1;
        for(int i = 0; i < arr.length; i++){
            int val = map.getOrDefault(arr[i]-difference,0);
            map.put(arr[i],val+1);
            res = Math.max(res,val+1);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestArithmeticSubsequence l=new LongestArithmeticSubsequence();
        l.longestArithSeqLength2(new int[]{9,4,7,2,10});
    }
}
