package effectivejava.chapter6.item39.annotationwitharrayparameter;

import java.util.*;

/**
 * 在这个示例中：
 *
 * m1 方法抛出了 ArithmeticException，因此当使用 @ExceptionTest(ArithmeticException.class) 注解时，测试应该通过。
 * m2 方法抛出了 ArrayIndexOutOfBoundsException，而不是注解中指定的 ArithmeticException，因此测试应该失败。
 * m3 方法没有抛出任何异常，因此当使用 @ExceptionTest(ArithmeticException.class) 注解时，测试应该失败。
 * doublyBad 方法被注解 @ExceptionTest 标记为可能抛出 IndexOutOfBoundsException 或 NullPointerException。由于 list.addAll(5, null) 可能会抛出这些异常之一，所以当这些异常之一被抛出时，测试应该通过。
 * 这个类展示了 @ExceptionTest 注解的灵活性，它可以指定单个异常类型或异常类型的数组。这种方法对于编写单元测试特别有用，尤其是当你想验证方法是否如预期那样抛出了特定的异常。
 */
// 包含带数组参数的注解的程序 (第 185 页)
public class Sample3 {
    // 这个变体可以处理注解的参数是单个元素的情况（与第 183 页上的相同）
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {  // 测试应该通过
        int i = 0;
        i = i / i; // 这里会产生 ArithmeticException
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m2() {  // 应该失败（抛出的是错误的异常）
        int[] a = new int[0];
        int i = a[1]; // 这里会产生 ArrayIndexOutOfBoundsException
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m3() { }  // 应该失败（没有异常）

    // 包含带数组参数的注解的代码 (第 185 页)
    @ExceptionTest({ IndexOutOfBoundsException.class,
            NullPointerException.class })
    public static void doublyBad() {   // 应该通过
        List<String> list = new ArrayList<>();

        // 根据规范，这个方法可能抛出 IndexOutOfBoundsException 或 NullPointerException
        list.addAll(5, null); // 这里可能抛出异常
    }
}
