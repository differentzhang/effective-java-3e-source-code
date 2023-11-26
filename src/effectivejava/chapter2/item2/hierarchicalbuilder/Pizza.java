package effectivejava.chapter2.item2.hierarchicalbuilder;
import java.util.*;

/**
 * Pizza 是一个包含配料集合 toppings 的抽象类。其内部定义了一个泛型抽象 Builder 类，
 * 这个 Builder 类允许通过 addTopping 方法向披萨中添加配料。Builder 类使用了所谓的 "模拟自身类型" 习语，
 * 这种设计方式使得子类可以在保留链式调用语法的同时，返回正确的子类类型的 Builder 实例
 */
// 用于类层次结构的 Builder 模式 (第 14 页)

// 注意底层的“模拟自身类型”习语允许任意流畅的层次结构，不仅限于构建器

public abstract class Pizza {
    // 定义披萨配料的枚举类型
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    final Set<Topping> toppings; // 披萨的配料集合

    // 抽象的 Builder 类，使用泛型实现模拟自身类型
    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class); // 配料集合，初始为空

        // 添加配料的方法，并返回当前 Builder 实例
        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping)); // 确保配料不为空
            return self(); // 返回当前 Builder 实例
        }

        // 抽象的 build 方法，子类需要实现
        abstract Pizza build();

        // 子类必须重写此方法以返回 "this"
        protected abstract T self();
    }

    // Pizza 的构造函数，使用 Builder 对象构建实例
    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone(); // 克隆 Builder 中的配料集合（参见条目 50）
    }
}
