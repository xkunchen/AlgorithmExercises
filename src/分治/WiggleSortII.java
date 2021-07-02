package 分治;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/2
 **/

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/wiggle-sort-ii/
 * 摆动排序 II
 */
//1 1 1 4 5 6    1 6 1 5 1 4
public class WiggleSortII {
    int n=-1;
    public void wiggleSort(int[] nums) {
        //找到中位数索引
        int midIndex = this.quickSelect(nums,0,nums.length-1);
        //找到中位数
        int mid = nums[midIndex];
        n=nums.length;
        //三分法
        for(int i=0,j=0,k=nums.length-1;j<=k;){
            System.out.println("j为"+j+";值为"+V(j));
            if(nums[V(j)]>mid){
                swap(nums,V(j++),V(i++));
            }else if(nums[V(j)]<mid){
                swap(nums,V(j),V(k--));
            }else{
                j++;
            }
        }
    }
    public int V(int i){
        //n|1偶数+1
        return (1+2*(i)) % (n|1);
    }

    public int quickSelect(int[] nums,int left,int right){
        int pivot = nums[left];
        int l = left;
        int r = right;
        while(l<r){
            while(l<r&&nums[r]>=pivot){
                r--;
            }
            if(l<r){
                nums[l++]=nums[r];
            }
            while(l<r&&nums[l]<=pivot){
                l++;
            }
            if(l<r){
                nums[r--]=nums[l];
            }
        }
        nums[l]=pivot;
        if(l==nums.length/2){
            return l;
        }else if(l>nums.length/2){
            return this.quickSelect(nums,left,l-1);
        }else{
            return this.quickSelect(nums,l+1,right);
        }
    }
    public void swap(int[] nums,int i,int j){
        int t = nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,5,1,1,6,4,2,4,6,4,3};
        WiggleSortII w=new WiggleSortII();
        w.wiggleSort(arr);
    }
}
