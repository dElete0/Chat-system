package com.delete;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient extends Frame{

    TextField textField = new TextField();
    TextArea textArea = new TextArea();

    public  static void main(String[] args) {
        new ChatClient().launchFrame();
    }

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
                System.exit(0);
            }
        });

        //new 一个输出监听器
        textField.addActionListener(new ChatClientListener());

        setVisible(true);

        connect();
    }

    //连接服务器
    public void connect() {
        try {
            Socket s = new Socket("127.0.0.1", 8888);
System.out.println("已经连上了");
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private class ChatClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String s = textField.getText().trim();
            textArea.setText(s);
            textField.setText("");
        }
    }

}