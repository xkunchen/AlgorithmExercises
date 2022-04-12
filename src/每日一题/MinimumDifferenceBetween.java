package 每日一题;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/2/11 10:19<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
 * 1984. 学生分数的最小差值
 */
public class MinimumDifferenceBetween {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < n; ++i) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
