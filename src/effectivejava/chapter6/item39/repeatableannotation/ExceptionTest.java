package effectivejava.chapter6.item39.repeatableannotation;

import java.lang.annotation.*;

/**
 * 在这个注解定义中：
 *
 * @Retention(RetentionPolicy.RUNTIME) 指定了注解的保留策略是在运行时。这意味着这个注解在运行时是可见的，可以通过反射机制被读取。
 *
 * @Target(ElementType.METHOD) 指定了这个注解只能用于方法。
 *
 * @Repeatable(ExceptionTestContainer.class) 表明 ExceptionTest 是一个可重复注解，它的值可以在同一个方法上重复多次。ExceptionTestContainer 是一个容器注解，用于在内部存储多个 ExceptionTest 注解实例。
 *
 * value() 方法返回一个 Class<? extends Throwable> 类型，表示这个注解可以指定一个方法应该抛出的异常类型。注解的使用者可以通过这个属性指定一个方法在执行时应该抛出哪种类型的异常。
 *
 * 例如，可以使用 @ExceptionTest 注解来标记一个测试方法，指定该方法应该抛出哪种类型的异常。如果一个方法可能抛出多种类型的异常，并且希望测试这些异常，可以在该方法上重复使用 ExceptionTest 注解。这种类型的注解通常用于编写单元测试。
 */
// 可重复注解类型 (第 186 页)
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时保留
@Target(ElementType.METHOD)         // 注解只能应用于方法
@Repeatable(ExceptionTestContainer.class) // 指定包含注解的容器注解类
public @interface ExceptionTest {
    // 定义一个 value 属性，返回异常类的类型
    // 使用 Class<? extends Throwable> 类型允许指定任何异常类型
    Class<? extends Throwable> value();
}
