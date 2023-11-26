package effectivejava.chapter2.item7;
import java.util.*;

/**
 * 内存泄漏的问题在于 pop 方法：当从栈中弹出对象时，该对象不会被垃圾收集器回收，
 * 因为栈内部维护的数组仍然保持着对这些对象的引用。这种类型的内存泄漏可能在实际应用中很难被发现，
 * 因为它不会产生明显的错误，但会逐渐增加内存的使用。
 * 为了解决这个问题，可以在弹出对象后，将数组中相应的元素显式地设为 null（如注释掉的 pop 方法所示）。
 * 这样做可以确保不再需要的对象能被垃圾收集器正常回收。
 */
// 你能发现“内存泄漏”吗？ (第 26-27 页)
public class Stack {
    private Object[] elements; // 存储元素的数组
    private int size = 0; // 栈当前大小
    private static final int DEFAULT_INITIAL_CAPACITY = 16; // 默认初始容量

    // 构造函数，初始化栈
    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    // 将元素推入栈
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    // 从栈中弹出元素
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException(); // 如果栈空，则抛出异常
        return elements[--size]; // 这里是潜在的内存泄漏点
    }

    /**
     * 确保至少有空间存储一个元素，必要时将容量大致翻倍。
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

//    // 修改后的 pop 方法 (第 27 页)
//    public Object pop() {
//        if (size == 0)
//            throw new EmptyStackException();
//        Object result = elements[--size];
//        elements[size] = null; // 消除过时的引用
//        return result;
//    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        for (String arg : args)
            stack.push(arg);

        while (true)
            System.err.println(stack.pop()); // 无限循环打印弹出的元素
    }
}
