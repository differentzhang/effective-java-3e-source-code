package effectivejava.chapter2.item2.builder;

/**
 * 这段代码展示了如何使用 Builder 模式来创建一个 NutritionFacts 对象。
 * Builder 模式使得代码更易于阅读和维护，特别是在处理有很多参数的情况时。
 * 在这个例子中，NutritionFacts 类有很多成员变量，其中一些是必需的（servingSize 和 servings），
 * 而其他则是可选的。通过 Builder 类，用户可以灵活地只设置他们需要的参数。
 */
// Builder 设计模式 (第 13 页)
public class NutritionFacts {
    private final int servingSize;  // 份量大小
    private final int servings;     // 份量数
    private final int calories;     // 卡路里
    private final int fat;          // 脂肪
    private final int sodium;       // 钠
    private final int carbohydrate; // 碳水化合物

    public static class Builder {
        // 必需参数
        private final int servingSize;
        private final int servings;

        // 可选参数 - 初始化为默认值
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        // 设置卡路里并返回 Builder 实例
        public Builder calories(int val) {
            calories = val;
            return this;
        }

        // 设置脂肪并返回 Builder 实例
        public Builder fat(int val) {
            fat = val;
            return this;
        }

        // 设置钠并返回 Builder 实例
        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        // 设置碳水化合物并返回 Builder 实例
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        // 构建并返回 NutritionFacts 实例
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    // NutritionFacts 的私有构造函数，使用 Builder 对象构建实例
    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    // 测试代码：创建一个 NutritionFacts 对象实例
    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
    }
}
