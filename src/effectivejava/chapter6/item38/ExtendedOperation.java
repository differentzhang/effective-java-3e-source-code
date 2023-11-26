package effectivejava.chapter6.item38;
import java.util.*;

/**
 * 在这个例子中，ExtendedOperation 枚举扩展了 BasicOperation 枚举中的操作。它包括额外的数学运算：指数 (EXP) 和取余 (REMAINDER)。每个枚举值都有自己的 apply 方法实现来执行相应的运算。
 *
 * main 方法演示了如何使用 ExtendedOperation 枚举：它创建一个包含所有 ExtendedOperation 常量的集合，并遍历这个集合，应用每个操作到提供的 x 和 y 参数上，然后打印出结果。
 *
 * 这个示例展示了如何使用接口和枚举来模拟可扩展枚举，同时保持类型安全和枚举的其他优点，如实例控制。通过将枚举常量作为一组操作的集合进行操作，可以有效地组织和使用枚举定义的操作。
 */
// 模拟可扩展枚举 (第 176-179 页)
public enum ExtendedOperation implements Operation {
    // 定义枚举实例，代表扩展的数学运算
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y); // 指数运算
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y; // 取余运算
        }
    };
    private final String symbol; // 运算符号

    // 枚举构造函数
    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override public String toString() {
        return symbol; // 覆盖 toString 方法，返回运算符号
    }

    // 使用集合实例来表示一组扩展枚举 (第 178 页)
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]); // 从命令行参数获取第一个数
        double y = Double.parseDouble(args[1]); // 从命令行参数获取第二个数
        test(Arrays.asList(ExtendedOperation.values()), x, y); // 测试扩展运算
    }
    private static void test(Collection<? extends Operation> opSet,
                             double x, double y) {
        for (Operation op : opSet)
            // 遍历操作集合，对每个操作应用 x 和 y 并打印结果
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }
}
