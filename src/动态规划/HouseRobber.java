package 动态规划;
//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
// 示例 1：
// 输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。
// 示例 2：
// 输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 提示：
// 0 <= nums.length <= 100
// 0 <= nums[i] <= 400
// Related Topics 动态规划
// 👍 1086 👎 0
/**
 * 打家劫舍
 *         https://leetcode-cn.com/problems/house-robber/
 *         动态规划
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length==1)return nums[0];
        if (nums.length==0) return 0;
        int[] arr=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i<2){
                arr[i]=nums[i];
            }else if (2==i){
                arr[i]=nums[i]+nums[i-2];
            }else if (i>2){
                arr[i]= Math.max( arr[i-2],arr[i-3])+nums[i];
            }
        }
        return Math.max(arr[arr.length-1],arr[arr.length-2]);
    }
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }
    //空间复杂度最低
    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
    public static void main(String[] args) {
        HouseRobber h=new HouseRobber();
        int arr[]={2,100,9,3,100};
        System.out.println(h.rob2(arr));
    }
}
