package effectivejava.chapter6.item34;

/**
 * Operation 是一个枚举类型，包含四种基本数学运算：加法 (PLUS)、减法 (MINUS)、乘法 (TIMES) 和除法 (DIVIDE)。
 * inverse 方法接收一个 Operation 枚举值，并返回该运算的逆运算。例如，加法的逆运算是减法。
 *
 * 在 main 方法中，程序读取两个命令行参数作为数学运算的操作数，然后遍历 Operation 枚举的所有值。
 * 对于每个操作，它计算该操作及其逆操作的结果，最后将这些信息打印到控制台。
 * 这种方式展示了如何使用枚举和 switch 语句来模拟更复杂的行为，同时保持代码的清晰和易于管理。
 */
// 使用枚举进行 switch 操作，以模拟缺失的方法 (第 167 页)
public class Inverse {
    // 根据提供的操作返回相反的操作
    public static Operation inverse(Operation op) {
        switch(op) {
            case PLUS:   return Operation.MINUS;  // 加法的逆运算是减法
            case MINUS:  return Operation.PLUS;   // 减法的逆运算是加法
            case TIMES:  return Operation.DIVIDE; // 乘法的逆运算是除法
            case DIVIDE: return Operation.TIMES;  // 除法的逆运算是乘法

            default:  throw new AssertionError("Unknown op: " + op); // 如果操作未知，则抛出异常
        }
    }

    public static void main(String[] args) {
        // 从命令行参数获取两个数
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        // 遍历所有的操作
        for (Operation op : Operation.values()) {
            Operation invOp = inverse(op); // 获取每个操作的逆运算
            // 打印结果：先执行操作，然后执行其逆运算，应得到原始值
            System.out.printf("%f %s %f %s %f = %f%n",
                    x, op, y, invOp, y, invOp.apply(op.apply(x, y), y));
        }
    }
}
