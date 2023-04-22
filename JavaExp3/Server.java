package JavaExp3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class MyServerFrame extends JFrame {
    ServerSocket serverSocket = null;
    Socket socket = null;
    JTextArea textArea = new JTextArea(20,40);
    BufferedWriter bw;
    MyServerFrame(){
        super("服务器");

        JPanel jp_North = new JPanel();
        jp_North.setBorder(BorderFactory.createTitledBorder("服务器设置: "));
        jp_North.add(new JLabel("Port:"));
        JTextField jNorthTextField = new JTextField(30);
        jp_North.add(jNorthTextField);
        JButton jNorthButton = new JButton("Start");
        jp_North.add(jNorthButton);
        this.add(jp_North, BorderLayout.NORTH);

        JPanel jp_Center = new JPanel();
        jp_Center.add(textArea);
        this.add(jp_Center,BorderLayout.CENTER);

        JPanel jp_South = new JPanel();
        jp_South.add(new JLabel("Say:"));
        JTextField jSouthTextField = new JTextField(30);
        jp_South.add(jSouthTextField);
        JButton jSouthButton = new JButton("Say");
        jp_South.add(jSouthButton);
        this.add(jp_South, BorderLayout.SOUTH);

        class MyThread extends Thread{
            @Override
            public void run() {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while(true){
                        textArea.append("Client:"+br.readLine()+"\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        jNorthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                    textArea.append("Connect to server...\n");
//                    serverSocket = new ServerSocket(Integer.parseInt(jNorthTextField.getText()));
//                    socket = serverSocket.accept();
//                    bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                    textArea.append("Client connected…\n");
                textArea.append("Connect to server...\n");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            serverSocket = new ServerSocket(Integer.parseInt(jNorthTextField.getText()));
                            socket = serverSocket.accept();
                            textArea.append("Client connected…\n");
                            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            new MyThread().start();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }).start();
            }
        });

        jSouthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String temp = jSouthTextField.getText();
                    bw.write(temp+"\n");
                    bw.flush();
                    textArea.append("Server:"+temp+"\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400,200,500,450);
        this.setResizable(false);
        this.setVisible(true);
    }
}

public class Server {
    public static void main(String[] args) {
        new MyServerFrame();
    }
}