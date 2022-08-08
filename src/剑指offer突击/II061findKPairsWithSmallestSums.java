package 剑指offer突击;

import java.util.*;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/3 14:32<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
 *    	[剑指 Offer II 061]和最小的 k 个数对
 */
public class II061findKPairsWithSmallestSums {
    //方法一：优先队列
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i,0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    //方法二：二分查找
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        /*二分查找第 k 小的数对和的大小*/
        int left = nums1[0] + nums2[0];
        int right = nums1[m - 1] + nums2[n - 1];
        int pairSum = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long cnt = 0;
            int start = 0;
            int end = n - 1;
            while (start < m && end >= 0) {
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    cnt += end + 1;
                    start++;
                }
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos = n - 1;
        /*找到小于目标值 pairSum 的数对*/
        for (int i = 0; i < m; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }
        }

        /*找到等于目标值 pairSum 的数对*/
        pos = n - 1;
        for (int i = 0; i < m && k > 0; i++) {
            int start1 = i;
            while (i < m - 1 && nums1[i] == nums1[i + 1]) {
                i++;
            }
            while (pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            int start2 = pos;
            while (pos > 0 && nums2[pos] == nums2[pos - 1]) {
                pos--;
            }
            if (nums1[i] + nums2[pos] != pairSum) {
                continue;
            }
            int count = (int) Math.min(k, (long) (i - start1 + 1) * (start2 - pos + 1));
            for (int j = 0; j < count && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[pos]);
                ans.add(list);
            }
        }
        return ans;
    }

    public List<List<Integer>> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
        int l = nums1[0] + nums2[0];
        int r = nums2[nums2.length - 1] + nums1[nums1.length - 1];
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> eq = new ArrayList<>();
        int midMax = r;// 和最小的K对数字的上界
        while (l <= r){//对和进行二分查找
            int mid = l + ((r - l) >>> 1);
            int count = 0;
            int i = 0, j = nums2.length - 1;
            while (i < nums1.length && j >= 0){// 计算小于等于mid的count个数以及等于mid的midlen个数
                if(nums1[i] + nums2[j] <= mid){
                    count += j + 1;// 第i列有j+1个元素<mid
                    i++;
                } else {
                    j--;
                }
            }
            if(count >= k) {
                midMax = mid;//更新记录上界
                r = mid - 1;
            }
            else l = mid + 1;
        }
        for (int j = 0; j < nums2.length; j++) {
            for (int i = 0;  i < nums1.length ; i++){
                if(nums1[i] + nums2[j] < midMax) {
                    if(k <= 0) break;
                    k--;
                    res.add(Arrays.asList(nums1[i],nums2[j]));
                }else if(nums1[i] + nums2[j] == midMax && eq.size() <= k){//记录下与上界相等的元素，先不着急添加进res
                    eq.add(Arrays.asList(nums1[i],nums2[j]));
                }else break;
            }
        }
        res.addAll(eq.subList(0,Math.min(k, eq.size())));//判断k小于总对数
        return res;
    }

    public static void main(String[] args) {
        II061findKPairsWithSmallestSums i=new II061findKPairsWithSmallestSums();
        i.kSmallestPairs2(new int[]{1,7,11},new int[]{2,4,6},7);
    }
}
