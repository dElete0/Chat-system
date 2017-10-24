package com.delete;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatSever {

    ServerSocket ss = null;
    boolean started = false;

    List<Client> clients = new ArrayList<>();

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
                clients.add(c);
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
        private DataOutputStream dos = null;

        public Client(Socket s){
            this.s = s;
            try{
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
                ifConnect = true;
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public void send(String str){
            try{
                dos.writeUTF(str);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public void run() {
            try{
                while(ifConnect){
                     String str = dis.readUTF();
                     System.out.println(str);
                     for(int i = 0; i < clients.size(); i++) {
                         Client c = clients.get(i);
                         c.send(str);
                     }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}