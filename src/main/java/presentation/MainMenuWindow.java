package presentation;

import model.Command.CommandSendLoginInformation;
import model.MessageHandler;
import model.StateHandler;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow {

    JTextField Username;
    JFrame frame;
    JTextField Password;

    JList<String> list;

    public MainMenuWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width / 5;
        int screenHeight = screenSize.height / 6;

        // Calculate the x and y coordinates to center the window
        int windowX = (screenSize.width - screenWidth) / 2;
        int windowY = (screenSize.height - screenHeight) / 2;

        frame = new JFrame("Chat Client");
        frame.setSize(screenWidth, screenHeight);
        frame.setLocation(windowX, windowY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainMenu = new JPanel(new BorderLayout()); // Panel for centering the button
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));

        JButton startNetworking = new JButton("Start Networking");
        startNetworking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doAction(e);
            }
        });
        buttonPanel.add(startNetworking);

        JButton newChat = new JButton("New Chat");
        newChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doAction(e);
            }
        });
        buttonPanel.add(newChat);

        JButton settings = new JButton("Settings");
        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doAction(e);
            }
        });
        buttonPanel.add(settings);

        String[] listData = {"User 1", "User 2", "User 3", "User 4"};
        list = new JList<>(listData);
        JScrollPane listScrollPane = new JScrollPane(list);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the selected item from the list
                    String selected = list.getSelectedValue();
                    if (selected != null) {
                        System.out.println("Clicked: " + selected);
                    }
                }
            }
        });

        listScrollPane.setPreferredSize(new Dimension(100, 500));

        mainMenu.add(listScrollPane, BorderLayout.WEST);
        mainMenu.add(buttonPanel, BorderLayout.EAST);


        frame.add(mainMenu); // Add the button panel to the frame

        frame.setVisible(false);
    }

    public void isVisible(boolean bool){
        frame.setVisible(bool);
    }

    public void doAction(ActionEvent e) {
        if (e.getActionCommand().equals("Start Networking")) {

        }else if(e.getActionCommand().equals("New Chat")){

        }else if(e.getActionCommand().equals("Settings")){

        }
    }
}
