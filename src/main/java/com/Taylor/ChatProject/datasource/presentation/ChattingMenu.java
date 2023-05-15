package com.Taylor.ChatProject.datasource.presentation;
import com.Taylor.ChatProject.datasource.ClientInformation;
import com.Taylor.ChatProject.datasource.communications.Datatypes.ChatMessage;
import com.Taylor.ChatProject.datasource.communications.Datatypes.ChatThread;
import com.Taylor.ChatProject.datasource.model.report.ChatForUsersReport;
import com.Taylor.ChatProject.datasource.model.report.Report;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChattingMenu extends JFrame {
        private JTextArea chatArea;
        private JTextField inputField;
        private JButton sendButton;

        public ChattingMenu() {
            setTitle("Chat Window");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 400);

            chatArea = new JTextArea();
            chatArea.setEditable(false);

            if(ReportHandler.getSingleton().hasReports()){
                ChatForUsersReport report = (ChatForUsersReport) ReportHandler.getSingleton().getNextReport();
                fillInitialChatArea(report.getChats());
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

            add(panel);
            this.setVisible(true);
        }

        private void sendMessage() {
            String message = inputField.getText();
            ChatMessage msg = new ChatMessage(ClientInformation.getSingleton().getUsername(), message);
            appendToChatArea(msg);
            inputField.setText("");
        }

        public void fillInitialChatArea(ChatThread thread){
            List<ChatMessage> messageList = thread.getChats();
            for(ChatMessage message : messageList){
                appendToChatArea(message);
            }
        }

        public void appendToChatArea(ChatMessage message) {
            chatArea.append(message.getSender() + ": " + message.getMessage() + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        }
    }
