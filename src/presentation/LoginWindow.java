package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow {

    JTextField Username;
    JTextField Password;
    public LoginWindow(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/3;

        // Calculate the x and y coordinates to center the window
        int windowX = (screenSize.width - screenWidth) / 2;
        int windowY = (screenSize.height - screenHeight) / 2;

        JFrame frame = new JFrame("Chat Client");
        frame.setSize(screenWidth, screenHeight);
        frame.setLocation(windowX, windowY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4,3));


        JLabel UsernameLabel = new JLabel("Username: ");
        Username = new JTextField();

        JLabel PasswordLabel = new JLabel("Password: ");
        Password = new JTextField();

        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doAction(e);
            }
        });
        //login.setAlignmentX(screenWidth);

        frame.add(UsernameLabel);
        frame.add(Username);
        frame.add(PasswordLabel);
        frame.add(Password);
        frame.add(login);
        //frame.pack();
        frame.setVisible(true);
    }


    public void doAction(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            
            String text1 = Username.getText();
            String text2 = Password.getText();
            System.out.println("Text from textbox 1: " + text1);
            System.out.println("Text from textbox 2: " + text2);
        }
    }
}
