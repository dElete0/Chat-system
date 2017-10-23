package com.delete;

import java.io.DataInputStream;
import java.net.*;
import java.io.IOException;

public class ChatSever {

    public static void main(String[] args) {

        ServerSocket ss = null;
        Socket s = null;
        DataInputStream dis = null;

        try {
            ss = new ServerSocket(8888);
        }catch (IOException e) {
            e.printStackTrace();
        }

        try{
            while (true) {
                boolean ifConnected = false;
                s = ss.accept();
System.out.println("a client connected!");
                ifConnected = true;
                dis = new DataInputStream(s.getInputStream());
                while(ifConnected) {
                    String str = dis.readUTF();
                    System.out.println(str);
                }
            }
        } catch (IOException e) {
            System.out.println("Client closed!");
        }finally {
            try {
                if(dis != null) dis.close();
                if(s != null) s.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }
}