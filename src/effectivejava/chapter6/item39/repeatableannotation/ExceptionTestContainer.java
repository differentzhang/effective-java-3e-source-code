package effectivejava.chapter6.item39.repeatableannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在这个注解定义中：
 *
 * @Retention(RetentionPolicy.RUNTIME) 指定了注解的保留策略是在运行时。这意味着这个注解在运行时是可见的，可以通过反射机制被读取。
 *
 * @Target(ElementType.METHOD) 指定了这个注解只能用于方法。
 *
 * value() 方法返回一个 ExceptionTest 数组，表示存储在该容器注解中的 ExceptionTest 注解实例。
 *
 * 当在一个方法上重复使用 ExceptionTest 注解时，这些注解实例被存储在 ExceptionTestContainer 注解中。这允许在单个方法上指定多个期望抛出的异常类型，每个类型对应一个 ExceptionTest 注解。
 *
 * 这种容器注解的模式是 Java 8 引入的，用于支持重复注解的使用。在处理注解时，尤其是通过反射获取注解信息时，需要考虑到可能存在容器注解。
 */
// 重复注解 ExceptionTest 的容器注解 (第 186 页)
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时保留
@Target(ElementType.METHOD)         // 注解只能应用于方法
public @interface ExceptionTestContainer {
    ExceptionTest[] value(); // 存储多个 ExceptionTest 注解实例
}
