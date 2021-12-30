package 测试;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2021/11/26 13:59<br/>
 *
 * @author xkunchen<br />
 */
public enum Test3 {
    MONDAY("星期一", 1),
    TUESDAY("星期二", 2),
    WEDNESDAY("星期三", 3),
    THURSDAY("星期四", 4),
    FRIDAY("星期五", 5),
    SATURDAY("星期六", 6),
    SUNDAY("星期日", 7);//记住要用分号结束

    private String desc;//文字描述
    private Integer code; //对应的代码

    /**
     * 私有构造,防止被外部调用
     *
     * @param desc
     */
    private Test3(String desc,Integer code) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 定义方法,返回代码,跟常规类的定义没区别
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    public static void main(String[] args) {
        for (Test3 day : Test3.values()) {
            System.out.println("name:" + day.name() +
                    ",desc:" + day.getDesc());
        }
    }
}
