package 排序算法;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/4/13
 **/

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public int[] sort(int[] sourceArray)   {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return quickSort(arr, 0, arr.length - 1);
    }
    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }
    private int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void quickSort2(int[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition2(array, begin, end);
        quickSort2(array, begin, pivot - 1);
        quickSort2(array, pivot + 1, end);
    }
    static int partition2(int[] a, int begin, int end) {
        // pivot: 标杆位置，counter: 小于pivot的元素的个数 
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (a[i] < a[pivot]) {
                int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
                counter++;
            }
        }
        int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
        return counter;
    }

    public static void main(String[] args) {
        QuickSort q=new QuickSort();
        int[] arr=new int[]{10,9,8,6,2,4,5,8,11,3,5};
        q.sort(arr);
    }
}
