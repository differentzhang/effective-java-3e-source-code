package effectivejava.chapter6.item39.repeatableannotation;

import java.util.ArrayList;
import java.util.List;

/**
 * 在这个类中：
 *
 * m1 方法抛出了 ArithmeticException，因此当使用 @ExceptionTest(ArithmeticException.class) 注解时，测试应该通过。
 * m2 方法抛出了 ArrayIndexOutOfBoundsException，而不是注解中指定的 ArithmeticException，因此测试应该失败。
 * m3 方法没有抛出任何异常，因此当使用 @ExceptionTest(ArithmeticException.class) 注解时，测试应该失败。
 * doublyBad 方法被重复注解 @ExceptionTest 标记为可能抛出 IndexOutOfBoundsException 或 NullPointerException。因为 list.addAll(5, null) 可能会抛出这些异常之一，所以当这些异常之一被抛出时，测试应该通过。
 * 这个类展示了如何使用可重复注解 @ExceptionTest 来指定一个方法可能抛出多种类型的异常，每种类型对应一个 ExceptionTest 注解。这种方式特别适合于编写需要验证多种异常的单元测试。
 */
// 包含可重复注解的程序 (第 186 页)
public class Sample4 {
    // 使用 @ExceptionTest 注解并指定 ArithmeticException 类型
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {  // 测试应该通过
        int i = 0;
        i = i / i; // 这里会产生 ArithmeticException
    }

    // 使用 @ExceptionTest 注解并指定 ArithmeticException 类型
    @ExceptionTest(ArithmeticException.class)
    public static void m2() {  // 应该失败（抛出的是错误的异常）
        int[] a = new int[0];
        int i = a[1]; // 这里会产生 ArrayIndexOutOfBoundsException
    }

    // 使用 @ExceptionTest 注解并指定 ArithmeticException 类型
    @ExceptionTest(ArithmeticException.class)
    public static void m3() { }  // 应该失败（没有异常）

    // 包含重复注解的代码 (第 186 页)
    @ExceptionTest(IndexOutOfBoundsException.class)
    @ExceptionTest(NullPointerException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();

        // 根据规范，这个方法可能抛出 IndexOutOfBoundsException 或 NullPointerException
        list.addAll(5, null); // 这里可能抛出异常
    }
}
