package effectivejava.chapter6.item36;

import java.util.*;

/**
 * Text 类有一个方法 applyStyles，它接受一个 Set 类型的参数，其中包含 Style 枚举的实例。Style 枚举定义了不同的文本样式，如粗体（BOLD）、斜体（ITALIC）等。
 *
 * 在 main 方法中，创建了一个 Text 实例，并调用 applyStyles 方法。这里使用了 EnumSet.of(Style.BOLD, Style.ITALIC) 来创建一个包含特定样式的集合。EnumSet 是专为枚举设计的高效集合实现，它在内部使用位向量来表示枚举常量集合，因此在性能和内存使用上都优于普通的 HashSet。
 *
 * 这种方法不仅使得代码更加清晰和易于维护，而且利用了 EnumSet 的性能优势，是处理枚举集合的推荐方式。
 */
// EnumSet - 用于替代位字段的现代解决方案 (第 170 页)
public class Text {
    // 定义枚举类型，表示文本样式
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // 应用一组样式到文本上
    // 虽然可以传入任何 Set 类型，但使用 EnumSet 最为合适
    public void applyStyles(Set<Style> styles) {
        // 输出应用的样式
        System.out.printf("Applying styles %s to text%n",
                Objects.requireNonNull(styles));
    }

    // 示例用法
    public static void main(String[] args) {
        Text text = new Text();
        // 使用 EnumSet 来指定要应用的样式
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
