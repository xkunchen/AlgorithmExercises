package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/29
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 * 全排列
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> currentList=new ArrayList<>();
        List<List<Integer>> reslutList=new ArrayList<>();
        boolean[] isUsed=new boolean[nums.length];
        deal(reslutList,nums,0,currentList,isUsed);
        return reslutList;
    }
    private void deal(List<List<Integer>> reslutList,int[] nums, int currentIndex, List<Integer> currentList,boolean[] isUsed) {
        if (currentIndex>nums.length){
            return;
        }
        if (currentIndex==nums.length){
            reslutList.add(new ArrayList<>(currentList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isUsed[i]) continue;
            currentList.add(nums[i]);
            isUsed[i]=true;
            deal(reslutList,nums,currentIndex+1,currentList,isUsed);
            isUsed[i]=false;
            currentList.remove(currentList.size()-1);
        }
    }

    public static void main(String[] args) {
        Permutations p=new Permutations();
        int[] nums={1,2,3};
        p.permute(nums);
    }
}
