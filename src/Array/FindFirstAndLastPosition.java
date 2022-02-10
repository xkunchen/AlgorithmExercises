package Array;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/2/8 10:43<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 在排序数组中查找元素的第一个和最后一个位置
 */
public class FindFirstAndLastPosition {
    //二分查找
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FindFirstAndLastPosition f=new FindFirstAndLastPosition();
        System.out.println(f.searchRange(new int[]{5,7,7,8,8,9,10,11,12},8));
    }
}
