package effectivejava.chapter6.item37;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Transition 枚举表示从一种 Phase （例如 SOLID）到另一种 Phase （例如 LIQUID）的状态转变。它使用两个 EnumMap，一个映射从某个状态出发的所有可能转变，另一个映射到达的每个状态对应的具体转变。例如，从固体到液体的转变是 MELT。
 *
 * main 方法则遍历所有可能的状态组合，并打印出相应的状态转变（如果存在）。例如，它会打印出 "SOLID to LIQUID : MELT"。
 *
 * 这种设计模式展示了如何有效地使用枚举来表示和处理复杂的状态转换逻辑，同时保持代码的清晰和易于维护。通过使用 EnumMap，它也保证了高效的性能。
 */
// 使用嵌套的 EnumMap 来关联枚举对和数据 - (第 174-175 页)
public enum Phase {
    SOLID, LIQUID, GAS; // 枚举值：固体、液体、气体

    public enum Transition {
        // 定义物质状态转变的枚举值
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private final Phase from; // 转变起始状态
        private final Phase to;   // 转变结束状态

        // 枚举的构造函数
        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        // 初始化物质状态转变的映射
        private static final Map<Phase, Map<Phase, Transition>> m = Stream.of(values()).collect(
                groupingBy(t -> t.from, // 按起始状态分组
                        () -> new EnumMap<>(Phase.class), // 使用 EnumMap 优化性能
                        toMap(t -> t.to, t -> t, // 从起始状态到结束状态的映射
                                (x, y) -> y, () -> new EnumMap<>(Phase.class))));

        // 根据起始和结束状态获取转变
        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }

    // 简单的演示程序 - 打印状态转变表
    public static void main(String[] args) {
        for (Phase src : Phase.values()) {
            for (Phase dst : Phase.values()) {
                Transition transition = Transition.from(src, dst);
                if (transition != null)
                    System.out.printf("%s to %s : %s %n", src, dst, transition);
            }
        }
    }
}
