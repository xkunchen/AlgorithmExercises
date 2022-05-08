package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/8 17:02<br/>
 *
 * @author xkunchen<br />
 */
public class II066mapSumPairs {
}

class MapSum {
    private MapSum[] children;
    private int value;

    /** Initialize your data structure here. */
    public MapSum() {
        children = new MapSum[26];
    }

    public void insert(String key, int val) {
        MapSum node = this;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new MapSum();
            }
            node = node.children[index];
        }
        node.value=val;
    }

    private MapSum searchPrefix(String prefix) {
        MapSum node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    public int sum(String prefix) {
        MapSum mapSum = searchPrefix(prefix);
        int sum=0;
        sum+=countSum(mapSum);
        return sum;
    }
    private int countSum(MapSum mapSum){
        int sum=0;
        if (mapSum!=null){
            sum+=mapSum.value;
            for (int i = 0; i < mapSum.children.length; i++) {
                sum+=countSum(mapSum.children[i]);
            }
        }
        return sum;
    }
}
