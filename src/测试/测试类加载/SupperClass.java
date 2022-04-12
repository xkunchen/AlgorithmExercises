package 测试.测试类加载;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/3/2 17:59<br/>
 *
 * @author xkunchen<br />
 */
public class SupperClass {
    public static void main(String[] args) {
        Set<String> result = new HashSet<String>();
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }
        };

        Set<String> set2 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("地下城与勇士");
                add("魔兽世界");
            }
        };

        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        System.out.println("交集：" + result);

        result.clear();
        result.addAll(set1);
        result.removeAll(set2);
        System.out.println("差集：" + result);

        result.clear();
        result.addAll(set1);
        result.addAll(set2);
        System.out.println("并集：" + result);
    }
}
