package Array;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/2/11 10:24<br/>
 *
 * @author xkunchen<br />
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/insert-interval/
 * 57. 插入区间
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        InsertInterval i=new InsertInterval();
        i.insert(new int[][]{{1,2}},new int[]{3,5});
    }
}

