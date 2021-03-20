package Array;

import java.util.Arrays;

//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
// 说明:
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
// 示例:
// 输入:
//nums1 = [1,3,5,0,0,0], m = 3
//nums2 = [2,4,6],       n = 3
//输出: [1,2,3,4,5,6]
// Related Topics 数组 双指针
// 👍 594 👎 0

/**
 *  合并两个有序数组
 *  https://leetcode-cn.com/problems/Merge-Sorted-Array/
 */

/**
 * 感悟：读懂题意，想清楚所有情况，三指针
 * 本题有3个步骤，一个是[0] 0 这种为开始单独处理，二个是正常的指针，三是当一个指针到底了如何特殊处理（就是把另外一个指针当前遍历到0，赋值到数组对应位置）
 */
public class MergeSortedArray {
    //第一次解答，既然从前不可以，就从后面开始
    //8月26日第一次解答
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m==0){
            for (int l = 0; l < nums2.length; l++) {
                nums1[l]=nums2[l];
            }
            return;
        }
        //双指针
        int i=m-1;//nums1的下标
        int j=n-1;//nums2的下标
        int pre=nums1[i];
        for (int k = n+m-1; k >=0; k--) {
            if (j<0){
                break;
            }
            //对比两个数组，大的排最后
            if (pre>nums2[j]&&i>=0){
                nums1[k]=nums1[i];
                --i;
                if (i>=0){
                    pre=nums1[i];
                }
            }else{
                nums1[k]=nums2[j--];
            }
        }
    }
    //第二次解答
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (m==0){
            for (int l = 0; l < nums2.length; l++) {
                nums1[l]=nums2[l];
            }
            return;
        }
        int nums1Index=m-1; //nums1指针
        int nums2Index=n-1;//nums2指针
        int numsIndex=m+n-1; //位置的指针
        while (numsIndex>0){
            if (nums1Index>=0&&nums2Index>=0) nums1[numsIndex--]=nums1[nums1Index]>nums2[nums2Index]?nums1[nums1Index--]:nums2[nums2Index--];
            if (nums1Index<0||nums2Index<0){
                while (numsIndex>=0)
                    nums1[numsIndex--]=nums1Index>=0?nums1[nums1Index--]:nums2[nums2Index--];
                break;
            }
        }
    }
    public static void main(String[] args) {
        MergeSortedArray m=new MergeSortedArray();
        int[] arr1={1,2,3,0,0,0};
        int[] arr2={2,5,6};
        m.merge2(arr1,3,arr2,3);
    }

}
