package 剑指offer突击;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/6 19:11<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/combination-sum/
 * ✔[剑指 Offer II 081]允许重复选择元素的组合
 * Related Topics 数组 回溯 👍 17 👎 0
 */
public class II081CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> returnList = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        dealDate(returnList,candidates,currentList,0,0,target);
        return returnList;
    }

    private void dealDate(List<List<Integer>> returnList,int[] candidates,List<Integer> currentList,int currentIndex,int currentSum,int target) {
        if (target==currentSum){
            returnList.add(new ArrayList<>(currentList));
            return;
        }
        if (target<currentSum){
            return;
        }
        for(int i=currentIndex;i<candidates.length;i++){
            if (i>0&&candidates[i]==candidates[i-1])continue;
            currentList.add(candidates[i]);
            dealDate(returnList,candidates,currentList,i,currentSum+candidates[i],target);
            currentList.remove(currentList.size()-1);
        }
    }

    public static void main(String[] args) {
        II081CombinationSum c=new II081CombinationSum();
        int[] arr=new int[]{2,3,6,7};
        c.combinationSum(arr,7);
    }
}
