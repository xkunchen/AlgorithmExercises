package 剑指II;
//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
// 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3
//,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2],
//..., a[n-2]] 。
// 示例 1：
//输入：numbers = [3,4,5,1,2]
//输出：1
// 示例 2：
//输入：numbers = [2,2,2,0,1]
//输出：0
// 提示：
// n == numbers.length
// 1 <= n <= 5000
// -5000 <= numbers[i] <= 5000
// numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
// Related Topics 数组 二分查找 👍 709 👎 0

/**
 *    	[剑指 Offer 11]旋转数组的最小数字	49.3%	Easy	0.0%
 */
public class Offer011 {
    public int minArray(int[] nums) {
        if (nums.length==1) return nums[0];
        int minData=nums[0];
        int left=0,right=nums.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            //解决[1,2]这种情况
            if(mid==left) {
                minData=Math.min(nums[left],nums[right]);
                break;
            }
            if (nums[mid]>nums[left]){
                //如果中间值小于最左值，说明扭转点在左半段
                right=mid;
                while (right!=mid&&nums[right]==nums[mid]){
                    right--;
                }
            }else if (nums[mid]<nums[left]){
                //如果中间值大于最左值，说明扭转点在右半段
                left=mid;
                while (left!=mid&&nums[left]==nums[mid]){
                    left++;
                }
            }else {
                left++;
            }
        }
        return minData;
    }

    public static void main(String[] args) {
        Offer011 o=new Offer011();
        o.minArray(new int[]{2,2,2,0,1});
    }
}
