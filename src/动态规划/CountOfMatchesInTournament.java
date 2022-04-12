package 动态规划;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/1/25 15:59<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/count-of-matches-in-tournament/
 * 比赛中的配对次数
 */
// 输入：n = 14
//输出：13
//解释：比赛详情：
//- 第 1 轮：队伍数 = 14 ，配对次数 = 7 ，7 支队伍晋级。
//- 第 2 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
//- 第 3 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
//- 第 4 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
//总配对次数 = 7 + 3 + 2 + 1 = 13

/**
 * 解释：当为偶数 f(n)=f(n/2)+n/2
 * 当为奇数 f(n)=f((i-1)/2+1)+(i-1)/2;
 */
public class CountOfMatchesInTournament {
    public int numberOfMatches(int n) {
        if (n==1){
            return 0;
        }
        if (n==2){
            return 1;
        }
        int[] arr=new int[n+1];
        for (int i = 2; i < arr.length; i++) {
            arr[i]=i%2==0?arr[i/2]+i/2:arr[(i-1)/2+1]+(i-1)/2;
        }
        return  arr[n];
    }

    public static void main(String[] args) {
        CountOfMatchesInTournament c=new CountOfMatchesInTournament();
        c.numberOfMatches(14);
    }
}
