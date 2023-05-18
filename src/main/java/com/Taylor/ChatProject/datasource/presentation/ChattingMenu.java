package com.Taylor.ChatProject.datasource.presentation;
import com.Taylor.ChatProject.datasource.ClientInformation;
import com.Taylor.ChatProject.datasource.communications.Datatypes.ChatMessage;
import com.Taylor.ChatProject.datasource.communications.Datatypes.ChatThread;
import com.Taylor.ChatProject.datasource.model.Command.CommandGetChatForUsers;
import com.Taylor.ChatProject.datasource.model.Command.CommandSendNewChat;
import com.Taylor.ChatProject.datasource.model.MessageHandler;
import com.Taylor.ChatProject.datasource.model.Observer.MessageStateObserver;
import com.Taylor.ChatProject.datasource.model.Observer.Observer;
import com.Taylor.ChatProject.datasource.model.Observer.TimerObserver;
import com.Taylor.ChatProject.datasource.model.Observer.UpdateMessageTimer;
import com.Taylor.ChatProject.datasource.model.report.ChatForUsersReport;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ChattingMenu extends TimerObserver {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private UpdateMessageTimer timer;


    public ChattingMenu() {
        timer = new UpdateMessageTimer();
        MessageStateObserver.getSingleton().addObserver(this);
        frame = new JFrame("Chat Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        System.out.println("Latest Recipient: " + ClientInformation.getSingleton().getLatestRecipient());

        if(ReportHandler.getSingleton().hasReports()){
            ChatForUsersReport report = (ChatForUsersReport) ReportHandler.getSingleton().getNextReport();
            fillChatArea(report.getChats());
        }

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                onClose();
            }
        });
    }

    private void sendMessage() {
        String message = inputField.getText();
        ChatMessage msg = new ChatMessage(ClientInformation.getSingleton().getUsername(), message);
        appendToChatArea(msg);
        inputField.setText("");
        CommandSendNewChat cmd = new CommandSendNewChat(ClientInformation.getSingleton().getUsername(),
                                                        ClientInformation.getSingleton().getLatestRecipient(),
                                                        message);
        MessageHandler.getSingleton().queueCommand(cmd);
    }

    public void fillChatArea(ChatThread thread){
        refillChatArea(thread);
        timer.startExecution();
    }

    public void refillChatArea(ChatThread thread){
        List<ChatMessage> messageList = thread.getChats();
        for(ChatMessage message : messageList){
            appendToChatArea(message);
        }
    }

    public void appendToChatArea(ChatMessage message) {
        chatArea.append(message.getSender() + ": " + message.getMessage() + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    @Override
    public void update() {
        chatArea.setText(null);
        System.out.println("fetching new messages");
        CommandGetChatForUsers cmd = new CommandGetChatForUsers(ClientInformation.getSingleton().getUsername(),
                ClientInformation.getSingleton().getLatestRecipient());

        System.out.println("Me: " + ClientInformation.getSingleton().getUsername() + " Other: " + ClientInformation.getSingleton().getLatestRecipient());
        MessageHandler.getSingleton().queueCommand(cmd);
        while(!ReportHandler.getSingleton().hasReports()){}
        ChatForUsersReport report = (ChatForUsersReport) ReportHandler.getSingleton().getNextReport();
        if(report.getChats() != null) {
            System.out.println(report);
            refillChatArea(report.getChats());
        }
    }

    private void onClose(){
        timer.stopExecution();
        ClientInformation.getSingleton().setLatestRecipient(null);
    }
}
