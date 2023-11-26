package effectivejava.chapter6.item34;

/**
 * 程序首先从命令行参数中读取地球上的重量（单位：牛顿），然后将其转换为质量（单位：千克），这是通过将重量除以地球的表面重力得到的。然后，程序遍历 Planet 枚举中的每个行星，并使用 surfaceWeight 方法计算在该行星上的重量。
 *
 * 这个程序展示了如何使用枚举类型 Planet 来进行跨不同上下文（即不同行星）的计算。这种方法使得代码简洁且易于理解，同时也展示了枚举的强大功能。
 */
// 接受地球重量并打印出所有行星上的重量表 (第 160 页)
public class WeightTable {
   public static void main(String[] args) {
      // 从命令行参数获取地球上的重量
      double earthWeight = Double.parseDouble(args[0]);
      // 计算质量（地球重量除以地球表面重力）
      double mass = earthWeight / Planet.EARTH.surfaceGravity();

      // 遍历 Planet 枚举的所有值
      for (Planet p : Planet.values())
         // 打印出在每个行星上的重量
         System.out.printf("Weight on %s is %f%n",
                 p, p.surfaceWeight(mass));
   }
}
