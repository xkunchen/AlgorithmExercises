package 测试;

import java.util.Arrays;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/30
 **/
//花了长时间终于想通树的构建，是从索引为1为节点开始构建树的，可以以为索引为0就是树的根节点
public class Test {
    public static int N = 10;           //Top10
    public static int arr[] ={30,6,232,23,231,56,35,24,642,24,3};
    //数组长度
    public static int len = arr.length;
    //堆中元素的有效元素 heapSize<=len
    public static int heapSize = len;

    /**
     * i节点为根及子树是一个小堆
     * @param i
     */
    public static void minHeap(int i){
        int l = left(i);
        int r = right(i);
        int index = i;
        if(l<heapSize && arr[l]<arr[index]){
            index = l;
        }
        if(r<heapSize && arr[r]<arr[index]){
            index = r;
        }
        if(index != i){
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
            //递归向下构建堆
            minHeap(index);
        }
    }
    /**
     * 返回i节点的左孩子
     * @param i
     * @return
     */
    public static int left(int i){
        return 2*i;
    }

    void testMethod(){
        int size = len / 2;
        for(int i = size;i>=0;i--){
            minHeap(i);
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j]+",");
            }
            System.out.println("");
        }
    }
    /**
     * 返回i节点的右孩子
     * @param i
     * @return
     */
    public static int right(int i){
        return 2*i+1;
    }
    public static void main(String[] args) {
        Test t=new Test();
        t.testMethod();


    }
}
