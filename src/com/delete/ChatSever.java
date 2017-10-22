package com.delete;

import java.net.*;
import java.io.IOException;

public class ChatSever {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true) {
                Socket s = ss.accept();
System.out.println("a client connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
