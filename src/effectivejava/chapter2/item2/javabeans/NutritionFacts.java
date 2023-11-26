package effectivejava.chapter2.item2.javabeans;

/**
 * NutritionFacts 类使用 JavaBeans 模式进行设计。该类提供了一系列的 setter 方法来设置各种营养成分的值。
 * 使用 JavaBeans 模式可以使得对象的创建过程更加灵活，但也使得对象可能在构建过程中处于不一致的状态，
 * 因为它允许对象在设置完全部属性之前就被使用。
 * 此外，JavaBeans 模式要求对象是可变的（即可以在对象创建后改变其状态），这可能不适用于需要不可变对象的场景。
 */
// JavaBeans 模式 - 允许不一致性，要求可变性 (第 11-12 页)
public class NutritionFacts {
    // 参数初始化为默认值（如果有的话）
    private int servingSize  = -1; // 必需的；没有默认值
    private int servings     = -1; // 必需的；没有默认值
    private int calories     = 0;
    private int fat          = 0;
    private int sodium       = 0;
    private int carbohydrate = 0;

    // 无参构造函数
    public NutritionFacts() { }

    // Setter 方法
    public void setServingSize(int val)  { servingSize = val; }
    public void setServings(int val)     { servings = val; }
    public void setCalories(int val)     { calories = val; }
    public void setFat(int val)          { fat = val; }
    public void setSodium(int val)       { sodium = val; }
    public void setCarbohydrate(int val) { carbohydrate = val; }

    // 测试代码
    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);    // 设置份量大小
        cocaCola.setServings(8);         // 设置份量数
        cocaCola.setCalories(100);       // 设置卡路里
        cocaCola.setSodium(35);          // 设置钠含量
        cocaCola.setCarbohydrate(27);    // 设置碳水化合物含量
    }
}
