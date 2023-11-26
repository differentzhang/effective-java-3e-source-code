package effectivejava.chapter2.item9.trywithresources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * firstLineOfFile 方法使用了 try-with-resources 语句来创建 BufferedReader。
 * 该语句确保无论正常结束还是发生异常，资源（在这里是 BufferedReader）都会被自动关闭。
 * 这种方式比传统的 try-finally 语句更简洁，并且减少了资源泄露的风险。
 * 在 main 方法中，方法被调用来读取指定路径文件的第一行，并将其打印出来。
 * 这展示了如何简洁地处理需要关闭的资源，特别是在涉及文件操作时。
 */
public class TopLine {
    // 使用 try-with-resources 语句 - 关闭资源的最佳方式！(第 35 页)
    static String firstLineOfFile(String path) throws IOException {
        // 创建一个 BufferedReader，并在 try 语句中自动管理它
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            // 读取并返回文件的第一行
            return br.readLine();
        } // try-with-resources 会自动关闭 BufferedReader
    }

    public static void main(String[] args) throws IOException {
        String path = args[0]; // 文件路径
        // 打印文件的第一行
        System.out.println(firstLineOfFile(path));
    }
}
