package effectivejava.chapter2.item2.hierarchicalbuilder;

import java.util.Objects;

/**
 * NyPizza 类继承自 Pizza 类，并且添加了一个新的属性 size 来表示披萨的尺寸。
 * NyPizza 的 Builder 类继承自 Pizza.Builder，并且通过重写 build() 和 self() 方法来确保正确地构建 NyPizza 实例。
 * 这个模式允许在维持 Pizza 类构建逻辑的同时，为 NyPizza 类添加特定的构建逻辑。
 */
// 使用分层构建器的子类 (第 15 页)
public class NyPizza extends Pizza {
    // 定义尺寸枚举
    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size; // 披萨的尺寸

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size; // Builder 中的尺寸属性

        // 构造函数，需要指定尺寸
        public Builder(Size size) {
            this.size = Objects.requireNonNull(size); // 确保尺寸不为空
        }

        // 重写 build 方法，创建 NyPizza 实例
        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        // 重写 self 方法，返回当前 Builder 实例
        @Override
        protected Builder self() {
            return this;
        }
    }

    // NyPizza 的私有构造函数，使用 Builder 对象构建实例
    private NyPizza(Builder builder) {
        super(builder); // 调用父类构造函数
        size = builder.size; // 设置尺寸
    }

    // 重写 toString 方法，返回披萨描述
    @Override
    public String toString() {
        return "New York Pizza with " + toppings; // 描述包含 toppings 的纽约披萨
    }
}
