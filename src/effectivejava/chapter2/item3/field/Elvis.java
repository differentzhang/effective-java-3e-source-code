package effectivejava.chapter2.item3.field;

/**
 * Elvis 类中的 public static final Elvis INSTANCE 字段代表了类的唯一实例。
 * 私有构造函数确保了外部代码不能通过 new Elvis() 创建类的另一个实例。
 * 通过将 INSTANCE 字段设置为公共和静态的，可以直接通过 Elvis.INSTANCE 在任何地方访问这个单例实例。
 * 在 main 方法中，我们通过 Elvis.INSTANCE 获取单例实例，
 * 并调用其方法来展示其功能。这种方法在功能上类似于枚举单例模式，
 * 但它不提供枚举类型的序列化和防止多次实例化的保障。
 */
// 使用公共最终字段的单例 (第 17 页)
public class Elvis {
    // 单例实例
    public static final Elvis INSTANCE = new Elvis();

    // 私有构造函数，防止外部通过 new 创建实例
    private Elvis() { }

    // 示例方法
    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!"); // 打印消息
    }

    // 这段代码通常会放在类外部
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE; // 获取单例实例
        elvis.leaveTheBuilding();     // 调用方法
    }
}
