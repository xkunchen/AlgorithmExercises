package 剑指offer;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/8 11:13<br/>
 *
 * @author xkunchen<br />
 */
public class II079subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> returnList=new ArrayList<>();
        List<Integer> currentList=new ArrayList<>();
        dfs(returnList,nums,currentList,0);
        return returnList;
    }

    private void dfs(List<List<Integer>> returnList, int[] nums, List<Integer> currentList, int index) {
        returnList.add(new ArrayList<>(currentList));
        for (int j = index; j < nums.length; j++) {
            currentList.add(nums[j]);
            dfs(returnList,nums,currentList,j+1);
            currentList.remove(currentList.size()-1);
        }
    }

    public static void main(String[] args) {
        II079subsets i=new II079subsets();
        i.subsets(new int[]{1,2,3});
    }
}
