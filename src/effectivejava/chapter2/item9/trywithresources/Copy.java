package effectivejava.chapter2.item9.trywithresources;

import java.io.*;

/**
 * copy 方法使用了 try-with-resources 语句来自动管理文件输入流（InputStream）和文件输出流（OutputStream）。
 * 与传统的 try-finally 相比，try-with-resources 语句更加简洁，它能自动关闭在括号内声明的资源，
 * 无论方法是正常结束还是因异常而结束。这种方式不仅使得代码更加简洁，而且减少了资源泄露的风险。
 * 在 main 方法中，通过提供源文件和目标文件的路径来调用 copy 方法，实现文件的复制。
 */
public class Copy {
    private static final int BUFFER_SIZE = 8 * 1024; // 缓冲区大小

    // 使用 try-with-resources 语句同时管理多个资源 - 简洁明了 (第 35 页)
    static void copy(String src, String dst) throws IOException {
        try (InputStream   in = new FileInputStream(src);  // 创建用于读取的输入流
             OutputStream out = new FileOutputStream(dst)) { // 创建用于写入的输出流
            byte[] buf = new byte[BUFFER_SIZE]; // 创建缓冲区
            int n;
            // 读取数据到缓冲区，并写入目标文件
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        } // 自动关闭 in 和 out 资源
    }

    public static void main(String[] args) throws IOException {
        String src = args[0]; // 源文件路径
        String dst = args[1]; // 目标文件路径
        copy(src, dst); // 执行复制操作
    }
}
