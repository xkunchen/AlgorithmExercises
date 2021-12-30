package 桶排序;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2021/11/2 15:19<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *  前 K 个高频元素
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "A");
        map.put("3", "C");
        map.put("2", "B");
        map.put("10", "G");
        map.put("11", "H");

        List<String> list = map.entrySet().stream()
                .sorted(Comparator.comparing((Map.Entry<String, String> entry) -> Integer.parseInt(entry.getKey())))
                .map(x -> x.getValue())
                .collect(Collectors.toList());
        list = list.subList(0, 3);
        System.out.println(list);
    }
}
