package 滑动窗口;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/17
 **/
/**
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 存在重复元素 III
 * https://leetcode-cn.com/problems/contains-duplicate-iii/
 * 有负数要考虑integer越界问题
 * set的应用
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length<2) return  false;
        int left=0;
        for (int i = 1; i < nums.length; i++) {
            if (i-left>k){
                left++;
            }
            for (int j = left; j <i ; j++) {
                if (Math.abs((long)nums[i]-(long)nums[j])<= t){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
    //桶排序
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<Long, Long>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }
    public static void main(String[] args) {
        ContainsDuplicateIII c=new ContainsDuplicateIII();
        int arr[]=new int[]{1,2,3,1};
        c.containsNearbyAlmostDuplicate2(arr,3,0);
    }
}
