package effectivejava.chapter6.item37;
import java.util.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

// 使用 EnumMap 将数据与枚举关联 (第 171-173 页)

/**
 * 这个示例中首先展示了一种不推荐的使用数组和 ordinal 方法进行分组的方式。接着展示了使用 EnumMap 进行分组的推荐做法，EnumMap 在这种情况下提供了更好的性能和类型安全性。
 *
 * 最后，示例演示了如何使用 Java 8 流（Stream）API 进行分组。首先是一个“简单”的流式处理，它不保证返回 EnumMap。然后是一个更精细的实现，它显式地使用 EnumMap 来收集结果，确保了更好的性能。
 *
 * 这个例子展示了如何根据枚举类型有效地组织和访问集合数据，同时展示了 Java 8 流 API 的强大功能。
 */
// 表示植物的简单类 (第 171 页)
class Plant {
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL } // 枚举：植物的生命周期

    final String name;          // 植物名称
    final LifeCycle lifeCycle;  // 植物的生命周期

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = {
                new Plant("Basil",    LifeCycle.ANNUAL),
                new Plant("Carroway", LifeCycle.BIENNIAL),
                new Plant("Dill",     LifeCycle.ANNUAL),
                new Plant("Lavendar", LifeCycle.PERENNIAL),
                new Plant("Parsley",  LifeCycle.BIENNIAL),
                new Plant("Rosemary", LifeCycle.PERENNIAL)
        };

        // 使用 ordinal() 方法索引数组 - 不推荐这样做！(第 171 页)
        Set<Plant>[] plantsByLifeCycleArr =
                (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycleArr.length; i++)
            plantsByLifeCycleArr[i] = new HashSet<>();
        for (Plant p : garden)
            plantsByLifeCycleArr[p.lifeCycle.ordinal()].add(p);
        // 打印结果
        for (int i = 0; i < plantsByLifeCycleArr.length; i++) {
            System.out.printf("%s: %s%n",
                    Plant.LifeCycle.values()[i], plantsByLifeCycleArr[i]);
        }

        // 使用 EnumMap 将数据与枚举关联 (第 172 页)
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle =
                new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lc : Plant.LifeCycle.values())
            plantsByLifeCycle.put(lc, new HashSet<>());
        for (Plant p : garden)
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        System.out.println(plantsByLifeCycle);

        // 基于流的方法 - 不太可能产生 EnumMap！(第 172 页)
        System.out.println(Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle)));

        // 使用流和 EnumMap 将数据与枚举关联 (第 173 页)
        System.out.println(Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle,
                        () -> new EnumMap<>(LifeCycle.class), toSet())));
    }
}
