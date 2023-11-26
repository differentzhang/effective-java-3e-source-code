package effectivejava.chapter2.item4;

/**
 * UtilityClass 被设计为一个不可实例化的工具类。
 * 它通过将构造函数设置为私有并在其中抛出 AssertionError 来确保无法在类的外部（也尽可能地防止在类的内部）创建其实例。
 * 这种设计模式常用于只包含静态方法和静态字段的类，例如工具类或常量类。
 * 通过这种方式，可以明确地表达这个类的设计意图，即它不应被实例化。
 */
// 不可实例化的工具类 (第 19 页)
public class UtilityClass {
    // 通过私有构造函数阻止类的实例化
    private UtilityClass() {
        // 抛出断言错误，防止在类内部意外调用构造函数
        throw new AssertionError();
    }

    // 其余部分省略
}
