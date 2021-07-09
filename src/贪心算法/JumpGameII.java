package 贪心算法;
//给定一个非负整数数组，你最初位于数组的第一个位置。
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
// 示例:
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 说明:
// 假设你总是可以到达数组的最后一个位置。
// Related Topics 贪心算法 数组
// 👍 724 👎 0

import java.util.HashSet;
import java.util.Set;

/**
 *      https://leetcode-cn.com/problems/jump-game-ii/
 *           贪心算法
 *           第一遍
 */
public class JumpGameII {
    public int jump(int[] nums) {
        //广度优先遍历,超时
        if (nums.length==1||nums[0]==0)return 0;
        //每次跳一步
        if (nums[0]>=nums.length-1) return 1;
        //存储下次遍历的索引
        Set<Integer> index=new HashSet<Integer>();
        Set<Integer> nextindex=new HashSet<Integer>();
        int time=1;
        index.add(0);
        while (true){
            for (int i:index){
                if (i+nums[i]>=nums.length-1) return time;
                for (int j = i+1; j <= i+nums[i]; j++) {
                    if (j>nums.length-1) break;
                    nextindex.add(j);
                }
            }
            index.clear();
            index.addAll(nextindex);
            nextindex.clear();
            time++;
        }
    }
    //贪心算法，思想就是能到达n就能到达n-1，所以可以用。
    public int jump2(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
    public int jump3(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
    public static void main(String[] args) {
        JumpGameII j=new JumpGameII();
        //int arr[]=new int[]{2,1,1,1,1};
         int arr[]=new int[]{2,4,1,1,4,1};
        j.jump2(arr);
    }
}
