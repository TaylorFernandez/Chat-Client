package com.Taylor.ChatProject.datasource.presentation;

import com.Taylor.ChatProject.datasource.ClientInformation;
import com.Taylor.ChatProject.datasource.model.Command.Command;
import com.Taylor.ChatProject.datasource.model.Command.CommandCreateNewChat;
import com.Taylor.ChatProject.datasource.model.Command.CommandGetChatForUsers;
import com.Taylor.ChatProject.datasource.model.Command.CommandGetPeers;
import com.Taylor.ChatProject.datasource.model.MessageHandler;
import com.Taylor.ChatProject.datasource.model.Observer.ChatListObserver;
import com.Taylor.ChatProject.datasource.model.Observer.Observer;
import com.Taylor.ChatProject.datasource.model.Observer.UpdateChatsTimer;
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

public class MainMenuWindow extends Observer {

    JTextField Username;
    JFrame frame;
    JTextField Password;

    JList<String> list;

    UpdateChatsTimer timer;

    private static MainMenuWindow singleton;

    public static MainMenuWindow getSingleton(){
        if (singleton != null) {
            return singleton;
        }
        singleton = new MainMenuWindow();
        return singleton;
    }

    public static void closeWindow(){
        singleton = null;
    }

    private MainMenuWindow() {
        timer = new UpdateChatsTimer();
        ChatListObserver.getSingleton().addObserver(this);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width / 4;
        int screenHeight = screenSize.height / 3;

        // Calculate the x and y coordinates to center the window
        int windowX = (screenSize.width - screenWidth) / 2;
        int windowY = (screenSize.height - screenHeight) / 2;

        frame = new JFrame("Main Menu");
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

        list = new JList<>();
        refillList();
        JScrollPane listScrollPane = new JScrollPane(list);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the selected item from the list
                    String selected = list.getSelectedValue();
                    if (selected != null) {
                        ClientInformation.getSingleton().setLatestRecipient(selected);
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
        timer.startExecution();
    }

    public void isVisible(boolean bool) {
        frame.setVisible(bool);
    }

    private void getChats(String chatPeer){
        CommandGetChatForUsers command = new CommandGetChatForUsers(ClientInformation.getSingleton().getUsername(), chatPeer);
        MessageHandler.getSingleton().queueCommand(command);
        while(!ReportHandler.getSingleton().hasReports()){}
        ChattingMenu menu = new ChattingMenu();
        System.out.println("fetching chat list");
    }

    public void doAction(ActionEvent e) {
        if (e.getActionCommand().equals("New Chat")) {
            openNewChatWindow();
        } else if (e.getActionCommand().equals("Settings")) {

        }
    }

    public void openNewChatWindow(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int windowX = (screenSize.width - 300) / 2;
        int windowY = (screenSize.height - 150) / 2;


        JFrame configFrame = new JFrame("New Chat");
        configFrame.setSize(300, 150);
        configFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configFrame.setLayout(new GridLayout(3, 2));
        configFrame.setLocation(windowX, windowY);

        JLabel uName = new JLabel("Recipient User Name: ");
        JTextField username = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save the IP address and port
                String usernameText = username.getText();

                //Add Code for creating new chat;
                CommandCreateNewChat cmd = new CommandCreateNewChat(usernameText);
                MessageHandler.getSingleton().queueCommand(cmd);
                ClientInformation.getSingleton().setLatestRecipient(usernameText);
                while(!ReportHandler.getSingleton().hasReports()){}
                if(ReportHandler.getSingleton().getNextReport().getSuccess()) {
                    getChats(usernameText);
                }

                configFrame.dispose(); // Close the configuration window
            }
        });


        configFrame.add(uName);
        configFrame.add(username);
        configFrame.add(createButton);

        configFrame.setVisible(true);
    }

    public void refillList(){
        CommandGetPeers peers = new CommandGetPeers();
        MessageHandler.getSingleton().queueCommand(peers);
        while(!ReportHandler.getSingleton().hasReports()){}
        GetPeersReport report = (GetPeersReport) ReportHandler.getSingleton().getNextReport();
        List<String> peersList = report.getPeers();

        String[] pList = new String[peersList.size()];
        for(int i = 0; i < pList.length; i++){
            pList[i] = peersList.get(i);
        }
        list.setListData(pList);
    }

    @Override
    public void update() {
        refillList();
    }
}
