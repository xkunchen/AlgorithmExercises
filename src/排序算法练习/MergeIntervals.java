package 排序算法练习;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/4/26
 **/
//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
// 示例 1：
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 示例 2：
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
// 提示：
// 1 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti <= endi <= 104
// Related Topics 排序 数组
// 👍 913 👎 0
/**
 * 分析：区间重合的判断是arr1[0]<arr2[0]<arr1[1],arr1[0]<arr2[1]<arr1[1]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 * 合并区间
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
    //因为是闭区间所以无效
    public int[][] merge2(int[][] intervals) {
        List<int[]> list=new ArrayList<>();
        int maxValue=Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < 2; j++) {
                maxValue=maxValue<intervals[i][j]?intervals[i][j]:maxValue;
            }
        }
        int[] arr=new int[maxValue+2];
        for (int i = 0; i < intervals.length; i++) {
            for (int j = intervals[i][0]; j <= intervals[i][1]; j++) {
                arr[j]=1;
            }
        }
        int index=0;
        int left=0;
        int right=0;
        while (index<arr.length){
            while (index<arr.length&&arr[index]==0){
                left=index+1;
                index++;
            }
            while (index<arr.length&&arr[index]==1){
                right=index;
                index++;
            }
            if (left==arr.length){
                break;
            }
            list.add(new int[]{left,right});
        }
        int[][] returnArr=new int[list.size()][2];
        return list.toArray(returnArr);
    }

    public static void main(String[] args) {
        MergeIntervals m=new MergeIntervals();
        int[][] intervals=new int[][]{
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };
        m.merge(intervals);
    }
}
