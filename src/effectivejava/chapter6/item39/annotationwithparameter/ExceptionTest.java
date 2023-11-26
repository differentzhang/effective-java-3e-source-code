package effectivejava.chapter6.item39.annotationwithparameter;

import java.lang.annotation.*;
/**
 * 在这个注解定义中：
 *
 * @Retention(RetentionPolicy.RUNTIME) 指定了注解的保留策略是在运行时。这意味着这个注解在运行时是可见的，可以通过反射机制被读取。
 *
 * @Target(ElementType.METHOD) 指定了这个注解只能用于方法。
 *
 * value() 方法返回一个 Class<? extends Throwable> 类型，表示这个注解可以指定一个方法应该抛出的异常类型。注解的使用者可以通过这个属性指定一个方法在执行时应该抛出哪种类型的异常。
 *
 * 例如，可以使用 @ExceptionTest 注解来标记一个测试方法，指定该方法应该抛出哪种类型的异常。测试框架可以使用这个信息来验证方法在执行时是否如预期那样抛出了指定的异常。这种类型的注解通常用于编写单元测试。
 */

/**
 * 表明被注解的方法是一个测试方法，必须抛出指定的异常才算成功。
 */
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时保留
@Target(ElementType.METHOD)         // 注解只能应用于方法
public @interface ExceptionTest {
    // 定义一个 value 属性，返回异常类的类型
    // 使用 Class<? extends Throwable> 类型允许指定任何异常类型
    Class<? extends Throwable> value();
}
