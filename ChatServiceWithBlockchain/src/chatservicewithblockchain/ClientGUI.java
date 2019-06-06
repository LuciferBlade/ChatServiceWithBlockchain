package chatservicewithblockchain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author blade
 */
public class ClientGUI extends javax.swing.JFrame {
    
    private static final long serialVersionUID = 1L;
    private Boolean connected;
    private Client client;
    private int port;
    private String defaultHost;

    //Constructor connection receiving a socket number
    ClientGUI(String host, int port) {
        super("Chat Client");
        
        initComponents();
        this.port = port;
        defaultHost = host;
        statusLabel.setVisible(false);
        
        logOutButton.setEnabled(false);
        clientListButton.setEnabled(false);
        sendButton.setEnabled(false);
        chatField.setEditable(false);
        backlogButton.setEnabled(false);
        infoWindow.setEditable(false);
        clearButton.setEnabled(false);
        addBlockButton.setEnabled(false);
        badHashButton.setEnabled(false);
        displayButton.setEnabled(false);
        validateButton.setEnabled(false);
        getVotes.setEnabled(false);
        serverChainButton.setEnabled(false);
        
    }

    // called by the Client to append text in the TextArea
    void append(String str) {
        infoWindow.append(str);
        infoWindow.setCaretPosition(infoWindow.getText().length() - 1);
    }
    
    void connectionFailed() {
        usernameField.setEditable(true);
        passwordField.setEditable(true);
        portField.setEditable(true);
        logInButton.setEnabled(true);
        regButton.setEnabled(true);
        logOutButton.setEnabled(false);
        clientListButton.setEnabled(false);
        statusLabel.setVisible(false);
        sendButton.setEnabled(false);
        chatField.setEditable(false);
        backlogButton.setEnabled(false);
        clearButton.setEnabled(false);
        addBlockButton.setEnabled(false);
        badHashButton.setEnabled(false);
        displayButton.setEnabled(false);
        validateButton.setEnabled(false);
        getVotes.setEnabled(false);
        serverChainButton.setEnabled(false);
        // don't react to a <CR> after the username
        //infoWindow.removeActionListener(this);
        connected = false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        logInButton = new javax.swing.JButton();
        regButton = new javax.swing.JButton();
        chatField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logOutButton = new javax.swing.JButton();
        clientListButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        statusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoWindow = new javax.swing.JTextArea();
        backlogButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        addBlockButton = new javax.swing.JButton();
        validateButton = new javax.swing.JButton();
        getVotes = new javax.swing.JButton();
        displayButton = new javax.swing.JButton();
        badHashButton = new javax.swing.JButton();
        serverChainButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kalbėkime client");
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        logInButton.setText("Prisijungti");
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        regButton.setText("Registruotis");
        regButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Slapyvardis");

        jLabel2.setText("Slaptažodis");

        logOutButton.setText("Atsijungti");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        clientListButton.setText("Prisijungusių sąrašas");
        clientListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientListButtonActionPerformed(evt);
            }
        });

        sendButton.setText("Siūsti");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Portas");

        statusLabel.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        statusLabel.setText("Online");

        infoWindow.setColumns(20);
        infoWindow.setRows(5);
        jScrollPane1.setViewportView(infoWindow);

        backlogButton.setText("Back Log");
        backlogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backlogButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Išvalyti");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        addBlockButton.setText("Add block");
        addBlockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBlockButtonActionPerformed(evt);
            }
        });

        validateButton.setText("Validate chain");
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });

        getVotes.setText("Vote results");
        getVotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getVotesActionPerformed(evt);
            }
        });

        displayButton.setText("Own chain");
        displayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayButtonActionPerformed(evt);
            }
        });

        badHashButton.setText("Add bad hash");
        badHashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                badHashButtonActionPerformed(evt);
            }
        });

        serverChainButton.setText("Server chain");
        serverChainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverChainButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chatField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(displayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(addBlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(badHashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(validateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getVotes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(serverChainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(regButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backlogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1))
                        .addGap(12, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clientListButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backlogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(badHashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(validateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(displayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(getVotes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(serverChainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed
        String username = usernameField.getText().trim();
        String password;
        password = new String(passwordField.getPassword()).trim();
        String portNumber = portField.getText().trim();
        //usrname
        if (username.length() == 0) {
            infoWindow.append("taip ir yra name");
            return;
        } //pass
        else if (username.length() == 0) {
            infoWindow.append("taip ir yra pass");
            return;
        } //portas
        else if (portNumber.length() == 0) {
            return;
        }
        int newPort;
        try {
            newPort = Integer.parseInt(portNumber);
        } catch (Exception en) {
            return;
        }
        client = new Client(defaultHost, newPort, username + " " + password, this);
        if (!client.start()) {
            connectionFailed();
            return;
        }
        client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, ""));
        
        connected = true;
        //On loging in shows who's online
        //client.sendMessage(new ChatMessage(ChatMessage.WHOISONLINE, "")); 
        statusLabel.setVisible(true);
        usernameField.setEditable(false);
        passwordField.setText("");
        passwordField.setEditable(false);
        portField.setEditable(false);
        logInButton.setEnabled(false);
        logOutButton.setEnabled(true);
        regButton.setEnabled(false);
        clientListButton.setEnabled(true);
        chatField.setEditable(true);
        sendButton.setEnabled(true);
        backlogButton.setEnabled(true);
        clearButton.setEnabled(true);
        addBlockButton.setEnabled(true);
        badHashButton.setEnabled(true);
        displayButton.setEnabled(true);
        validateButton.setEnabled(true);
        getVotes.setEnabled(true);
        serverChainButton.setEnabled(true);

    }//GEN-LAST:event_logInButtonActionPerformed

    private void clientListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientListButtonActionPerformed
        client.sendMessage(new ChatMessage(ChatMessage.WHOISONLINE, ""));
    }//GEN-LAST:event_clientListButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, chatField.getText()));
        chatField.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void regButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regButtonActionPerformed
        String username = usernameField.getText();
        if (username.equals("")) {
            return;
        }
        String password = passwordField.getText().trim();
        if (password.equals("")) {
            return;
        }
        client = new Client(defaultHost, 6666, "Admin admin", this);
        if (!client.start()) {
            return;
        }
        connected = true;
        client.sendMessage(new ChatMessage(ChatMessage.REGISTER, username + " " + password));
        client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
        client.disconnect();
        connected = false;
    }//GEN-LAST:event_regButtonActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged

    }//GEN-LAST:event_formWindowStateChanged

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
        connectionFailed();
        client.disconnect();
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void backlogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backlogButtonActionPerformed
        client.sendMessage(new ChatMessage(ChatMessage.BACKLOG, ""));
    }//GEN-LAST:event_backlogButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        infoWindow.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void addBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBlockButtonActionPerformed
        client.bchain.add(chatField.getText());
        Block temp = client.bchain.blockchain.get(client.bchain.count() - 1);
        if (client.bchain.validate()) {
            client.sendMessage(new ChatMessage(ChatMessage.BLOCKCHAIN, temp.getVote() + "/" + temp.getHash() + "/" + temp.getTimeStamp()));
        } else {
            client.display("ERROR!!! Blockchain is invalid!\nSeeking help!!!");
            client.bchain = new Blockchain();
            client.bchain.blockchain.remove(0);
            client.sendMessage(new ChatMessage(ChatMessage.VALIDATE, ""));
        }
        chatField.setText("");
    }//GEN-LAST:event_addBlockButtonActionPerformed

    private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateButtonActionPerformed
        //client.sendMessage(new ChatMessage(ChatMessage.VALIDATE, ""));

        //test
        //Block b = new Block("lol", new Block(), "haha");
        //client.bchain.add(b);
        if (client.bchain.validate()) {
            client.display("blockchain is valid!\n");
        } else {
            client.display("ERROR!!! Blockchain is invalid!\nSeeking help!!!");
            client.bchain = new Blockchain();
            client.bchain.blockchain.remove(0);
            client.sendMessage(new ChatMessage(ChatMessage.VALIDATE, ""));
            
        }
    }//GEN-LAST:event_validateButtonActionPerformed

    private void getVotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getVotesActionPerformed
        client.sendMessage(new ChatMessage(ChatMessage.VOTES, ""));
    }//GEN-LAST:event_getVotesActionPerformed

    private void displayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayButtonActionPerformed
        client.display(client.bchain.toString());
    }//GEN-LAST:event_displayButtonActionPerformed

    private void badHashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_badHashButtonActionPerformed
        client.bchain.add(new Block("Get rekt!!!", 123456, "hahahahaha"));
        client.display("Hahahaha!!! Block: 'Get rekt!!!', timeStamp: '123456', hash: 'hahahahaha' was added\nDeal with it\n");
    }//GEN-LAST:event_badHashButtonActionPerformed

    private void serverChainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverChainButtonActionPerformed
        client.sendMessage(new ChatMessage(ChatMessage.SHOWCHAIN, ""));
    }//GEN-LAST:event_serverChainButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientGUI cGUI;
                cGUI = new ClientGUI("localhost", 6666);
                cGUI.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBlockButton;
    private javax.swing.JButton backlogButton;
    private javax.swing.JButton badHashButton;
    private javax.swing.JTextField chatField;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton clientListButton;
    private javax.swing.JButton displayButton;
    private javax.swing.JButton getVotes;
    private javax.swing.JTextArea infoWindow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logInButton;
    private javax.swing.JButton logOutButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField portField;
    private javax.swing.JButton regButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton serverChainButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JButton validateButton;
    // End of variables declaration//GEN-END:variables

}
