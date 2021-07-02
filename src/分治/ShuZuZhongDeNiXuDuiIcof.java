package 分治;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/2
 **/

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * 剑指 Offer 51. 数组中的逆序对
 */
public class ShuZuZhongDeNiXuDuiIcof {
    int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);
    }
    private int mergeSort(int l, int r) {
        // 终止条件
        if (l >= r) return 0;
        // 递归划分
        int m = (l + r) / 2;
        int res = mergeSort(l, m) + mergeSort(m + 1, r);
        // 合并阶段
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++)
            tmp[k] = nums[k];
        for (int k = l; k <= r; k++) {
            if (i == m + 1)
                nums[k] = tmp[j++];
            else if (j == r + 1 || tmp[i] <= tmp[j])
                nums[k] = tmp[i++];
            else {
                nums[k] = tmp[j++];
                res += m - i + 1; // 统计逆序对
            }
        }
        return res;
    }

}
