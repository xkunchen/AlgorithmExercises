package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/29
 **/
/**
 * https://leetcode-cn.com/problems/permutations-ii/
 * 全排列 II
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> currentList=new ArrayList<>();
        List<List<Integer>> reslutList=new ArrayList<>();
        Arrays.sort(nums);
        boolean[] isUsed=new boolean[nums.length];
        deal(reslutList,nums,0,currentList,isUsed);
        return reslutList;
    }
    //112
    //112 121 112 121 211 211
    private void deal(List<List<Integer>> reslutList, int[] nums, int currentIndex , List<Integer> currentList, boolean[] isUsed) {
        if (currentIndex==nums.length){
            reslutList.add(new ArrayList<>(currentList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isUsed[i] || (i > 0 && nums[i] == nums[i - 1] && !isUsed[i - 1])) {
                continue;
            }
            currentList.add(nums[i]);
            isUsed[i]=true;
            deal(reslutList,nums,currentIndex+1,currentList,isUsed);
            isUsed[i]=false;
            currentList.remove(currentList.size()-1);
        }
    }

    public static void main(String[] args) {
        PermutationsII p=new PermutationsII();
        int nums[]=new int[]{3,3,0,3};
        p.permuteUnique(nums);
    }
}
