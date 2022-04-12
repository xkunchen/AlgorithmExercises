package 测试.测试类加载;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/3/2 18:00<br/>
 *
 * @author xkunchen<br />
 */
public class SubClass   extends SupperClass {
    static { System.out.println("SubClass init!"); }
}

class NotInitialization2 {
    public static void main(String[] args) {
        SupperClass[] sca = new SupperClass[10];
        System.out.println(sca);
    }
}
