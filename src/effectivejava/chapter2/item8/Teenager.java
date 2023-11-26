package effectivejava.chapter2.item8;

import java.util.concurrent.TimeUnit;

/**
 * Teenager 类的 main 方法中创建了一个 Room 实例，但没有保存对它的引用。
 * 这意味着该实例可能很快就会成为垃圾回收的目标。因为 Room 类实现了自动清理机制（通过 Cleaner），
 * 所以理论上它会在垃圾收集时自动清理资源。然而，依赖垃圾收集来清理资源是危险的，
 * 因为 Java 虚拟机不保证垃圾收集器何时运行。
 *
 * 在注释的 System.gc() 调用中显示了一种尝试强制垃圾收集器运行的方式。
 * 但在实际应用中，强制垃圾回收并不是一个好的做法，因为它对具体的垃圾收集器实现和运行环境的行为有依赖，
 * 可能会导致不可预测的结果。最佳实践是显式管理资源，例如通过使用 try-with-resources 语句来确保资源的及时清理。
 */
// 使用清理器安全网的资源的不良客户端 (第 33 页)
public class Teenager {
    public static void main(String[] args) {
        // 创建 Room 实例但不保存引用
        new Room(99);
        System.out.println("Peace out");

        // 取消注释下一行并重新测试行为，但请注意，你不应该依赖这种行为！
        // System.gc(); // 显式调用垃圾收集器
    }
}
