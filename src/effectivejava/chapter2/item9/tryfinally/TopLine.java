package effectivejava.chapter2.item9.tryfinally;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * firstLineOfFile 方法接收一个文件路径作为参数，并创建了一个 BufferedReader 对象来读取该文件。
 * 方法尝试读取文件的第一行，并在 finally 块中确保 BufferedReader 被关闭。
 * 这种模式确保了即使在读取文件时发生异常，资源也会被正确关闭。
 *
 * 尽管这种 try-finally 方法确实能保证资源的关闭，但它不是最简洁的方式，特别是当处理多个资源时。
 * 从 Java 7 开始，推荐使用 try-with-resources 语句，它能更简洁地实现相同的资源管理，并提供更好的可读性。
 */
public class TopLine {
    // try-finally - 不再是关闭资源的最佳方式！(第 34 页)
    static String firstLineOfFile(String path) throws IOException {
        // 创建一个 BufferedReader 来读取文件
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            // 尝试读取文件的第一行
            return br.readLine();
        } finally {
            br.close(); // 确保在返回第一行或抛出异常时关闭 BufferedReader
        }
    }

    public static void main(String[] args) throws IOException {
        String path = args[0]; // 文件路径
        // 打印文件的第一行
        System.out.println(firstLineOfFile(path));
    }
}
