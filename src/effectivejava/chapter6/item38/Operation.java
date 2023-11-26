package effectivejava.chapter6.item38;

/**
 * 在这个接口中，apply 方法是抽象的，意味着任何实现了 Operation 接口的类都必须提供 apply 方法的具体实现。这种设计模式使得可以创建不同的 Operation 实现，每种实现都具有不同的行为。
 *
 * 例如，可以创建枚举类型（如 BasicOperation 和 ExtendedOperation），这些枚举实现了 Operation 接口，并提供了 apply 方法的不同实现来执行具体的数学运算。这样的设计允许在不同的枚举类型之间共享相同的接口，从而实现类似于可扩展枚举的功能。
 *
 * 这种方法的好处在于提供了一种机制，使得你可以定义一组相关的操作，这些操作可以在运行时动态地选择和扩展，同时保持类型安全和枚举的其他优点。
 */
// 使用接口模拟可扩展枚举 (第 176 页)
public interface Operation {
    // 声明一个 apply 方法
    // 接受两个 double 类型的参数，返回一个 double 类型的结果
    double apply(double x, double y);
}
