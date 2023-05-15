package com.Taylor.ChatProject.datasource.presentation;

import com.Taylor.ChatProject.datasource.ClientInformation;
import com.Taylor.ChatProject.datasource.model.Command.Command;
import com.Taylor.ChatProject.datasource.model.Command.CommandGetChatForUsers;
import com.Taylor.ChatProject.datasource.model.Command.CommandGetPeers;
import com.Taylor.ChatProject.datasource.model.MessageHandler;
import com.Taylor.ChatProject.datasource.model.report.GetPeersReport;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;
import org.apache.logging.log4j.message.Message;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        CommandGetPeers peers = new CommandGetPeers();
        MessageHandler.getSingleton().queueCommand(peers);
        while(!ReportHandler.getSingleton().hasReports()){}
        GetPeersReport report = (GetPeersReport) ReportHandler.getSingleton().getNextReport();
        System.out.println("MainMenuReport: " + report.toString());
        List<String> peersList = report.getPeers();

        String[] pList = new String[peersList.size()];
        for(int i = 0; i < pList.length; i++){
            pList[i] = peersList.get(i);
        }
        System.out.println("Filled Peers");
        list = new JList<>(pList);
        JScrollPane listScrollPane = new JScrollPane(list);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the selected item from the list
                    String selected = list.getSelectedValue();
                    if (selected != null) {
                        getChats(selected);
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

    private void getChats(String chatPeer){
        CommandGetChatForUsers command = new CommandGetChatForUsers(ClientInformation.getSingleton().getUsername(), chatPeer);
        MessageHandler.getSingleton().queueCommand(command);
        while(!ReportHandler.getSingleton().hasReports()){}
        ChattingMenu menu = new ChattingMenu();

    }

    public void doAction(ActionEvent e) {
        if (e.getActionCommand().equals("Start Networking")) {

        } else if (e.getActionCommand().equals("New Chat")) {

        } else if (e.getActionCommand().equals("Settings")) {

        }
    }
}
