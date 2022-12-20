package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/20 14:32<br/>
 *
 * @author xkunchen<br />
 */
public class Offer017 {

    public int[] printNumbers(int n) {
        int end = (int)Math.pow(10, n) - 1;
        int[] res = new int[end];
        for(int i = 0; i < end; i++){
            res[i] = i + 1;
        }
        return res;
    }

}
