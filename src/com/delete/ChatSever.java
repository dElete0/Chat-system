package com.delete;

import java.io.DataInputStream;
import java.net.*;
import java.io.IOException;

public class ChatSever {

    ServerSocket ss = null;
    boolean started = false;

    public static void main(String[] args) {
        new ChatSever().start();
    }

    public void start() {

        try {
            ss = new ServerSocket(8888);
            started = true;
        }catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (started) {
                Socket s = ss.accept();
                Client c = new Client(s);
                System.out.println("a client connected!");
                new Thread(c).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Client implements Runnable{

        private Socket s;
        private DataInputStream dis = null;
        private boolean ifConnect = false;

        public Client(Socket s){
            this.s = s;
            try{
                dis = new DataInputStream(s.getInputStream());
                ifConnect = true;
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public void run() {
            try{
                while(ifConnect){
                     String str = dis.readUTF();
                     System.out.println(str);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}