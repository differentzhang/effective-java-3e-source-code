package effectivejava.chapter2.item6;
import java.util.regex.Pattern;

/**
 * isRomanNumeralSlow 方法在每次调用时都会创建一个新的 Pattern 对象，这是一个性能上的开销。
 * 相比之下，isRomanNumeralFast 方法通过使用一个静态的、预编译的 Pattern 对象，避免了这种开销。
 * 在 main 方法中，通过多次运行这两个方法并计时，
 * 可以观察到使用预编译 Pattern 的性能优势。这个例子展示了在性能敏感的应用中重用昂贵对象的重要性。
 */
// 重用昂贵对象以提高性能 (第 22 和 23 页)
public class RomanNumerals {
    // 性能可以大幅提升! (第 22 页)
    // 检查字符串是否为有效的罗马数字的较慢方法
    static boolean isRomanNumeralSlow(String s) {
        // 每次调用时都创建一个新的 Pattern 对象
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // 重用昂贵对象以提高性能 (第 23 页)
    // 创建一个静态的 final Pattern 对象，用于复用
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    // 检查字符串是否为有效的罗马数字的较快方法
    static boolean isRomanNumeralFast(String s) {
        // 使用预编译的 Pattern 对象
        return ROMAN.matcher(s).matches();
    }

    public static void main(String[] args) {
        int numSets = Integer.parseInt(args[0]); // 测试集数量
        int numReps = Integer.parseInt(args[1]); // 每个测试集的重复次数
        boolean b = false;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime(); // 开始时间
            for (int j = 0; j < numReps; j++) {
                // 使用慢方法（将 Slow 替换为 Fast 可以看到性能差异）
                b ^= isRomanNumeralSlow("MCMLXXVI");
            }
            long end = System.nanoTime(); // 结束时间
            System.out.println(((end - start) / (1_000. * numReps)) + " μs."); // 打印每次操作的平均时间
        }

        // 防止虚拟机优化掉所有操作。
        if (!b)
            System.out.println();
    }
}
