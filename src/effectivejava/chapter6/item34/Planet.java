package effectivejava.chapter6.item34;

/**
 * 每个 Planet 枚举实例都代表一个行星，包含该行星的质量和半径。枚举构造函数使用这些值来计算表面重力，这是基于万有引力公式计算得出的。
 *
 * 该枚举还提供了几个方法来访问这些属性：mass() 返回行星的质量，radius() 返回行星的半径，surfaceGravity() 返回行星的表面重力。此外，还有一个 surfaceWeight() 方法，它接受一个质量参数，并计算该质量在行星表面的重量。
 *
 * 这个枚举演示了如何在枚举类型中封装数据和行为，使得枚举不仅仅是简单的常量集合，而是可以拥有更复杂的结构和功能。
 */
// 带有数据和行为的枚举类型 (第 159-160 页)
public enum Planet {
    // 枚举实例，代表太阳系中的行星，每个行星都有其质量和半径
    MERCURY(3.302e+23, 2.439e6),
    VENUS  (4.869e+24, 6.052e6),
    EARTH  (5.975e+24, 6.378e6),
    MARS   (6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN (5.685e+26, 6.027e7),
    URANUS (8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    private final double mass;           // 质量，单位：千克
    private final double radius;         // 半径，单位：米
    private final double surfaceGravity; // 表面重力，单位：米/秒²

    // 万有引力常数，单位：米³/千克秒²
    private static final double G = 6.67300E-11;

    // 构造函数
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius); // 计算表面重力
    }

    // 获取质量
    public double mass()           { return mass; }
    // 获取半径
    public double radius()         { return radius; }
    // 获取表面重力
    public double surfaceGravity() { return surfaceGravity; }

    // 计算给定质量在行星表面的重量（重量 = 质量 * 表面重力）
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity;  // F = ma
    }
}
