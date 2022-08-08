package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/8 10:09<br/>
 *
 * @author xkunchen<br />
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combinations/
 *    [剑指 Offer II 080]含有 k 个元素的组合
 */
public class II080combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> returnList=new ArrayList<>();
        List<Integer> currentList=new ArrayList<>();
        dfs(returnList,n,k,currentList,1);
        return returnList;
    }

    private void dfs(List<List<Integer>> returnList,int n, int k, List<Integer> currentList, int index) {
        if (currentList.size()==k){
            returnList.add(new ArrayList<>(currentList));
            return;
        }
        for (int j = index; j <=n; j++) {
            currentList.add(j);
            dfs(returnList,n,k,currentList,j+1);
            currentList.remove(currentList.size()-1);
        }
    }

    public static void main(String[] args) {
        II080combinations i=new II080combinations();
        i.combine(4,2);
    }
}
