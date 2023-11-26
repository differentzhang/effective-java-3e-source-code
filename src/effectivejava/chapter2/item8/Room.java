package effectivejava.chapter2.item8;

import java.lang.ref.Cleaner;

/**
 * Room 类代表一个需要清理的资源。它内部有一个静态内部类 State，
 * 该类实现了 Runnable 接口，用于执行清理操作。Room 类在构造函数中创建 State 实例，
 * 并将其注册到 Cleaner 对象。当 Room 实例不再被使用时，
 * 如果没有显式调用 close 方法，Cleaner 会自动调用 State 的 run 方法进行清理，这样可以防止资源泄漏。
 *
 * 这种模式在处理必须清理的资源时非常有用，尤其是在客户端忘记调用关闭方法的情况下，Cleaner 提供了一种安全网，
 * 确保资源最终被清理。然而，依赖于清理器或者垃圾回收进行资源清理通常不是最佳实践，因为垃圾回收的时间是不确定的。
 * 最好的做法是显式地管理资源，比如使用 try-with-resources 语句来确保及时清理。
 */
// 使用清理器作为安全网的自动关闭类 (第 32 页)
public class Room implements AutoCloseable {
    // 创建一个 Cleaner 实例
    private static final Cleaner cleaner = Cleaner.create();

    // 需要清理的资源。这个类不能引用 Room！
    private static class State implements Runnable {
        int numJunkPiles; // 这个房间中的垃圾堆数量

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // 由 close 方法或 cleaner 调用
        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0; // 清理房间
        }
    }

    // 这个房间的状态，与我们的清理对象共享
    private final State state;

    // 我们的清理对象。当它适合进行垃圾回收时，会清理房间
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state); // 注册清理对象
    }

    @Override
    public void close() {
        cleanable.clean(); // 在关闭时清理
    }
}
