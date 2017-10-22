package com.delete;

import java.awt.*;

public class ChatClient extends Frame{

    TextField textField = new TextField();
    TextArea textArea = new TextArea();

    public  static void main(String[] args){
        new ChatClient().launchFrame();
    }

    public void launchFrame(){
        setLocation(400,400);
        this.setSize(300,300);
        add(textField,BorderLayout.SOUTH);
        add(textArea,BorderLayout.NORTH);
        pack();
        setVisible(true);
    }

}