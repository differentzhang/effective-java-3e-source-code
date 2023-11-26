package effectivejava.chapter2.item7;

/**
 * 在 Java 中，创建自定义异常通常是为了提供更明确、更具可读性的错误信息。
 * 在这个例子中，EmptyStackException 直接继承了 IllegalStateException，没有添加任何新的功能或属性，
 * 但它通过其名称明确表达了异常的具体情况，即“空栈”。
 * 在实际应用中，当栈为空且有不恰当的操作尝试访问栈元素时，抛出此异常可以使错误处理更加直观和易于理解。
 */
// 自定义异常，用于表示空栈异常
// (在第 26 页的 Stack 程序中抛出)
public class EmptyStackException extends IllegalStateException {
    // 这个类是一个简单的自定义异常类，继承自 IllegalStateException。
    // 它没有添加任何新的方法或属性，仅通过类名表明这是一个特定于“空栈”情况的异常。
}
