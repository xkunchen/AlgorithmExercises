package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/18 18:16<br/>
 *
 * @author xkunchen<br />
 */

import java.util.*;

/**
 *    [剑指 Offer II 084]含有重复元素集合的全排列
 *    https://leetcode-cn.com/problems/permutations-ii/
 */
public class II084PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        List<Integer> currentList=new ArrayList<>();
        Set<Integer> indexSet=new HashSet<>();
        Arrays.sort(nums);
        dealData(list,currentList,nums,indexSet,0);
        return  list;
    }
    private void dealData(List<List<Integer>> list, List<Integer> currentList, int[] nums,Set<Integer> indexSet,int currentIndex) {
        if (currentList.size()==nums.length){
            list.add(new ArrayList<>(currentList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (indexSet.contains(i)||i>0&&nums[i]==nums[i-1]&& !indexSet.contains(i-1))continue;
            currentList.add(nums[i]);
            indexSet.add(i);
            dealData(list,currentList,nums,indexSet,i+1);
            indexSet.remove(i);
            currentList.remove(currentList.size()-1);
        }
    }
    boolean[] vis;

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

    public static void main(String[] args) {
        II084PermutationsII i=new II084PermutationsII();
        i.permuteUnique(new int[]{1,1,2});
    }
}
