package 回溯;
//给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。
// 示例 1：
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 示例 2：
//输入：nums = [0]
//输出：[[],[0]]
// 提示：
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// Related Topics 位运算 数组 回溯算法
// 👍 948 👎 0

import java.util.*;

/**
 * 子集
 *               https://leetcode-cn.com/problems/subsets/
 *               回溯
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        dealSubset(nums,result,list,0);
        return result;
    }

    private void dealSubset(int[] nums, List<List<Integer>> result, List<Integer> list,int index) {
        result.add(list);
        for (int i = index; i < nums.length; i++) {
            List<Integer> newList =new ArrayList<>();
            newList.addAll(list);
            newList.add(nums[i]);
            dealSubset(nums,result,newList,i+1);
        }
    }

    public static void main(String[] args) {
        Subsets s=new Subsets();
        int nums[]={6,5,5};
        s.subsets(nums);
    }
}
