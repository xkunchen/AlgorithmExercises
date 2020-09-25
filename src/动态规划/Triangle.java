package 动态规划;
//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
// 例如，给定三角形：
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
// 说明：
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
// Related Topics 数组 动态规划
// 👍 602 👎 0

import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.get;
/**
 * 思路：
 * 上一个最小路径f（n）(n)=min（f（n-1）（n-1），f（n-1）（n）），进行递归，把递归结果装起来，自下而上
 */

/**
 * 三角形最小路径和
 *         https://leetcode-cn.com/problems/triangle/description/
 *         动态规划
 */
public class Triangle {
    //第一遍，自上而下的动态规划
    public int minimumTotal(List<List<Integer>> triangle) {
        int arr[] = new int[triangle.get(triangle.size() - 1).size()];
        int MinLoad = 0;
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> trian = triangle.get(i);
            for (int j = trian.size() - 1; j >= 0; j--) {
                if (i == 0) {
                    arr[j] = trian.get(j);
                } else {
                    if (j == 0) {
                        arr[j] = arr[j] + trian.get(j);
                    } else if (j == trian.size() - 1) {
                        arr[j] = arr[j - 1] + trian.get(j);
                    } else {
                        arr[j] = Math.min(arr[j - 1], arr[j]) + trian.get(j);
                    }
                }
                if (i == triangle.size() - 1) {
                    if (j == trian.size() - 1) MinLoad = arr[j];
                    else MinLoad = Math.min(arr[j], MinLoad);
                }
            }
        }
        return MinLoad;
    }
    //第二遍，自下而上的动态规划，反过来想象
    public int minimumTotal2(List<List<Integer>> triangle) {
        int arr[] = new int[triangle.get(triangle.size() - 1).size()];
        int MinLoad = 0;
        return MinLoad;
    }

    public static void main(String[] args) {
        Triangle t = new Triangle();
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        List<Integer> three = new ArrayList<>();
        List<Integer> four = new ArrayList<>();
        one.add(2);
        two.add(3);
        two.add(4);
        three.add(6);
        three.add(5);
        three.add(7);
        four.add(4);
        four.add(1);
        four.add(8);
        four.add(3);
        triangle.add(one);
        triangle.add(two);
        triangle.add(three);
        triangle.add(four);
        t.minimumTotal(triangle);
    }
}