package 二分查找;
//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
// 你可以假设数组中不存在重复的元素。
// 你的算法时间复杂度必须是 O(log n) 级别。
// 示例 1:
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
// 示例 2:
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1
// Related Topics 数组 二分查找
// 👍 982 👎 0
/**
 * 搜索旋转排序数组
 *             https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *             二分查找
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        //首先查找到扭转点
        int index=0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]<nums[i-1]){
                index=i;
                break;
            }
        }
        int left = 0, right , mid;
        if (target<nums[0]){
            left=index;
            right=nums.length;
        }else {
            left=0;
            right=index-1;
        }

        while (left <= right) {
            mid = (right - left) / 2 + left;
            if(mid*mid==x){
                return mid;
            }else if((long)mid*(long)mid<x){
                if(((long)mid+1)*((long)mid+1)>x){
                    return mid;
                }
                left = mid + 1;
            }else{
                if(((long)mid-1)*((long)mid-1)<x){
                    return mid-1;
                }
                right = mid - 1;
            }
        }
        return -1;
    }
}
