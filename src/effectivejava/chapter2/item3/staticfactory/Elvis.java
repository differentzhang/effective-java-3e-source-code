package effectivejava.chapter2.item3.staticfactory;

/**
 * Elvis 类使用私有构造函数和一个私有的静态常量 INSTANCE 来维护单例。
 * 外部代码不能直接访问 INSTANCE 字段，而是必须通过 Elvis.getInstance() 静态方法来获取实例。
 * 这种方法的一个优点是，实现单例的逻辑被封装在 getInstance() 方法中，
 * 如果将来需要改变单例的实现方式，只需修改这个方法而不影响外部调用。
 * 此外，如果需要支持延迟加载（lazy loading），这种方法也更为方便。
 * 在 main 方法中，展示了如何通过 Elvis.getInstance() 获取单例实例并调用其方法。
 */
// 使用静态工厂的单例 (第 17 页)
public class Elvis {
    // 单例实例
    private static final Elvis INSTANCE = new Elvis();

    // 私有构造函数，防止外部通过 new 创建实例
    private Elvis() { }

    // 静态工厂方法，返回单例实例
    public static Elvis getInstance() {
        return INSTANCE;
    }

    // 示例方法
    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!"); // 打印消息
    }

    // 这段代码通常会放在类外部
    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance(); // 通过静态工厂方法获取单例实例
        elvis.leaveTheBuilding();          // 调用方法
    }
}
