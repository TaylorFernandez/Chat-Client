package com.Taylor.ChatProject.datasource.presentation;

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
        int screenWidth = screenSize.width / 4;
        int screenHeight = screenSize.height / 3;

        // Calculate the x and y coordinates to center the window
        int windowX = (screenSize.width - screenWidth) / 2;
        int windowY = (screenSize.height - screenHeight) / 2;

        frame = new JFrame("Chat Client");
        frame.setSize(screenWidth, screenHeight);
        frame.setLocation(windowX, windowY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);

        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

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

        // Set the preferred size of the listScrollPane to modify the height of the JList
        listScrollPane.setPreferredSize(new Dimension(100, 500));

        mainMenu.add(listScrollPane);
        mainMenu.add(buttonPanel);

        frame.add(mainMenu);

        frame.setVisible(false);
    }

    public void isVisible(boolean bool) {
        frame.setVisible(bool);
    }

    public void doAction(ActionEvent e) {
        if (e.getActionCommand().equals("Start Networking")) {

        } else if (e.getActionCommand().equals("New Chat")) {

        } else if (e.getActionCommand().equals("Settings")) {

        }
    }
}
