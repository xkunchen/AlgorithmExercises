package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/18 17:45<br/>
 *
 * @author xkunchen<br />
 */


import java.util.*;

/**
 *    [剑指 Offer II 083]没有重复元素集合的全排列
 * https://leetcode-cn.com/problems/permutations/
 *
 */
public class II083Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        List<Integer> currentList=new ArrayList<>();
        Set<Integer> indexSet=new HashSet<>();
        dealData(list,currentList,nums,indexSet);
        return  list;
    }

    private void dealData(List<List<Integer>> list, List<Integer> currentList, int[] nums,Set<Integer> index) {
        if (currentList.size()==nums.length){
            list.add(new ArrayList<>(currentList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (index.contains(i))continue;
            currentList.add(nums[i]);
            index.add(i);
            dealData(list,currentList,nums,index);
            index.remove(i);
            currentList.remove(currentList.size()-1);
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

    public static void main(String[] args) {
        II083Permutations i=new II083Permutations();
        i.permute2(new int[]{1,2,3});
    }
}
