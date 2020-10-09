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
            right=nums.length-1;
        }else if(target==nums[0]){
            return 0;
        }else {
            left=0;
            right= (index==0) ?nums.length-1:index-1;
        }

        while (left <= right) {
            if (nums[left] ==target) return left;
            if (nums[right]==target) return right;
            mid = (right - left) / 2 + left;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }
    public int search2(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 先根据 nums[0] 与 target 的关系判断目标值是在左半段还是右半段
            if (target >= nums[0]) {
                // 目标值在左半段时，若 mid 在右半段，则将 mid 索引的值改成 inf
                if (nums[mid] < nums[0]) {
                    nums[mid] = Integer.MAX_VALUE;
                }
            } else {
                // 目标值在右半段时，若 mid 在左半段，则将 mid 索引的值改成 -inf
                if (nums[mid] >= nums[0]) {
                    nums[mid] = Integer.MIN_VALUE;
                }
            }

            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
    public int search3(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        SearchInRotatedSortedArray s=new SearchInRotatedSortedArray();
        int nums[]=new int[]{4,5,6,7,0,1,2};
        //      int nums[]=new int[]{1};
        s.search2(nums,6);
    }
}
