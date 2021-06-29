package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/24
 **/

/**
 * 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class CombinationSum {
    public  List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int sum=0;
        dfs(candidates,target,result,path,sum,0);
        return result;
    }
    public static void dfs(int[] indicates,int target,List<List<Integer>> result,List<Integer> path,int sum,int index){
        if(sum==target){
            result.add(new ArrayList<>(path));
            return;
        }
        if(sum>target) return;

        for(int i=index;i<indicates.length;i++){
            path.add(indicates[i]);
            sum+=indicates[i];
            dfs(indicates,target,result,path,sum,i);
            sum-=indicates[i];
            path.remove(path.size()-1);
        }

    }
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
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
            deal(reslutList,candidates,target,i,currentList,currentSum+candidates[i]);
            currentList.remove(currentList.size()-1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        System.out.println(target - candidates[idx]);
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
    public static void main(String[] args) {
        CombinationSum c=new CombinationSum();
        int[] arr=new int[]{10,1,2,7,6,1,5};
        c.combinationSum3(arr,8);
    }
}
