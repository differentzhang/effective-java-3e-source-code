package effectivejava.chapter6.item39.annotationwitharrayparameter;
import effectivejava.chapter6.item39.markerannotation.Test;

import java.lang.reflect.*;

/**
 * 这个程序的主要逻辑如下：
 *
 * 处理 @Test 注解：对于每个带有 @Test 注解的方法，尝试调用该方法。如果方法正常执行，计算为通过；如果方法抛出异常，则计为失败，并打印失败信息。
 *
 * 处理 @ExceptionTest 注解：对于每个带有 @ExceptionTest 注解的方法，尝试调用该方法。如果方法没有抛出异常或者抛出的异常类型不在 @ExceptionTest 注解声明的异常列表中，计算为失败，并打印失败信息。如果抛出的异常匹配 @ExceptionTest 注解声明的异常类型之一，则计算为通过。
 *
 * 这个测试运行器展示了如何使用反射来处理自定义注解，从而实现一个简单的测试框架。通过使用 @Test 和 @ExceptionTest 注解，可以轻松地标记要测试的方法以及期望的测试行为。
 */
// 程序处理标记注解和带有数组参数的注解 (第 185 页)
public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0; // 测试总数
        int passed = 0; // 通过的测试数
        Class<?> testClass = Class.forName(args[0]); // 加载测试类

        // 遍历测试类中的所有方法
        for (Method m : testClass.getDeclaredMethods()) {
            // 处理 @Test 注解的方法
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null); // 调用无参数的方法
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " failed: " + exc);
                } catch (Exception exc) {
                    System.out.println("Invalid @Test: " + m);
                }
            }

            // 处理带有数组参数的 @ExceptionTest 注解 (第 185 页)
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null); // 调用方法
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;
                    Class<? extends Throwable>[] excTypes =
                            m.getAnnotation(ExceptionTest.class).value();
                    for (Class<? extends Throwable> excType : excTypes) {
                        if (excType.isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed)
                        System.out.printf("Test %s failed: %s %n", m, exc);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }
}
