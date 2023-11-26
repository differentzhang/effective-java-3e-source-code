package effectivejava.chapter6.item34;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Operation 枚举定义了四种基本的数学运算：加法 (PLUS)、减法 (MINUS)、乘法 (TIMES) 和除法 (DIVIDE)。每个枚举常量都有自己的 apply 方法实现，用于执行相应的运算。
 *
 * 枚举的构造函数接收一个符号字符串（如 "+"），并将其存储在 symbol 字段中。toString 方法被覆盖以返回这个符号。
 *
 * fromString 方法是一个静态方法，它允许通过符号字符串查找相应的枚举常量。这是通过创建一个从符号到枚举常量的映射来实现的。
 *
 * main 方法演示了如何使用 Operation 枚举：它读取两个命令行参数作为操作数，然后对每个枚举常量应用这些操作数，并打印结果。这个例子展示了枚举如何提供比普通类更多的功能和灵活性。
 */
// 具有常量特定类主体和数据的枚举类型 (第 163-164 页)
public enum Operation {
    // 定义四个枚举常量，每个都有自己的 apply 方法实现
    PLUS("+") {
        public double apply(double x, double y) { return x + y; } // 加法操作
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; } // 减法操作
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; } // 乘法操作
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; } // 除法操作
    };

    private final String symbol; // 每个枚举常量关联的符号

    // 枚举构造函数
    Operation(String symbol) { this.symbol = symbol; }

    @Override public String toString() { return symbol; } // 覆盖 toString 方法

    // 抽象方法，由每个枚举常量具体实现
    public abstract double apply(double x, double y);

    // 在枚举类型上实现 fromString 方法 (第 164 页)
    private static final Map<String, Operation> stringToEnum =
            Stream.of(values()).collect(
                    toMap(Object::toString, e -> e)); // 将符号映射到枚举常量

    // 根据字符串返回相应的 Operation，如果存在的话
    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]); // 从命令行参数获取第一个数
        double y = Double.parseDouble(args[1]); // 从命令行参数获取第二个数
        for (Operation op : Operation.values())
            // 遍历枚举常量并应用数学运算
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }
}
