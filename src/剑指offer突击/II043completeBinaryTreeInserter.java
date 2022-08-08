package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/8 17:56<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/complete-binary-tree-inserter/
 *    	[剑指 Offer II 043]往完全二叉树添加节点
 */
public class II043completeBinaryTreeInserter {

}
class CBTInserter {
    TreeNode root;
    Deque<TreeNode> deque;

    public CBTInserter(TreeNode root) {
        this.root = root;
        deque = new LinkedList();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);

        // BFS to populate deque
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null || node.right == null)
                deque.offerLast(node);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    public int insert(int v) {
        TreeNode node = deque.peekFirst();
        deque.offerLast(new TreeNode(v));
        if (node.left == null){
            node.left = deque.peekLast();
        }else {
            node.right = deque.peekLast();
            deque.pollFirst();
        }

        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
    //一维数组构建二叉树
    public static TreeNode buildTree(Integer[] arr, int i) // 初始时,传入的i==1
    {
        //TreeNode root = null; // 定义根节点
        if (i > arr.length) {// i >= arr.length 时,表示已经到达了根节点
            return null;
        }
        if(arr[i-1] == null){
            return null;
        }
        TreeNode root = new TreeNode(arr[i-1]); // 根节点
        root.left = buildTree(arr, 2*i); // 递归建立左孩子结点
        root.right = buildTree(arr, 2*i+1); // 递归建立右孩子结点
        return root;
    }

    public static void main(String[] args) {
        TreeNode root=buildTree(new Integer[]{1,2,3,4,5,6},1);
        CBTInserter c=new CBTInserter(root);

    }
}

 class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
     this.right = right;
  }
}

