package 广度与深度优先遍历;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 示例：
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
//
// Related Topics 字符串 回溯算法
// 👍 1255 👎 0

/**
 * 括号生成
 *         https://leetcode-cn.com/problems/generate-parentheses/#/description
 *         树
 *         第一遍
 */

/**
 * 用树的遍历，剪枝最重要
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> allResult=new ArrayList<String>();
        //解法思路，加括号，进行递归
        addBracket("", 0, 0,n,allResult);
        return allResult;
    }

    private void addBracket(String s, int left, int right, int n, List<String> allResult) {
        //两个结果，当左括号和右括号都相等时，且右括号等于n返回
        //当左括号大于n时，结束
        //第一个值应该为（
        //结束条件
        if (left<right||left>n){
            return;
        }
        if (n==right){
            System.out.println(s);
            allResult.add(s);
            return;
        }
        //处理逻辑
        //继续递归
        addBracket(s+"(",left+1,right,n,allResult);
        addBracket(s+")",left,right+1,n,allResult);
    }
    //解法2：动态规划
    ArrayList[] cache = new ArrayList[100];

    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left: generate(c)) {
                    for (String right: generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

    public List<String> generateParenthesis2(int n) {
        return generate(n);
    }
    //解法3：深度优先遍历
    // 做减法

    public List<String> generateParenthesis3(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }
    //解法4：广度优先遍历
    class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }
    public List<String> generateParenthesis4(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }
    public static void main(String[] args) {
        GenerateParentheses g=new GenerateParentheses();
        g.generateParenthesis2(4);
    }
}
