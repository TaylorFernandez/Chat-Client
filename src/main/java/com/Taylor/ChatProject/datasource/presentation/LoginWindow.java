package com.Taylor.ChatProject.datasource.presentation;

import com.Taylor.ChatProject.datasource.ClientInformation;
import com.Taylor.ChatProject.datasource.model.Command.CommandCreateNewUser;
import com.Taylor.ChatProject.datasource.model.Command.CommandSendLoginInformation;
import com.Taylor.ChatProject.datasource.model.MessageHandler;
import com.Taylor.ChatProject.datasource.model.Observer.ApplicationStateObserver;
import com.Taylor.ChatProject.datasource.model.State.LoginWaitingState;
import com.Taylor.ChatProject.datasource.model.report.CreateNewUserReport;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow {
    JTextField Username;
    JFrame frame;
    JTextField Password;

    public LoginWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width / 5;
        int screenHeight = screenSize.height / 6;

        // Calculate the x and y coordinates to center the window
        int windowX = (screenSize.width - screenWidth) / 2;
        int windowY = (screenSize.height - screenHeight) / 2;

        frame = new JFrame("Login");
        frame.setSize(screenWidth, screenHeight);
        frame.setLocation(windowX, windowY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        JPanel ipConfigPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        JButton configButton = new JButton("Configure Networking");
        configButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openConfigWindow();
            }
        });
        ipConfigPanel.add(configButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doAction(e);
            }
        });
        buttonPanel.add(login);

        JButton newUser = new JButton("New User");
        newUser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){openNewUserWindow();}
        });
        buttonPanel.add(newUser);

        JLabel UsernameLabel = new JLabel("Username: ");
        Username = new JTextField();

        JLabel PasswordLabel = new JLabel("Password: ");
        Password = new JTextField();

        frame.add(UsernameLabel);
        frame.add(Username);
        frame.add(PasswordLabel);
        frame.add(Password);
        frame.add(ipConfigPanel);
        frame.add(buttonPanel);

        frame.setVisible(true);
    }

    public void isVisible(boolean bool) {
        frame.setVisible(bool);
    }

    public static void showFailurePopup() {
        JOptionPane.showConfirmDialog(null, "Login Failed. Please try again!", "Login failed",
                JOptionPane.DEFAULT_OPTION);
    }

    public void doAction(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            String usernameText = Username.getText();
            String passwordText = Password.getText();
            System.out.println("Logging in");
            CommandSendLoginInformation info = new CommandSendLoginInformation(usernameText, passwordText);
            MessageHandler.getSingleton().queueCommand(info);
            ApplicationStateObserver.getSingleton().setState(new LoginWaitingState());
        }
    }

    public void openNewUserWindow(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the x and y coordinates to center the window
        int windowX = (screenSize.width - 300) / 2;
        int windowY = (screenSize.height - 150) / 2;

        JFrame configFrame = new JFrame("New User");
        configFrame.setSize(300, 150);
        configFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configFrame.setLayout(new GridLayout(3, 2));
        configFrame.setLocation(windowX, windowY);

        JLabel uName = new JLabel("UserName: ");
        JTextField username = new JTextField();

        JLabel pwd = new JLabel("Password: ");
        JTextField password = new JTextField();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String uName = username.getText();
                String pwd = password.getText();

                //TODO: add code to notify server of new user
                CommandCreateNewUser cmd = new CommandCreateNewUser(uName, pwd);
                MessageHandler.getSingleton().queueCommand(cmd);
                while(!ReportHandler.getSingleton().hasReports()){}
                CreateNewUserReport r = (CreateNewUserReport) ReportHandler.getSingleton().getNextReport();
                System.out.println("New User Report: " + r.getSuccess());
                ReportHandler.getSingleton().addNewReport(r);
                ClientInformation.getSingleton().setUsername(uName);
                ApplicationStateObserver.getSingleton().setState(new LoginWaitingState());

                configFrame.dispose(); // Close the configuration window
            }
        });

        configFrame.add(uName);
        configFrame.add(username);
        configFrame.add(pwd);
        configFrame.add(password);
        configFrame.add(saveButton);

        configFrame.setVisible(true);
    }

    public void openConfigWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        // Calculate the x and y coordinates to center the window
        int windowX = (screenSize.width - 300) / 2;
        int windowY = (screenSize.height - 150) / 2;


        JFrame configFrame = new JFrame("Configuration");
        configFrame.setSize(300, 150);
        configFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configFrame.setLayout(new GridLayout(3, 2));
        configFrame.setLocation(windowX, windowY);

        JLabel ipLabel = new JLabel("IP Address: ");
        JTextField ipField = new JTextField();

        JLabel portLabel = new JLabel("Port: ");
        JTextField portField = new JTextField();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save the IP address and port
                String ipAddress = ipField.getText();
                int port = Integer.parseInt(portField.getText());

                //"http://localhost:8080/"
                String newURL = "http://" + ipAddress + ":" + port +'/';
                System.out.println(newURL);
                ClientInformation.getSingleton().setServerAddress(newURL);

                configFrame.dispose(); // Close the configuration window
            }
        });

        configFrame.add(ipLabel);
        configFrame.add(ipField);
        configFrame.add(portLabel);
        configFrame.add(portField);
        configFrame.add(saveButton);

        configFrame.setVisible(true);
    }
}
