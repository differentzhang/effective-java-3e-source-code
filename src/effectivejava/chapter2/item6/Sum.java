package effectivejava.chapter2.item6;

import java.util.Comparator;

/**
 * sum 方法中使用了 Long 类的对象 sum 来累加数值，而不是使用 long 基本数据类型。
 * 由于 Long 是一个对象，每次循环中 sum += i 这个操作都会创建一个新的 Long 对象，而这导致了大量不必要的对象创建。
 * 这种不必要的对象创建是造成程序性能极差的主要原因。
 * 在实际编程中，应优先使用基本数据类型而非装箱基本类型，以避免不必要的对象创建和降低性能。
 */
// 性能极差的程序！你能发现对象创建在哪里吗？ (第 24 页)
public class Sum {
    // 计算 0 到 Integer.MAX_VALUE 之和的方法
    private static long sum() {
        Long sum = 0L; // 使用了 Long 对象，而非 long 基本类型
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i; // 每次循环都会创建一个新的 Long 对象
        return sum;
    }

    public static void main(String[] args) {
        int numSets = Integer.parseInt(args[0]); // 测试集数量
        long x = 0;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime(); // 开始时间
            x += sum(); // 调用 sum 方法
            long end = System.nanoTime(); // 结束时间
            System.out.println((end - start) / 1_000_000. + " ms."); // 打印耗时
        }

        // 防止虚拟机优化掉所有操作
        if (x == 42)
            System.out.println();
    }
}
