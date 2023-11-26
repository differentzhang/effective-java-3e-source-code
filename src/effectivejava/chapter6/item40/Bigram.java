package effectivejava.chapter6.item40;
import java.util.*;

/**
 * 错误的地方是 equals 方法的重写。在 Java 中，正确重写 equals 方法的签名是 public boolean equals(Object obj)。在这段代码中，equals 方法被重载了，而不是被重写，因为它的参数是 Bigram 类型，而不是 Object 类型。
 *
 * 由于 equals 方法没有被正确重写，HashSet 在比较 Bigram 对象时会使用 Object 类的 equals 方法，而不是 Bigram 类中定义的 equals 方法。Object 类的 equals 方法只检查两个引用是否指向同一个对象，这会导致 HashSet 认为所有的 Bigram 实例都是不同的，即使它们有相同的 first 和 second 值。
 */
// Can you spot the bug? (Page 188)
public class Bigram {
    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first  = first;
        this.second = second;
    }

    // 这里的问题在于 equals 方法的签名不正确
    public boolean equals(Bigram b) {
        return b.first == first && b.second == second;
    }

    // hashCode 方法看起来是正确的
    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++)
            for (char ch = 'a'; ch <= 'z'; ch++)
                s.add(new Bigram(ch, ch));
        System.out.println(s.size());
    }
}
/**
 * 正确的 equals 方法重写应该是这样的：
 * 在 main 方法中，程序添加了 26 * 10 = 260 个 Bigram 对象到集合 s 中。如果 equals 和 hashCode 方法都被正确实现，集合的大小应该是 26，因为对于每个字符 ch，new Bigram(ch, ch) 都会产生相同的对象。但由于 equals 方法的错误，HashSet 会将所有这些对象视为不同，导致 s.size() 输出为 260。
 */
/*
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bigram bigram = (Bigram) o;
    return first == bigram.first && second == bigram.second;
}


 */