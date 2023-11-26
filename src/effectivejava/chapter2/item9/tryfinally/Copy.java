package effectivejava.chapter2.item9.tryfinally;

import java.io.*;

/**
 * 方法 copy 接受两个字符串参数：源文件路径和目标文件路径。它首先创建一个 InputStream 来读取源文件，
 * 然后在一个 try 块中创建一个 OutputStream 来写入目标文件。
 * 文件的读取和写入是通过一个缓冲区进行的，以提高效率。
 * 每个流都包装在它自己的 try 块中，并且在对应的 finally 块中关闭，以确保即使发生异常也能正确关闭资源。
 *
 * 尽管这种方法确保了资源的正确关闭，但它使得代码变得冗长且难以阅读。
 * 当处理多个需要关闭的资源时，try-finally 的嵌套会变得更加复杂。
 * 从 Java 7 开始，推荐使用 try-with-resources 语句来简化这种资源管理代码。
 */
public class Copy {
    private static final int BUFFER_SIZE = 8 * 1024; // 缓冲区大小

    // 当使用多于一个资源时，try-finally 的写法不够优雅！(第 34 页)
    static void copy(String src, String dst) throws IOException {
        // 创建输入流以读取源文件
        InputStream in = new FileInputStream(src);
        try {
            // 创建输出流以写入目标文件
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE]; // 创建缓冲区
                int n;
                // 从输入流读取数据到缓冲区，并写入输出流
                while ((n = in.read(buf)) >= 0)
                    out.write(buf, 0, n);
            } finally {
                out.close(); // 确保输出流被关闭
            }
        } finally {
            in.close(); // 确保输入流被关闭
        }
    }

    public static void main(String[] args) throws IOException {
        String src = args[0]; // 源文件路径
        String dst = args[1]; // 目标文件路径
        copy(src, dst); // 执行复制操作
    }
}
