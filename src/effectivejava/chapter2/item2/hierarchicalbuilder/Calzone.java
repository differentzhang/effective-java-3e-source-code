package effectivejava.chapter2.item2.hierarchicalbuilder;

/**
 * 这段代码展示了如何在一个具有继承关系的类结构中使用 Builder 模式。Calzone 类扩展了 Pizza 类，
 * 添加了自己的特定属性 sauceInside。通过 Builder 类的继承，
 * 可以在保持 Pizza 类的构建功能的同时，添加特定于 Calzone 的构建逻辑。这种模式使得代码更加灵活且易于扩展。
 */
// 使用分层构建器的子类 (第 15 页)
public class Calzone extends Pizza {
    private final boolean sauceInside; // 是否在里面加酱料

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false; // 默认不在里面加酱料

        // 设置酱料在内部，并返回 Builder 实例
        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        // 重写 build 方法，返回 Calzone 实例
        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        // 重写 self 方法，返回当前 Builder 实例
        @Override
        protected Builder self() {
            return this;
        }
    }

    // Calzone 的私有构造函数，使用 Builder 对象构建实例
    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }

    // 重写 toString 方法，用于描述 Calzone 对象
    @Override
    public String toString() {
        return String.format("Calzone with %s and sauce on the %s",
                toppings, sauceInside ? "inside" : "outside");
    }
}
