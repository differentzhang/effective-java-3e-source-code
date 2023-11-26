package effectivejava.chapter6.item39.annotationwitharrayparameter;
import java.lang.annotation.*;

/**
 * 在这个注解定义中：
 *
 * @Retention(RetentionPolicy.RUNTIME) 指定了注解的保留策略是在运行时。这意味着这个注解在运行时是可见的，可以通过反射机制被读取。
 *
 * @Target(ElementType.METHOD) 指定了这个注解只能用于方法。
 *
 * value() 方法返回一个 Class<? extends Exception>[] 类型，表示这个注解可以指定一个或多个异常类。注解的使用者可以通过这个属性指定一个方法应该抛出的异常类型。
 *
 * 例如，可以使用 @ExceptionTest 注解来标记一个测试方法，指定该方法应该抛出哪些类型的异常。测试框架可以使用这个信息来验证方法在执行时是否如预期那样抛出了指定的异常。这种类型的注解通常用于编写单元测试。
 */
// 带有数组参数的注解类型 (第 184 页)
@Retention(RetentionPolicy.RUNTIME) // 该注解在运行时保留
@Target(ElementType.METHOD) // 该注解只能应用于方法
public @interface ExceptionTest {
    // 定义一个 value 属性，返回异常类的数组
    // 使用 Class<? extends Exception>[] 类型来允许多个异常类
    Class<? extends Exception>[] value();
}
