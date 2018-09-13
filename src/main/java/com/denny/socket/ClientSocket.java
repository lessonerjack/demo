package com.denny.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-09-08 10:22
 */
public class ClientSocket {

    public static void main(String args[]){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1",8005),2000);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("你好，服务端，哈哈哈哈哈哈");
            printWriter.flush();
            socket.shutdownOutput();
            //Thread.sleep(10000);
            System.out.println("开始读数据了");
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info ="";
            String temp ="";
            while ((temp = bufferedReader.readLine()) != null){
                info += temp;
                System.out.println("客户端接收服务端发送信息："+info);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
