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
