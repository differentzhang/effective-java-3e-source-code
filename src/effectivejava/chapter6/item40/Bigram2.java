package effectivejava.chapter6.item40;

import java.util.HashSet;
import java.util.Set;

/**
 * 在这个修复后的 Bigram2 类中：
 *
 * equals 方法被正确地重写。它首先检查传入的对象是否为 Bigram2 类型，然后比较当前对象和传入对象的 first 和 second 字符是否相同。
 *
 * hashCode 方法根据 first 和 second 字符计算并返回散列码。
 *
 * main 方法用于测试 Bigram2 类。它创建一个 HashSet 并添加了 26 * 10 = 260 个 Bigram2 实例，每个实例由相同的字符 ch 构成。由于 equals 和 hashCode 方法被正确实现，HashSet 正确地处理了重复元素，最终 s.size() 应该输出 26，因为相同的 Bigram2 实例被视为相等。
 */
// 修复后的 Bigram 类 (第 189 页)
public class Bigram2 {
    private final char first;   // 第一个字符
    private final char second;  // 第二个字符

    // 构造函数
    public Bigram2(char first, char second) {
        this.first  = first;
        this.second = second;
    }

    // 正确重写 equals 方法
    @Override public boolean equals(Object o) {
        if (!(o instanceof Bigram2)) // 检查对象是否为 Bigram2 类型
            return false;
        Bigram2 b = (Bigram2) o;
        return b.first == first && b.second == second; // 比较两个对象的 first 和 second 字符
    }

    // hashCode 方法
    public int hashCode() {
        return 31 * first + second; // 计算散列码
    }

    // 测试程序
    public static void main(String[] args) {
        Set<Bigram2> s = new HashSet<>();
        for (int i = 0; i < 10; i++)
            for (char ch = 'a'; ch <= 'z'; ch++)
                s.add(new Bigram2(ch, ch)); // 向集合中添加 Bigram2 对象
        System.out.println(s.size()); // 打印集合的大小
    }
}
