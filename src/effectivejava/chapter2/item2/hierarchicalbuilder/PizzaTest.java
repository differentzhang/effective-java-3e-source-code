package effectivejava.chapter2.item2.hierarchicalbuilder;

import static effectivejava.chapter2.item2.hierarchicalbuilder.Pizza.Topping.*;
import static effectivejava.chapter2.item2.hierarchicalbuilder.NyPizza.Size.*;

/**
 * NyPizza 和 Calzone 类分别使用它们的 Builder 类构建。
 * 对于 NyPizza，我们指定了尺寸为 SMALL 并添加了 SAUSAGE 和 ONION 作为配料。
 * 对于 Calzone，我们添加了 HAM 作为配料，并通过调用 sauceInside() 方法指定酱料放在披萨内部。
 * 这种方式演示了如何灵活地使用 Builder 模式来构建具有不同属性和配置的对象。
 */
// 使用分层构建器 (第 16 页)
public class PizzaTest {
    public static void main(String[] args) {
        // 构建一个纽约风味的小号披萨，添加香肠和洋葱作为配料
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();

        // 构建一个卡尔佐尼披萨，添加火腿作为配料，并将酱料放在内部
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside().build();

        // 输出两种披萨的描述
        System.out.println(pizza);
        System.out.println(calzone);
    }
}
