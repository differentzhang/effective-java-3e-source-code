package effectivejava.chapter6.item39.markerannotation;
import java.lang.annotation.*;

// 标记注解类型声明 (第 180 页)
/**
 * 在这个注解定义中：
 *
 * @Retention(RetentionPolicy.RUNTIME) 指定了注解的保留策略是在运行时。这意味着这个注解在运行时是可见的，可以通过反射机制被读取。
 *
 * @Target(ElementType.METHOD) 指定了这个注解只能用于方法。
 *
 * 由于 Test 是一个标记注解，它没有定义任何方法。标记注解的主要用途是通过其存在与否来提供信息。在这个例子中，Test 注解的存在表示一个方法是测试方法，应该由测试运行器执行。
 *
 * 此注解应该只用于无参数的静态方法。这通常是编写单元测试时的一个常见约定，确保测试方法可以独立于测试类的实例运行。
 */

/**
 * 表明被注解的方法是一个测试方法。
 * 只应该用于无参数的静态方法。
 */
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时保留
@Target(ElementType.METHOD)         // 注解只能应用于方法
public @interface Test {
}
