package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/29 18:24<br/>
 *
 * @author xkunchen<br />
 */

/**
 *    [剑指 Offer II 009]乘积小于 K 的子数组
 *    https://leetcode-cn.com/problems/subarray-product-less-than-k/
 */
public class II009SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int len=nums.length;
        int left=0;
        int right=0;
        int result=1;
        int count=0;
        while(right<len){
            result*=nums[right];
            while (result >= k) {
                result /= nums[left];
                left++;
            }
            count += right - left + 1;
            right++;
        }
        return  count;
    }

    //二分查找
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k == 0) return 0;
        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + Math.log(nums[i]);
        }

        int ans = 0;
        for (int i = 0; i < prefix.length; i++) {
            int lo = i + 1, hi = prefix.length;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (prefix[mi] < prefix[i] + logk - 1e-9) lo = mi + 1;
                else hi = mi;
            }
            ans += lo - i - 1;
        }
        return ans;
    }

    //双指针
    public int numSubarrayProductLessThanK3(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            ans += right - left + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        II009SubarrayProductLessThanK i=new II009SubarrayProductLessThanK();
        i.numSubarrayProductLessThanK3(new int[]{1,2,3},0);
    }
}
