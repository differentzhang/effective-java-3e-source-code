package effectivejava.chapter2.item9.trywithresources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * firstLineOfFile 方法通过 try-with-resources 语句创建了一个 BufferedReader 来读取文件。
 * 如果在读取过程中发生 IOException，
 * 则方法会捕获这个异常并返回一个指定的默认值（在这个例子中是 "Toppy McTopFace"）。
 * 这种模式在处理可能出现的输入输出异常时非常有用，它允许程序在出现错误时以优雅的方式恢复，
 * 而不是直接崩溃或需要外部干预。
 * 在 main 方法中，通过提供文件路径和默认值来调用该方法，从而能够处理正常和异常情况。
 */
public class TopLineWithDefault {
    // 带有 catch 子句的 try-with-resources 语句 (第 36 页)
    static String firstLineOfFile(String path, String defaultVal) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            // 尝试读取文件的第一行
            return br.readLine();
        } catch (IOException e) {
            // 如果发生 IOException，返回默认值
            return defaultVal;
        }
    }

    public static void main(String[] args) throws IOException {
        String path = args[0]; // 文件路径
        // 打印文件的第一行或默认值
        System.out.println(firstLineOfFile(path, "Toppy McTopFace"));
    }
}
