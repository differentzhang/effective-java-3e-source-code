package effectivejava.chapter6.item39.repeatableannotation;

import effectivejava.chapter6.item39.markerannotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 这个程序的主要逻辑如下：
 *
 * 加载测试类：通过 Class.forName 方法加载传递给 main 方法的类名。
 *
 * 遍历测试方法：遍历这个类中声明的所有方法，检查每个方法是否有 @Test 注解或 @ExceptionTest 及其容器注解。
 *
 * 执行测试：
 *
 * 对于有 @Test 注解的方法，使用反射机制调用这些方法。如果方法正常执行，则认为测试通过；如果方法抛出异常，则认为测试失败，并打印相关信息。
 * 对于有 @ExceptionTest 注解的方法，尝试调用方法并检查是否抛出了注解中指定的异常类型。
 * 统计和打印结果：最后，程序打印出总的测试数、通过的测试数和失败的测试数。
 *
 * 这个测试运行器展示了如何使用反射和自定义注解（包括重复注解）来实现一个简单的测试框架。通过 @Test 和 @ExceptionTest 注解，可以轻松地将任何方法标记为测试方法，并指定期望的测试行为。
 */
// 处理标记注解和可重复注解的程序 (第 187 页)
public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0; // 测试总数
        int passed = 0; // 通过的测试数
        Class testClass = Class.forName(args[0]); // 加载测试类

        // 遍历测试类中声明的所有方法
        for (Method m : testClass.getDeclaredMethods()) {
            // 处理 @Test 注解的方法
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null); // 调用无参数的方法
                    passed++; // 如果方法调用成功，则计为通过
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause(); // 获取实际抛出的异常
                    System.out.println(m + " failed: " + exc); // 打印失败信息
                } catch (Exception exc) {
                    System.out.println("INVALID @Test: " + m);
                }
            }

            // 处理可重复注解 (第 187 页)
            if (m.isAnnotationPresent(ExceptionTest.class)
                    || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;
                try {
                    m.invoke(null); // 调用方法
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] excTests =
                            m.getAnnotationsByType(ExceptionTest.class);
                    for (ExceptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++; // 如果抛出的异常匹配注解中指定的类型，则测试通过
                            break;
                        }
                    }
                    if (passed == oldPassed)
                        System.out.printf("Test %s failed: %s %n", m, exc);
                }
            }
        }
        // 打印测试结果
        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }
}
