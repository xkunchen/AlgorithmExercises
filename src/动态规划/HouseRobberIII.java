package 动态规划;

import java.util.HashMap;
import java.util.Map;

//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
// 示例 1:
// 输入: [3,2,3,null,3,null,1]
//     3
//    / \
//   2   3
//    \   \
//     3   1
//输出: 7
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
// 示例 2:
// 输入: [3,4,5,1,3,null,1]
//     3
//    / \
//   4   5
//  / \   \
// 1   3   1
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// Related Topics 树 深度优先搜索
// 👍 587 👎 0
class TreeNode {
    int val;

    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
public class HouseRobberIII {
    //第一遍看答案，官方题解,自上而下
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    public int rob4(TreeNode root) {
        int[] rootStatus = dfs2(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs2(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs2(node.left);
        int[] r = dfs2(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

    //第二种解法,自上而下
    Map<TreeNode, Integer> memo = new HashMap<>();
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        // 利用备忘录消除重叠子问题
        if (memo.containsKey(root))
            return memo.get(root);
        // 抢，然后去下下家
        int do_it = root.val
                + (root.left == null ?
                0 : rob2(root.left.left) + rob2(root.left.right))
                + (root.right == null ?
                0 : rob2(root.right.left) + rob2(root.right.right));
        // 不抢，然后去下家
        int not_do = rob2(root.left) + rob2(root.right);

        int res = Math.max(do_it, not_do);
        memo.put(root, res);
        return res;
    }
    //第三种解法,自上而下
    int rob3(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    /* 返回一个大小为 2 的数组 arr
    arr[0] 表示不抢 root 的话，得到的最大钱数
    arr[1] 表示抢 root 的话，得到的最大钱数 */
    int[] dp(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        // 抢，下家就不能抢了
        int rob = root.val + left[0] + right[0];
        // 不抢，下家可抢可不抢，取决于收益大小
        int not_rob = Math.max(left[0], left[1])
                + Math.max(right[0], right[1]);

        return new int[]{not_rob, rob};
    }

    //一维数组构建二叉树
    public static  TreeNode buildTree(Integer[] arr, int i) // 初始时,传入的i==1
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
        HouseRobberIII houseRobberIII=new HouseRobberIII();
        Integer arr[]={3,4,5,1,3,null,1};
        TreeNode treeNode = buildTree(arr, 1);
        houseRobberIII.rob3(treeNode);
    }
}
