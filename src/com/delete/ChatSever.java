package com.delete;

import java.io.DataInputStream;
import java.net.*;
import java.io.IOException;

public class ChatSever {

    public static void main(String[] args) {

        boolean started = false;

        try {
            ServerSocket ss = new ServerSocket(8888);
            started = true;
            while (true) {
                boolean ifConnected = false;
                Socket s = ss.accept();
System.out.println("a client connected!");
                ifConnected = true;
                DataInputStream dis = new DataInputStream(s.getInputStream());
                while(ifConnected) {
                    String str = dis.readUTF();
                    System.out.println(str);
                }
                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}