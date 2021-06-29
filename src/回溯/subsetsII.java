package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/29
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/subsets-ii/
 * 子集 II
 */
public class subsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] isUsed=new boolean[nums.length];
        dealSubset(nums,result,list,0,isUsed);
        return result;
    }

    private void dealSubset(int[] nums, List<List<Integer>> result, List<Integer> list,int index,boolean[] isUsed) {
        result.add(list);
        for (int i = index; i < nums.length; i++) {
            if (i>0&&nums[i]==nums[i-1]&&!isUsed[i-1])continue;
            List<Integer> newList =new ArrayList<>();
            newList.addAll(list);
            newList.add(nums[i]);
            isUsed[i]=true;
            dealSubset(nums,result,newList,i+1,isUsed);
            isUsed[i]=false;
        }
    }
    public static void main(String[] args) {
        subsetsII s=new subsetsII();
        int nums[]={1,2,2};
        s.subsetsWithDup(nums);
    }
}
