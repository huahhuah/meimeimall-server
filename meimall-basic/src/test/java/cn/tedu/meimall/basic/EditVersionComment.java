package cn.tedu.meimall.basic;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class EditVersionComment {

    String oldVersionCode = " * @version 1.0";
    String newVersionCode = " * @version 2.0";

    @Test
    void editVersionComment() throws Throwable {
        String javaFileDirectory = "D:\\IdeaProjects\\meimeimall-server";
        File file = new File(javaFileDirectory);
        executeEdit(file);
        System.out.println("完成！");
    }

    void executeEdit(File file) throws Throwable {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                executeEdit(file1);
            }
        }
        String fileName = file.getName();
        if (fileName.endsWith(".java")) {
            File dir = file.getParentFile();
            File srcFile = new File(dir, fileName);
            File destFile = new File(dir, "temp.java");
            BufferedReader reader = new BufferedReader(new FileReader(srcFile));
            PrintWriter writer = new PrintWriter(destFile);
            String line;
            while ((line = reader.readLine()) != null) {
                if (oldVersionCode.equals(line)) {
                    writer.println(newVersionCode);
                    System.out.println(file.getAbsolutePath());
                    System.out.println(line);
                } else {
                    writer.println(line);
                }
            }
            reader.close();
            writer.close();
            srcFile.delete();
            destFile.renameTo(srcFile);
        }
    }

}

