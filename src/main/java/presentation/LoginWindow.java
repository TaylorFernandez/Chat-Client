package presentation;

import model.Command.CommandSendLoginInformation;
import model.MessageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow {
    JTextField Username;
    JTextField Password;

    public LoginWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width / 5;
        int screenHeight = screenSize.height / 6;

        // Calculate the x and y coordinates to center the window
        int windowX = (screenSize.width - screenWidth) / 2;
        int windowY = (screenSize.height - screenHeight) / 2;

        JFrame frame = new JFrame("Chat Client");
        frame.setSize(screenWidth, screenHeight);
        frame.setLocation(windowX, windowY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for centering the button
        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doAction(e);
            }
        });
        buttonPanel.add(login);

        JLabel UsernameLabel = new JLabel("Username: ");
        Username = new JTextField();

        JLabel PasswordLabel = new JLabel("Password: ");
        Password = new JTextField();

        frame.add(UsernameLabel);
        frame.add(Username);
        frame.add(PasswordLabel);
        frame.add(Password);
        frame.add(buttonPanel); // Add the button panel to the frame

        frame.setVisible(true);
    }

    public void doAction(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            String usernameText = Username.getText();
            String passwordText = Password.getText();

            CommandSendLoginInformation info = new CommandSendLoginInformation(usernameText, passwordText);
            System.out.println("Bruh: " + MessageHandler.getSingleton().toString());
            MessageHandler.getSingleton().QueueCommand(info);
        }
    }
}
