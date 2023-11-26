package effectivejava.chapter6.item34;

import static effectivejava.chapter6.item34.PayrollDay.PayType.*;

/**
 * PayrollDay 枚举包含一周的每一天，每天都与一个 PayType 策略相关联。PayType 是一个嵌套的枚举，定义了计算加班费的两种不同策略：WEEKDAY 和 WEEKEND。每个策略都有自己的 overtimePay 方法来计算加班费。
 *
 * PayrollDay 的 pay 方法结合了基本工资和加班费来计算总工资。这种设计模式允许灵活地为不同的日子定义不同的支付规则，同时保持代码的简洁和易于维护。
 *
 * main 方法演示了如何使用 PayrollDay 枚举：遍历枚举值并计算在一个标准工作日（8小时，即480分钟）的工资。这种策略枚举模式提供了一种优雅的方式来封装变化的行为，并且易于扩展以适应新的支付规则。
 */
// 策略枚举模式 (第 166 页)
enum PayrollDay {
    // 定义枚举值，代表一周的每一天，并关联相应的支付策略
    MONDAY(WEEKDAY), TUESDAY(WEEKDAY), WEDNESDAY(WEEKDAY),
    THURSDAY(WEEKDAY), FRIDAY(WEEKDAY),
    SATURDAY(WEEKEND), SUNDAY(WEEKEND);

    private final PayType payType; // 每个枚举值关联一个 PayType 枚举

    PayrollDay(PayType payType) { this.payType = payType; }

    // 根据工作时间和支付率计算工资
    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    // 策略枚举类型
    enum PayType {
        // 定义平时的加班支付策略
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 :
                        (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        // 定义周末的加班支付策略
        WEEKEND {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate); // 抽象方法计算加班费
        private static final int MINS_PER_SHIFT = 8 * 60; // 标准工作时间

        // 计算总工资（基本工资加上加班费）
        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }

    public static void main(String[] args) {
        // 遍历所有的 PayrollDay 枚举值，打印每天的工资
        for (PayrollDay day : values())
            System.out.printf("%-10s%d%n", day, day.pay(8 * 60, 1));
    }
}
