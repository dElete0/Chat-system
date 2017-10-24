package com.delete;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient extends Frame{

    Socket s = null;
    DataOutputStream dos = null;

    TextField textField = new TextField();
    TextArea textArea = new TextArea();

    public  static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    //窗口界面
    public void launchFrame(){
        setLocation(400,400);
        this.setSize(300,300);
        add(textField,BorderLayout.SOUTH);
        add(textArea,BorderLayout.NORTH);
        pack();

        //关闭窗口的监听器
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnect();
                System.exit(0);
            }
        });

        //new 一个输出监听器
        textField.addActionListener(new ChatClientListener());

        setVisible(true);

        connect();

        while (true){
            textArea.setText(getUTF(s));
        }
    }

    //连接服务器
    public void connect() {
        try {
            s = new Socket("127.0.0.1", 8888);
            dos = new DataOutputStream(s.getOutputStream());
System.out.println("已经连上了");
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //输出监听器
    private class ChatClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = textField.getText().trim();
            textArea.setText(str);
            textField.setText("");

            //向服务器发送字符串
            try {
                dos.writeUTF(str);
                dos.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    //输入方法
    public String getUTF(Socket s){
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = null;
        try {
            str = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    //断开连接，释放资源
    public void disconnect() {
        try{
            dos.close();
            s.close();
        }catch (IOException e2){
            e2.printStackTrace();
        }
    }

}