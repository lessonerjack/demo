package com.denny.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-09-08 10:08
 */
public class CopyFile {
    static final String filePath = "d:\\123.txt";

    public static void main(String args[]) throws Exception {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream("d:\\copy.txt");

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
        } finally {

        }
    }
}
