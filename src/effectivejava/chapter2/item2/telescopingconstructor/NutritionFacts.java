package effectivejava.chapter2.item2.telescopingconstructor;

/**
 * NutritionFacts 类通过提供一系列重载的构造器，
 * 使得用户可以在创建对象时选择设置不同数量的参数。虽然这种方法在参数数量较少时可行，但当参数数量增加时，
 * 递增构造器模式的可读性和易用性会大大降低，特别是在参数多且有多个可选参数时。
 * 这也是为什么在实际开发中，建议使用 Builder 模式来替代递增构造器模式。
 */
// 递增构造器模式 - 扩展性不佳！(第 10-11 页)
public class NutritionFacts {
    private final int servingSize;  // (毫升) 必需
    private final int servings;     // (每容器) 必需
    private final int calories;     // (每份) 可选
    private final int fat;          // (克/份) 可选
    private final int sodium;       // (毫克/份) 可选
    private final int carbohydrate; // (克/份) 可选

    // 提供仅包含必需参数的构造器
    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    // 提供包含一个可选参数的构造器
    public NutritionFacts(int servingSize, int servings,
                          int calories) {
        this(servingSize, servings, calories, 0);
    }

    // 提供包含两个可选参数的构造器
    public NutritionFacts(int servingSize, int servings,
                          int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    // 提供包含三个可选参数的构造器
    public NutritionFacts(int servingSize, int servings,
                          int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    // 提供包含所有参数的构造器
    public NutritionFacts(int servingSize, int servings,
                          int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize  = servingSize;
        this.servings     = servings;
        this.calories     = calories;
        this.fat          = fat;
        this.sodium       = sodium;
        this.carbohydrate = carbohydrate;
    }

    // 测试代码
    public static void main(String[] args) {
        NutritionFacts cocaCola =
                new NutritionFacts(240, 8, 100, 0, 35, 27); // 创建对象实例
    }

}
