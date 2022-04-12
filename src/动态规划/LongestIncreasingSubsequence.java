package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/9
 **/

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *         最长递增子序列
 *         动态规划
 */
public class LongestIncreasingSubsequence {
    //自己琢磨出来的
    public int lengthOfLIS(int[] nums) {
        if (nums.length==0) return 0;
        int arr[]=new int[nums.length];
        arr[0]=1;
        int maxLength=1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <i; j++) {
                arr[i]=Math.max(arr[i],nums[i]>nums[j] ? Math.max(arr[i],arr[j]+1):1);
            }
            maxLength=Math.max(arr[i],maxLength);
        }
        return maxLength;
    }
    //答案，跟自己的题解一样
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
    //贪心算法 对数组进行一个重新分配位置
    public int lengthOfLIS3(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
    public static void main(String[] args) {
        LongestIncreasingSubsequence l=new LongestIncreasingSubsequence();
        l.lengthOfLIS3(new int[]{3,15,2,16,3,4,5,6,7,17,101,18});
    }
}
