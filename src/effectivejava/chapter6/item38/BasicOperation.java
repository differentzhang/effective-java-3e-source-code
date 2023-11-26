package effectivejava.chapter6.item38;

/**
 * 每个枚举值（PLUS, MINUS, TIMES, DIVIDE）代表一种基本数学运算。每个值都有一个 apply 方法的实现，用于执行相应的数学运算。
 *
 * 这种方法利用了接口的灵活性来模拟可扩展枚举。虽然 Java 的枚举类型本身不是可扩展的（你不能继承一个枚举类型来扩展它），但你可以通过实现接口的方式来达到类似的效果。在这种情况下，Operation 接口可以被不同的枚举类型实现，这些枚举类型可以定义自己特定的运算行为。
 *
 * 请注意，这段代码的完整实现可能还包括 Operation 接口的定义，该接口包含 apply 方法的声明。
 */
// 使用接口模拟可扩展枚举 - 基本实现 (第 176 页)
public enum BasicOperation implements Operation {
    // 定义枚举实例，代表基本的数学运算
    PLUS("+") {
        public double apply(double x, double y) { return x + y; } // 加法实现
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; } // 减法实现
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; } // 乘法实现
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; } // 除法实现
    };

    private final String symbol; // 符号，用于表示运算

    // 枚举构造函数
    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override public String toString() {
        return symbol; // 覆盖 toString 方法，返回运算符号
    }
}
