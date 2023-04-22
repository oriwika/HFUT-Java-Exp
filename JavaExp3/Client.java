package JavaExp3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

class MyClientFrame extends JFrame {
    Socket socket = null;
    JTextArea textArea = new JTextArea(20, 40);
    BufferedWriter bw;

    MyClientFrame() {
        super("客户端");

        JPanel jp_North = new JPanel();
        jp_North.setBorder(BorderFactory.createTitledBorder("客户端设置: "));
        jp_North.add(new JLabel("Server IP:"));
        JTextField jNorthTextField_IP = new JTextField(10);
        jp_North.add(jNorthTextField_IP);
        jp_North.add(new JLabel("Server Port:"));
        JTextField jNorthTextField_Port = new JTextField(10);
        jp_North.add(jNorthTextField_Port);
        JButton jNorthButton = new JButton("Connect");
        jp_North.add(jNorthButton);
        this.add(jp_North, BorderLayout.NORTH);

        JPanel jp_Center = new JPanel();
        jp_Center.add(textArea);
        this.add(jp_Center, BorderLayout.CENTER);

        JPanel jp_South = new JPanel();
        jp_South.add(new JLabel("Say:"));
        JTextField jSouthTextField = new JTextField(30);
        jp_South.add(jSouthTextField);
        JButton jSouthButton = new JButton("Say");
        jp_South.add(jSouthButton);
        this.add(jp_South, BorderLayout.SOUTH);

        class MyThread extends Thread{
            public void run(){
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while(true){
                        textArea.append("Server:"+br.readLine()+"\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        jNorthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.append("Connect to server…\n");
                    socket = new Socket(jNorthTextField_IP.getText(), Integer.parseInt(jNorthTextField_Port.getText()));
                    bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    new MyThread().start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jSouthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String temp = jSouthTextField.getText();
                    bw.write(temp+"\n");
                    bw.flush();
                    textArea.append("Client:"+temp+"\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 200, 500, 450);
        this.setResizable(false);
        this.setVisible(true);
    }
}
public class Client {
    public static void main(String[] args) {
        new MyClientFrame();
    }
}
