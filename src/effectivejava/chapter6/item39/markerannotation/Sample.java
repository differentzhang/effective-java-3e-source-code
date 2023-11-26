package effectivejava.chapter6.item39.markerannotation;

/**
 * 在这个类中，@Test 注解被应用于几个静态方法：
 *
 * m1 是一个空方法，使用了 @Test 注解，应被视为一个测试方法且应该通过测试。
 * m3 和 m7 都使用了 @Test 注解，但这些方法抛出了异常，因此它们在测试中应该失败。
 * m5 是一个非静态方法，但错误地使用了 @Test 注解，这是一个无效的使用。
 * 其他没有使用 @Test 注解的方法（m2, m4, m6, m8）不应被视为测试方法。
 *
 * 这个示例展示了如何使用标记注解来定义测试方法，这在编写简单测试框架或进行单元测试时非常有用。这种方式使得测试的指定变得直观且易于管理。
 */
// 包含标记注解的程序 (第 181 页)
public class Sample {
    @Test
    public static void m1() { }        // 测试应该通过

    public static void m2() { }        // 没有 @Test 注解，不是测试方法

    @Test public static void m3() {    // 测试应该失败
        throw new RuntimeException("Boom"); // 抛出异常
    }

    public static void m4() { }        // 没有 @Test 注解，不是测试方法

    @Test public void m5() { }         // 无效使用：非静态方法
    // @Test 注解应只用于静态方法

    public static void m6() { }        // 没有 @Test 注解，不是测试方法

    @Test public static void m7() {    // 测试应该失败
        throw new RuntimeException("Crash"); // 抛出异常
    }

    public static void m8() { }        // 没有 @Test 注解，不是测试方法
}
