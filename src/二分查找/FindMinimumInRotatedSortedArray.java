package 二分查找;
//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
// 请找出其中最小的元素。
// 你可以假设数组中不存在重复元素。
// 示例 1:
// 输入: [3,4,5,1,2]
//输出: 1
// 示例 2:
// 输入: [4,5,6,7,0,1,2]
//输出: 0
// Related Topics 数组 二分查找
// 👍 268 👎 0

/**
 * 寻找旋转排序数组中的最小值
 *          https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 *          二分查找
 */
public class FindMinimumInRotatedSortedArray {
    //可以转换为寻找扭转点
    public int findMin(int[] nums) {
        if (nums.length==1) return nums[0];
        int minData=nums[0];
        int left=0,right=nums.length;
        while (left<right){
            int mid=(left+right)/2;
            //解决[1,2]这种情况
            if(mid==nums.length-1) {
                minData=minData<nums[mid]?minData:nums[mid];
                break;
            }
            if (nums[mid]<nums[left]){
                //如果中间值小于最左值，说明扭转点在左半段
                right=mid-1;
                if (nums[right]>nums[mid]){
                    return nums[mid];
                }
            }else{
                //因为没有重复元素,如果中间值大于最左值，说明扭转点在右半段
                left=mid+1;
                if (nums[left]<nums[mid]){
                    return nums[left];
                }
            }
        }
        return minData;
    }

    public static void main(String[] args) {
        int nums[]={4,5,6,7,0,1,2};
        FindMinimumInRotatedSortedArray f=new FindMinimumInRotatedSortedArray();
        System.out.println(f.findMin(nums));
    }
}
