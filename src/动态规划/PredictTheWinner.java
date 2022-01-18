package 动态规划;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2021/12/30 15:03<br/>
 *
 * @author xkunchen<br />
 */
//给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
// 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。每一回合，玩家从数组的任意一端取一个数字（即，nums[0
//] 或 nums[nums.length - 1]），取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取
//时，游戏结束。
// 如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。你可以假设每个玩家的玩法都会使他的分
//数最大化。
// 示例 1：
//输入：nums = [1,5,2]
//输出：false
//解释：一开始，玩家 1 可以从 1 和 2 中进行选择。
//如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）
//可选。
//所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
//因此，玩家 1 永远不会成为赢家，返回 false 。
// 示例 2：
//输入：nums = [1,5,233,7]
//输出：true
//解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
//最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。
// 提示：
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 10⁷
// Related Topics 递归 数组 数学 动态规划 博弈 👍 509 👎 0
/**
 * https://leetcode-cn.com/problems/predict-the-winner/
 *  预测赢家
 */

/**
 * 分析：最小子问题
 * 本题可以看成
 * 假设{1,2,5,2,411,6,1}可以看成{1,2,5,2,411,6}或{2,5,2,411,6,1}的结果互换
 * 假设{1,2,5,2,4,6,1}可以看成{1,2,5,2,4,6}或{2,5,2,4,6,1}的结果互换
 * f(x) 和 f(y) 两个人的结果 f(1~7)=-max(f(1~6))+f(7)>0 或 f(1~7)=-max(f(2~7))+f(1)>0
 * 最终总结出： f(1~n)=max((-max(f(1~(n-1)))+f(n))，-max(f(2~n))+f(1))
 */
public class PredictTheWinner {

    //递归
    public boolean PredictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }

    /**
     * f(1~n)=max((-max(f(1~(n-1)))+f(n))，-max(f(2~n))+f(1))
     */
    public boolean PredictTheWinner2(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                int i1 = nums[i] - dp[i + 1][j];
                int i2 = nums[j] - dp[i][j - 1];
                dp[i][j] = Math.max(i1,i2 );
            }
        }
        return dp[0][length - 1] >= 0;
    }

    public boolean PredictTheWinner3(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }

    public static void main(String[] args) {
        PredictTheWinner p=new PredictTheWinner();
        System.out.println(p.PredictTheWinner2(new int[]{1,2,5,2,4,6,1}));
    }

}
