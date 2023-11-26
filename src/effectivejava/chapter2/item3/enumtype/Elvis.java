package effectivejava.chapter2.item3.enumtype;

/**
 * Elvis 枚举代表了单例。枚举类型自动提供序列化机制，并且由 JVM 保证其单例性。
 * 这样，Elvis.INSTANCE 将始终保持单一实例。使用枚举来实现单例模式是一种简单而有效的方式，
 * 它避免了多线程同步问题，也不需要担心序列化和反序列化的问题。
 * 在 main 方法中，我们通过 Elvis.INSTANCE 获取单例实例，并调用其方法来展示其功能。
 */
// 枚举单例 - 首选方法 (第 18 页)
public enum Elvis {
    INSTANCE; // 单例实例

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
