package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/15 14:45<br/>
 *
 * @author xkunchen<br />
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *    [剑指 Offer II 082]含有重复元素集合的组合
 *     https://leetcode-cn.com/problems/combination-sum-ii/
 */
public class II082CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            if (i>currentIndex&&candidates[i]==candidates[i-1])continue;
            currentList.add(candidates[i]);
            dealDate(returnList,candidates,currentList,i+1,currentSum+candidates[i],target);
            currentList.remove(currentList.size()-1);
        }
    }

    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum23(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backtrack(path,candidates,target,0,0);
        return res;
    }
    private void backtrack(List<Integer> path,int[] candidates,int target,int sum,int begin) {
        if(sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin;i < candidates.length;i++) {
            if(i > begin && candidates[i] == candidates[i-1]) continue;
            int rs = candidates[i] + sum;
            if(rs <= target) {
                path.add(candidates[i]);
                backtrack(path,candidates,target,rs,i+1);
                path.remove(path.size()-1);
            } else {
                break;
            }
        }
    }


    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();
    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }
    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }



    public static void main(String[] args) {
        II082CombinationSumII i=new II082CombinationSumII();
        i.combinationSum2(new int[]{10,1,2,7,6,1,5},8);
    }
}
