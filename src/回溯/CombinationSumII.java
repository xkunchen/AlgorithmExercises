package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/25
 **/

import java.util.*;

/**
 * https://leetcode-cn.com/problems/combination-sum-ii/
 * 组合总和 II
 */
public class CombinationSumII {
    //自己解的，换个跳过条件
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> currentList=new ArrayList<>();
        List<List<Integer>> reslutList=new ArrayList<>();
        Arrays.sort(candidates);
        deal(reslutList,candidates,target,0,currentList,0);
        return reslutList;
    }

    private void deal(List<List<Integer>> reslutList,int[] candidates, int target, int currentIndex, List<Integer> currentList, int currentSum) {
        if (currentSum>target||currentIndex>candidates.length){
            return;
        }
        if (currentSum==target){
            reslutList.add(new ArrayList<>(currentList));
            return;
        }
        for (int i = currentIndex; i < candidates.length; i++) {
            if(i > currentIndex && candidates[i] == candidates[i-1]) continue;
            currentList.add(candidates[i]);
            deal(reslutList,candidates,target,i+1,currentList,currentSum+candidates[i]);
            currentList.remove(currentList.size()-1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        Deque<Integer> path=new LinkedList<>();
        // 排序方便进行剪枝
        Arrays.sort(candidates);
        dfs(res,path,candidates,0,target);
        return res;
    }
    private void dfs(List<List<Integer>> res, Deque<Integer> path, int[] nums, int index, int target){
        if (target==0){
            res.add(new ArrayList<>(path));
            return;
        }
        if (index>=nums.length) return;
        List<Integer> pre=new ArrayList<>(nums.length);
        // 此处遍历决定的是每一个位置的数字的值
        for (int i=index;i<nums.length;i++){
            if (pre.contains(nums[i])) continue;
            // 剪枝
            if (target-nums[i]<0) break;
            path.addLast(nums[i]);
            pre.add(nums[i]);
            dfs(res,path,nums,i+1,target-nums[i]);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSumII c=new CombinationSumII();
        int[] arr=new int[]{10,1,2,7,6,1,5};
        c.combinationSum(arr,8);
    }
}
