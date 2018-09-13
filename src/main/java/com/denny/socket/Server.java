package com.denny.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-09-08 10:43
 */
public class Server extends Thread{


    ServerSocket server = null;
    Socket sk = null;
    BufferedReader rdr = null;
    PrintWriter wtr = null;

    public Server()
    {

    }

    @Override
    public void run()
    {
        try {
            server = new ServerSocket(8005);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true)
        {

            System.out.println("Listenning...");
            try
            {
//                  每个请求交给一个线程去处理
                Socket sk = server.accept();
                wtr = new PrintWriter(sk.getOutputStream());
                rdr = new BufferedReader(new InputStreamReader(sk
                        .getInputStream()));
                String line = rdr.readLine();
                System.out.println("从客户端来的信息：" + line);
//              特别，下面这句得加上     “\n”,
                wtr.println( line + "'\n");
                wtr.flush();
                System.out.println("已经返回给客户端！");
                sk.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("once end...");

        }
    }

    public static void main(String [] args)
    {
        new Server().start();
    }


}
