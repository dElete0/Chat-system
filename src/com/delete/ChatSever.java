package com.delete;

import java.io.DataInputStream;
import java.net.*;
import java.io.IOException;

public class ChatSever {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true) {
                Socket s = ss.accept();
System.out.println("a client connected!");
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String str = dis.readUTF();
                System.out.println(str);
                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}