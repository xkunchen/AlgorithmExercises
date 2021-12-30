package 桶排序;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2021/11/2 15:06<br/>
 *
 * @author xkunchen<br />
 */

/**
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 */

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/contains-duplicate-iii/
 * 存在重复元素 III
 */
public class ContainsDuplicateIII {
    //桶排序
    /**
     * 思路及算法
     * 我们也可以使用利用桶排序的思想解决本题。我们按照元素的大小进行分桶，维护一个滑动窗口内的元素对应的元素。
     * 对于元素 x，其影响的区间为 [x−t,x+t]。于是我们可以设定桶的大小为 t+1。如果两个元素同属一个桶，那么这两个元素必然符合条件。
     * 如果两个元素属于相邻桶，那么我们需要校验这两个元素是否差值不超过 t。如果两个元素既不属于同一个桶，也不属于相邻桶，那么这两个元素必然不符合条件。
     * 具体地，我们遍历该序列，假设当前遍历到元素 x，那么我们首先检查 x 所属于的桶是否已经存在元素，如果存在，那么我们就找到了一对符合条件的元素，
     * 否则我们继续检查两个相邻的桶内是否存在符合条件的元素。
     * 实现方面，我们将 int 范围内的每一个整数 x 表示为 x=(t+1)×a+b(0≤b≤t)  的形式，这样 x 即归属于编号为 a 的桶。因为一个桶内至多只会有一个元素，所以我们使用哈希表实现即可。
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<Long, Long>();
        long w = (long) t + 1;//桶大小
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);//获取是哪个桶的位置
            if (map.containsKey(id)) { //如果这个桶里有数据
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {//如果相邻的桶有数据，并且在范围内
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {//如果相邻的桶有数据，并且在范围内
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
}
