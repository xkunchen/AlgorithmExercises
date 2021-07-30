package 博客;

import java.util.Random;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/29
 **/
//面试题目：如何在10亿个整数中找出前1000个最大的数。
public class TopN {
        public static int N = 10;           //Top10
        public static int LEN = 100000000; //1亿个整数
        public static int arrs[] =  new int[LEN];
        public static int arr[] = new int[N];
        //数组长度
        public static int len = arr.length;
        //堆中元素的有效元素 heapSize<=len
        public static int heapSize = len;
        public static void main(String[] args) {
            //生成随机数组
            for(int i = 0;i<LEN;i++){
                arrs[i] = new Random().nextInt(999999999);
            }

            //构建初始堆
            for(int i =  0;i<N;i++){
                arr[i] = arrs[i];
            }
            //构建小顶堆
            long start =System.currentTimeMillis();
            buildMinHeap();
            for(int i = N;i<LEN;i++){
                if(arrs[i] > arr[0]){
                    arr[0] = arrs[i];
                    minHeap(0);
                }
            }
            System.out.println(LEN+"个数，求Top"+N+"，耗时"+(System.currentTimeMillis()-start)+"毫秒");
            print();
        }


        /**
         * 自底向上构建小堆
         */
        public static void buildMinHeap(){
            int size = len / 2;
            for(int i = size;i>=0;i--){
                minHeap(i);
            }
        }

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

        /**
         * 返回i节点的右孩子
         * @param i
         * @return
         */
        public static int right(int i){
            return 2*i+1;
        }
        /**
         * 打印
         */
        public  static void print(){
            for(int a:arr){
                System.out.print(a+",");
            }
            System.out.println();
        }
}
