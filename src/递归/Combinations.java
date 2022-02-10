package 递归;
//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
// 示例:
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [2,3],
//  [3,4],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// Related Topics 回溯算法
// 👍 338 👎 0


import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * https://leetcode-cn.com/problems/combinations/
 */
public class Combinations {

    //回溯算法
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> arr=new ArrayList<>();
        dealData(arr,new ArrayList<>(),n,k,0);
        return arr;
    }

    private void dealData(List<List<Integer>> arr, List<Integer> currentList, int n, int k,int currentInteger) {
        if (currentList.size()==k){
            List<Integer> newList=new ArrayList<>();
            newList.addAll(currentList);
            arr.add(newList);
            return;
        }
        for (Integer i = currentInteger+1; i <= n; i++) {
            currentList.add(i);
            dealData(arr,currentList,n,k,i);
            currentList.remove(i);
        }
    }
    //非递归（字典序法）实现组合型枚举

    public List<List<Integer>> combine2(int n, int k) {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 初始化
        // 将 temp 中 [0, k - 1] 每个位置 i 设置为 i + 1，即 [0, k - 1] 存 [1, k]
        // 末尾加一位 n + 1 作为哨兵
        for (int i = 1; i <= k; ++i) {
            temp.add(i);
        }
        temp.add(n + 1);

        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<Integer>(temp.subList(0, k)));
            j = 0;
            // 寻找第一个 temp[j] + 1 != temp[j + 1] 的位置 t
            // 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
            while (j < k && temp.get(j) + 1 == temp.get(j + 1)) {
                temp.set(j, j + 1);
                ++j;
            }
            // j 是第一个 temp[j] + 1 != temp[j + 1] 的位置
            temp.set(j, temp.get(j) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Combinations c=new Combinations();
        c.combine2(4,2);
    }
}
