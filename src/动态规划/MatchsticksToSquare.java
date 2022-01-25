package 动态规划;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/1/18 15:24<br/>
 *
 * @author xkunchen<br />
 */
//还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴
//都要用到。
// 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
// 示例 1:
//输入: [1,1,2,2,2]
//输出: true
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
// 示例 2:
//输入: [3,3,3,3,4]
//输出: false
//解释: 不能用所有火柴拼成一个正方形。
// 注意:
// 给定的火柴长度和在 0 到 10^9之间。
// 火柴数组的长度不超过15。
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 👍 236 👎 0

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 火柴拼正方形
 * https://leetcode-cn.com/problems/matchsticks-to-square/
 */
public class MatchsticksToSquare {
    public List<Integer> nums;
    public int[] sums;
    public int possibleSquareSide;

    public MatchsticksToSquare() {
        this.sums = new int[4];
    }

    // Depth First Search function.
    public boolean dfs(int index) {
        if (index == this.nums.size()) {
            return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];
        }
        int element = this.nums.get(index);
        for(int i = 0; i < 4; i++) {
            if (this.sums[i] + element <= this.possibleSquareSide) {
                this.sums[i] += element;
                if (this.dfs(index + 1)) {
                    return true;
                }
                this.sums[i] -= element;
            }
        }
        return false;
    }

    //深度优先遍历
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int L = nums.length;
        int perimeter = 0;
        for(int i = 0; i < L; i++) {
            perimeter += nums[i];
        }
        this.possibleSquareSide =  perimeter / 4;
        if (this.possibleSquareSide * 4 != perimeter) {
            return false;
        }
        this.nums = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(this.nums, Collections.reverseOrder());
        return this.dfs(0);
    }
}
class Solution {
    public HashMap<Pair<Integer, Integer>, Boolean> memo;
    public int[] nums;
    public int possibleSquareSide;
    public Solution() {
        this.memo = new HashMap<Pair<Integer, Integer>, Boolean>();
    }
    public boolean recurse(Integer mask, Integer sidesDone) {
        int total = 0;
        int L = this.nums.length;
        Pair<Integer, Integer> memoKey = new Pair(mask, sidesDone);
        for(int i = L - 1; i >= 0; i--) {
            if ((mask&(1 << i)) == 0) {
                total += this.nums[L - 1 - i];
            }
        }
        if (total > 0 && total % this.possibleSquareSide == 0) {
            sidesDone++;
        }
        if (sidesDone == 3) {
            return true;
        }
        if (this.memo.containsKey(memoKey)) {
            return this.memo.get(memoKey);
        }
        boolean ans = false;
        int c = total / this.possibleSquareSide;
        int rem = this.possibleSquareSide * (c + 1) - total;
        for(int i = L - 1; i >= 0; i--) {
            if (this.nums[L - 1 - i] <= rem && (mask&(1 << i)) > 0) {
                if (this.recurse(mask ^ (1 << i), sidesDone)) {
                    ans = true;
                    break;
                }
            }
        }
        this.memo.put(memoKey, ans);
        return ans;
    }

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int L = nums.length;
        int perimeter = 0;
        for(int i = 0; i < L; i++) {
            perimeter += nums[i];
        }
        int possibleSquareSide =  perimeter / 4;
        if (possibleSquareSide * 4 != perimeter) {
            return false;
        }
        this.nums = nums;
        this.possibleSquareSide = possibleSquareSide;
        return this.recurse((1 << L) - 1, 0);
    }
}
