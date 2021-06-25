package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/25
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum-ii/
 * 组合总和 II
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> currentList=new ArrayList<>();
        List<List<Integer>> reslutList=new ArrayList<>();
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
            currentList.add(candidates[i]);
            deal(reslutList,candidates,target,i+1,currentList,currentSum+candidates[i]);
            currentList.remove(currentList.size()-1);
        }
    }
}
