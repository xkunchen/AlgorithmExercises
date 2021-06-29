package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/28
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum-iii/
 * 组合总和 III
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> currentList=new ArrayList<>();
        List<List<Integer>> reslutList=new ArrayList<>();
        deal(reslutList,k,n,0,1,currentList,0);
        return reslutList;
    }
    private void deal(List<List<Integer>> reslutList,int k, int target, int currentIndex,int currentValue, List<Integer> currentList, int currentSum) {
        if (currentIndex>k||currentValue>10){
            return;
        }
        if (currentIndex==k){
            if (currentSum==target)  reslutList.add(new ArrayList<>(currentList));
            return;
        }
        for (int i = currentValue; i < target; i++) {
            if (currentSum+i>target) break;
            currentList.add(i);
            deal(reslutList,k,target,currentIndex+1,i+1,currentList,currentSum+i);
            currentList.remove(currentList.size()-1);
        }
    }

    public static void main(String[] args) {
        CombinationSumIII c=new CombinationSumIII();
        c.combinationSum3(3,9);
    }
}
