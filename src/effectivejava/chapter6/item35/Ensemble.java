package effectivejava.chapter6.item35;

/**
 * 每个 Ensemble 枚举值代表一个不同的乐队组合，如独奏（SOLO）、二重奏（DUET）、三重奏（TRIO）等，每种组合都有一个对应的音乐家数量。枚举的构造函数接受一个整数参数（音乐家的数量），并将其存储在 numberOfMusicians 实例字段中。
 *
 * numberOfMusicians 方法允许外部访问这个数量。例如，Ensemble.SOLO.numberOfMusicians() 将返回 1。
 *
 * 此枚举展示了如何在枚举中存储和访问附加数据，这种方式使得枚举不仅仅是单纯的命名常量，而是可以携带更多有用信息的强类型枚举值。
 */
// 枚举类型，其中存储了整数数据（表示音乐家的数量） (第 168 页)
public enum Ensemble {
    // 定义枚举值，每个值代表不同类型的乐队组合
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians; // 表示该组合中音乐家的数量

    // 枚举的构造函数，用于设置音乐家的数量
    Ensemble(int size) { this.numberOfMusicians = size; }

    // 获取该组合中音乐家的数量
    public int numberOfMusicians() { return numberOfMusicians; }
}
