package effectivejava.chapter6.item39.markerannotation;

import java.lang.reflect.*;

/**
 * 这个程序的主要逻辑如下：
 *
 * 加载测试类：通过 Class.forName 方法加载传递给 main 方法的类名。
 *
 * 遍历测试方法：遍历这个类中声明的所有方法，检查每个方法是否有 @Test 注解。
 *
 * 执行测试：对于有 @Test 注解的方法，使用反射机制调用这些方法。如果方法正常执行，则认为测试通过；如果方法抛出异常，则认为测试失败，并打印相关信息。
 *
 * 统计和打印结果：最后，程序打印出总的测试数、通过的测试数和失败的测试数。
 *
 * 这个测试运行器展示了如何使用反射和自定义注解来实现一个简单的测试框架。通过 @Test 注解，可以轻松地将任何方法标记为测试方法。
 */
// 程序处理标记注解 (第 182 页)
public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0; // 总测试数
        int passed = 0; // 通过的测试数
        Class<?> testClass = Class.forName(args[0]); // 加载测试类

        // 遍历测试类中声明的所有方法
        for (Method m : testClass.getDeclaredMethods()) {
            // 如果方法上存在 @Test 注解
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null); // 调用无参数的方法
                    passed++; // 如果方法调用成功，则计为通过
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause(); // 获取实际抛出的异常
                    System.out.println(m + " failed: " + exc); // 打印失败信息
                } catch (Exception exc) {
                    System.out.println("Invalid @Test: " + m); // 处理非预期异常
                }
            }
        }
        // 打印测试结果
        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }
}
