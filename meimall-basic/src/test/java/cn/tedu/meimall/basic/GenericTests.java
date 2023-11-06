package cn.tedu.meimall.basic;

import java.io.File;

public class GenericTests {
    public static void main(String[] args) {
        TransferObject<String> to1 = TransferObject.build(
                "克晶姐", System.currentTimeMillis(), "傳奇哥", "傳奇，你現在在忙吗？");

        File file = new File("D:/學習資料/a.zip");
        TransferObject<File> to2 = TransferObject.build(
                "傳奇哥", System.currentTimeMillis(), "克晶姐", file);

        TransferObject<String> to3 = TransferObject.build(
                "傳奇哥", System.currentTimeMillis(), "克晶姐", "在呢，我現在學習呢。");
    }
}

