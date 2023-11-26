package effectivejava.chapter6.item39.annotationwithparameter;
import effectivejava.chapter6.item39.annotationwithparameter.ExceptionTest;

import java.util.*;

/**
 * m1 方法抛出了 ArithmeticException，因此当使用 @ExceptionTest(ArithmeticException.class) 注解时，测试应该通过。
 * m2 方法抛出了 ArrayIndexOutOfBoundsException，而不是注解中指定的 ArithmeticException，因此测试应该失败。
 * m3 方法没有抛出任何异常，因此当使用 @ExceptionTest(ArithmeticException.class) 注解时，测试应该失败。
 * 这个类展示了 @ExceptionTest 注解的用法，它可以指定单个异常类型。这种方法对于编写单元测试特别有用，尤其是当你想验证方法是否如预期那样抛出了特定的异常。
 */
// 包含带参数注解的程序 (第 183 页)
public class Sample2 {
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
}
