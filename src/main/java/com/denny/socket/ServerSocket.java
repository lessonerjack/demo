package com.denny.socket;

import java.io.*;
import java.net.Socket;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-09-08 10:43
 */
public class ServerSocket {


    public static  void main(String args[]){
        try {
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(9999);
            System.out.println("服务端已启动，等待客户端连接..");
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info ="";
            String temp ="";
            while ((temp = bufferedReader.readLine()) != null){
                info += temp;
                System.out.println("客户端接收服务端发送信息："+info);
            }

            OutputStream outputStream = accept.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("你好，客户端，哈哈哈哈哈哈");
            printWriter.flush();
            accept.shutdownOutput();

            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
