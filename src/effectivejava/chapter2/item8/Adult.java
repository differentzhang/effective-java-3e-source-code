package effectivejava.chapter2.item8;

/**
 * Room 类可能实现了 AutoCloseable 接口，
 * 这使得它可以在 try-with-resources 语句中使用。这种语句不仅简化了代码（避免了显式的关闭资源代码），
 * 而且还提供了更好的异常处理机制。当控制离开 try 块时，即使是因为异常或早期返回，资源 myRoom 也会被自动关闭。
 * 这个模式是处理需要清理的资源（如文件、数据库连接等）的推荐方法，因为它减少了内存泄漏和其他资源管理错误的风险。
 */
// 正确管理资源并使用清理器作为安全网的客户端 (第 33 页)
public class Adult {
    public static void main(String[] args) {
        // 使用 try-with-resources 语句确保资源（Room）被正确关闭
        try (Room myRoom = new Room(7)) {
            // 创建 Room 实例并在 try 块结束时自动关闭
            System.out.println("Goodbye");
        }
        // myRoom 在这里被自动关闭，触发任何必要的清理操作
    }
}
