package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/13 18:35<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/combination-sum-iv/
 *    	[剑指 Offer II 104]排列的数目
 */
public class II104combinationSumIv {
    public int combinationSum4(int[] nums, int target) {
        int arr[]=new int[target+1];
        arr[0]=1;
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int j = 0;j<nums.length&&nums[j] <= i; j++) {
                arr[i]+=arr[i-nums[j]];
            }
        }
        return arr[target];
    }

    public static void main(String[] args) {
        II104combinationSumIv i=new II104combinationSumIv();
        i.combinationSum4(new int[]{1,2,3},4);
    }
}
