/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatservicewithblockchain;

/**
 *
 * @author blade
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

    // for I/O
    private ObjectInputStream sInput;       // to read from the socket
    private ObjectOutputStream sOutput;     // to write on the socket
    private Socket socket;
    private final ClientGUI gui;
    // the server, the port and the username
    private String server, username, password;
    private int port;
    Blockchain bchain;

    Client(String server, int port, String username, ClientGUI gui) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.gui = gui;
        bchain = new Blockchain();
    }

    public boolean start() {
        // try to connect to the server
        try {
            socket = new Socket(server, port);
        } catch (Exception ec) {
            display("Error while connecting to server:" + ec);
            return false;
        }
        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
        display(msg);
        // Creating both Data Stream
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException eIO) {
            display("Exception creating new Input/output Streams: " + eIO);
            return false;
        }
        // creates the Thread to listen from the server
        new ListenFromServer().start();
        // Send our username and password to the server
        try {
            sOutput.writeObject(username/* + "" + password*/);
            if (sInput.equals("wrong")) {
                disconnect();
            }
        } catch (IOException eIO) {
            display("Exception doing login : " + eIO);
            disconnect();
            return false;
        }
        return true;
    }

    //To send a message to the GUI
    public void display(String msg) {
        gui.append(msg + "\n");      //add text to text area
    }

    //To send a message to the server
    void sendMessage(ChatMessage msg) {
        try {
            sOutput.writeObject(msg);
        } catch (IOException e) {
            display("Exception writing to server: " + e);
        }
    }

    //if something goes wrong closing I/O streams and disconnect
    public void disconnect() {
        try {
            if (sInput != null) {
                sInput.close();
            }
        } catch (Exception e) {
        }
        try {
            if (sOutput != null) {
                sOutput.close();
            }
        } catch (Exception e) {
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
        }
        // inform the GUI
        if (gui != null) {
            gui.connectionFailed();
        }
    }

    public static void main(String[] args) {
        // default values
        int portNumber = 6666;
        String serverAddress = "localhost";
        String username = "";
        ClientGUI gui;
        gui = new ClientGUI("localhost", 6666);
        // client object
        Client client = new Client(serverAddress, portNumber, username, gui);
        //if cannot connect end process
        if (!client.start()) {
            return;
        }
        // wait for messages from user
        Scanner scan = new Scanner(System.in);
        // loop forever for message from the user
        while (true) {
            System.out.print("> ");
            // read message from user
            String msg = scan.nextLine();
            // logout if message is LOGOUT
            if (msg.equalsIgnoreCase("LOGOUT")) {
                client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
                // break to do the disconnect
                break;
            } // message WhoIsIn
            else if (msg.equalsIgnoreCase("WHOISIN")) {
                client.sendMessage(new ChatMessage(ChatMessage.WHOISONLINE, ""));
            } else if (msg.equalsIgnoreCase("REGISTER")) {
                client.sendMessage(new ChatMessage(ChatMessage.REGISTER, username));
            } else if (msg.equalsIgnoreCase("REGISTER")) {
                client.sendMessage(new ChatMessage(ChatMessage.BACKLOG, ""));
            } else if (msg.equalsIgnoreCase("BLOCKCHAIN")) {
                client.sendMessage(new ChatMessage(ChatMessage.BLOCKCHAIN, msg));

            } else if (msg.equalsIgnoreCase("VALIDATE")) {
                client.sendMessage(new ChatMessage(ChatMessage.VALIDATE, ""));
            } else {              // default to ordinary message
                client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg));
            }
        }
        // done disconnect
        client.disconnect();
    }

    /*
    * a class that waits for the message from the server and append them to the JTextArea
    * if we have a GUI
     */
    class ListenFromServer extends Thread {

        @Override
        public void run() {
            boolean test = false;
            while (true) {
                try {
                    String msg = (String) sInput.readObject();
                    if (!msg.startsWith("#BLOCKCHAIN#")) {
                        gui.append(msg);
                    } else if (msg.startsWith("#BLOCKCHAIN#")) {
                        String[] parts = msg.split("\\/");
                        if (bchain.count() == 1 && test == false) {
                            bchain.blockchain.set(0, new Block(parts[1], Long.parseLong(parts[2]), parts[3]));
                            bchain.reHash();
                            test = true;
                        } else {
                            if (!bchain.blockchain.get(bchain.count() - 1).getHash().equals(parts[3])) {
                                bchain.blockchain.add(new Block(parts[1], Long.parseLong(parts[2]), parts[3]));
                                bchain.reHash();
                            }

                        }
                    }
                } catch (IOException e) {
                    display("Server has close the connection: " + e);
                    if (gui != null) {
                        gui.connectionFailed();
                    }
                    break;
                } // can't happen with a String object but need the catch anyhow
                catch (ClassNotFoundException e2) {
                }
            }
        }
    }
}
