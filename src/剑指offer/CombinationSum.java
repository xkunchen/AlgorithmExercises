package 剑指offer;

import com.sun.org.apache.bcel.internal.generic.NEW;
import sun.security.util.ArrayUtil;

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
public class CombinationSum {
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
        CombinationSum c=new CombinationSum();
        int[] arr=new int[]{2,3,6,7};
        c.combinationSum(arr,7);
    }
}
