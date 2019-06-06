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
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

/*
* The server that can be run both as a console application or a GUI
 */
public class Server {

    private static int uniqueId;                        // a unique ID for each connection
    private final ArrayList<ClientThread> ClientList;   // an ArrayList to keep the list of the Client
    private final SimpleDateFormat date;                // to display time
    private final int port;                             // the port number to listen for connection
    private boolean serverOnline;                       // the boolean that will be turned off to stop the server
    private Blockchain bchain;

    //server constructor
    public Server(int port) {

        this.port = port;                               // the port
        date = new SimpleDateFormat("HH:mm:ss");        // to display hh:mm:ss
        ClientList = new ArrayList<>();     // ArrayList for the Client list
        bchain = new Blockchain();

    }

    public void start() {
        serverOnline = true;

        /* create socket server and wait for connection requests */
        try {
            //server socket
            ServerSocket sSocket = new ServerSocket(port);

            // infinite loop to wait for connections
            while (serverOnline) {
                display("Server waiting for Clients on port: " + port);

                Socket socket = sSocket.accept();                // accept connection

                if (!serverOnline) {
                    break;
                }
                ClientThread thread = new ClientThread(socket);  // make a thread for each client
                ClientList.add(thread);                          // save it in the ArrayList
                thread.start();
            }
            // stopping
            try {
                sSocket.close();

                //while there are any clients...
                for (int i = 0; i < ClientList.size(); ++i) {
                    ClientThread tc = ClientList.get(i);
                    try {

                        //closing input output and client
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Exception e) {
                display("Exception closing the server and clients: " + e);
            }
        } // something went bad with making client or such
        catch (IOException e) {
            String msg = " Exception on new ServerSocket: " + e + "\n";
            display(msg);
        }
    }

    public void saveMessages(String someMessage) {
        PrintWriter output;
        try {
            output = new PrintWriter(new FileWriter(port + ".txt", true));
            String temp = someMessage.substring(9);
            output.append(temp);
            output.close();
        } catch (IOException ex) {
        }
    }

    public void showBackloggedMessages(String username) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(port + ".txt"));
            String line;
            display(username + " pries broadcast");
            broadcast("\nBacklog so far:\n", username);
            while ((line = reader.readLine()) != null) {
                broadcast(line, username);
            }
        } catch (FileNotFoundException ex) {
        }
    }

    public Boolean registerNewUser(String message) {
        PrintWriter output;
        display(message);
        if (checkLoginfo(message)) {
            return false;
        }
        try {
            output = new PrintWriter(new FileWriter("loginInfo.txt", true));
            String temp = message + "\n";
            output.append(temp);
            output.close();
            return true;
        } catch (IOException ex) {

        }
        return false;
    }

    static Boolean checkLoginfo(String msg) {
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader("loginInfo.txt"));
            String line;
            while ((line = buffer.readLine()) != null) {
                if (line.equals(msg)) {
                    buffer.close();
                    return true;
                }
            }
        } catch (IOException ex) {
        }
        return false;
    }

    //Display an event (not a message) to the console
    private void display(String msg) {
        String time = date.format(new Date()) + " " + msg;
        System.out.println(time);
    }

    //broadcast messages to clients
    private synchronized void broadcast(String message) {
        String time = date.format(new Date());              //timestamp
        String messageLf;
        if (message.startsWith("#BLOCKCHAIN#")) {
            messageLf = message;
        } else {
            messageLf = time + " " + message + "\n";
        }
        //display message on server
        System.out.print(messageLf);
        saveMessages(messageLf);
        //removing clients which cant get message
        for (int i = ClientList.size(); --i >= 0;) {
            ClientThread client = ClientList.get(i);
            // try to write to the Client if it fails remove it from the list
            if (!client.writeMsg(messageLf)) {
                ClientList.remove(i);
                display("Client " + client.username + " removed from list.");
            }
        }
    }

    private synchronized void broadcast(String message, String username) {
        //display message on server
        System.out.print(message + "\n");
        //removing clients which cant get message
        ClientThread client = ClientList.get(ClientList.size() - 1);
        int i = ClientList.size() - 1;
        while (!client.username.equals(username) || i > -1) {
            display(client.username);
            client = ClientList.get(i);
            if (client.username.equals(username)) {
                break;
            } else {
                i--;
            }
        }
        if (ClientList != null && !ClientList.isEmpty()) {
            if (!client.writeMsg(message + "\n")) {
                ClientList.remove(client);
                display("Client " + client.username + " removed from list.");
            }
        }
        // try to write to the Client if it fails remove it from the list

    }

    // removing logged off clients
    synchronized void remove(int id) {
        // scan the array list until we found the Id
        for (int i = 0; i < ClientList.size(); ++i) {
            ClientThread ct = ClientList.get(i);
            // found it
            if (ct.id == id) {
                ClientList.remove(i);
                return;
            }
        }
    }

    //for each client making new thread
    class ClientThread extends Thread {
        // the socket where to listen/talk

        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;

        // id to cancel connection
        int id;

        // client username and password for checking
        String username, password;

        // new type of messages to more easily find messages
        ChatMessage clientMessage;

        String date; //log in timestamp

        // Constructor
        ClientThread(Socket socket) {
            id = ++uniqueId;
            this.socket = socket;

            System.out.println("Thread trying to create Object Input/Output Streams");
            try {
                // create output and input streams
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());
                // read the username and password
                username = (String) sInput.readObject();
                String[] temp = username.split("\\s");
                System.out.println(temp[0] + " " + temp[1]);
                Boolean isOnline = false;

                for (int i = 0; i < ClientList.size(); ++i) {
                    ClientThread ct = ClientList.get(i);
                    if (ct.username.equals(temp[0]) && !ct.isAlive()) {
                        isOnline = true;
                    }
                }

                if (!checkLoginfo(username) || isOnline) {
                    display(username);
                    sInput.close();
                    sOutput.close();
                    socket.close();
                    return;
                }
                username = temp[0];

                display(username + " just connected.");
                display(bchain.toString());

            } catch (IOException e) {
                display("Exception creating new Input/output Streams: " + e);
                return;
            } catch (ClassNotFoundException e) {
            }
            date = new Date().toString() + "\n";
        }

        // what will run forever
        @Override
        public void run() {

            for (int i = 0; i < bchain.count(); i++) {
                writeMsg("#BLOCKCHAIN#/" + bchain.blockchain.get(i).getVote() + "/"
                        + bchain.blockchain.get(i).getTimeStamp() + "/"
                        + bchain.blockchain.get(i).getHash() + "/" + (bchain.count() - 1));
            }
            // loop until server closed
            boolean serverOnline = true;
            while (serverOnline) {

                // read message
                try {
                    clientMessage = (ChatMessage) sInput.readObject();
                } catch (IOException e) {
                    display(username + " Vartotojas prisijunges arba, nera sistemoje ir pan.");
                    break;
                } catch (ClassNotFoundException e2) {
                    break;
                }
                String message = clientMessage.getMessage();

                // Switch on the type of message receive
                switch (clientMessage.getType()) {
                    case ChatMessage.MESSAGE:
                        if (message.isEmpty()) {
                            break;
                        } else {
                            broadcast(username + ": " + message);
                        }
                        break;
                    case ChatMessage.LOGOUT:
                        display(username + " disconnected with a LOGOUT message.");
                        serverOnline = false;
                        break;
                    case ChatMessage.BACKLOG: {
                        try {
                            display(username + " pries show");
                            showBackloggedMessages(username);
                        } catch (IOException ex) {
                        }
                    }
                    break;
                    case ChatMessage.REGISTER:
                        if (registerNewUser(message)) {
                            display(message + " has been created by Admin");
                        } else {
                            display(username + " is already in system");
                        }
                        break;
                    case ChatMessage.WHOISONLINE:
                        writeMsg("List of the users connected at " + date.format(new Date().toString()) + "\n");
                        // scan all the connected users
                        for (int i = 0; i < ClientList.size(); ++i) {
                            ClientThread ct = ClientList.get(i);
                            writeMsg((i + 1) + ") " + ct.username + " since " + ct.date);
                        }
                        break;

                    case ChatMessage.BLOCKCHAIN:
                        if (bchain.validate()) {
                            String[] parts = message.split("\\/");
                            bchain.add(new Block(parts[0], Long.parseLong(parts[2]), parts[1]));
                            bchain.reHash();
                            if (bchain.validate()) {
                                broadcast("#BLOCKCHAIN#/" + parts[0] + "/" + parts[2] + "/" + parts[1]);
                            } else {
                                broadcast("doesnt work :c\n");
                                display(bchain.blockchain.get(bchain.blockchain.size() - 1).toString());
                                bchain.blockchain.remove(bchain.blockchain.size() - 1);
                            }
                        }
                        break;
                    case ChatMessage.VALIDATE:
                        if (bchain.validate()) {
                            for (int i = 0; i < bchain.count(); i++) {
                                writeMsg("#BLOCKCHAIN#/" + bchain.blockchain.get(i).getVote() + "/"
                                        + bchain.blockchain.get(i).getTimeStamp() + "/"
                                        + bchain.blockchain.get(i).getHash() + "/"
                                        + bchain.count());
                            }
                        } else {

                        }
                        break;
                    case ChatMessage.VOTES:
                        Vote vtemp = bchain.votingCounting();
                        vtemp.sortDecreasing();
                        display(vtemp.toString());
                        writeMsg(vtemp.toString());
                        break;
                    case ChatMessage.SHOWCHAIN:
                        writeMsg(bchain.toString());
                        break;
                }
            }

            remove(id);
            close();
        }

        // try to close everything
        private void close() {
            try {
                if (sOutput != null) {
                    sOutput.close();
                }
            } catch (Exception e) {
            }
            try {
                if (sInput != null) {
                    sInput.close();
                }
            } catch (Exception e) {
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
            }
        }

        // Write string to client output stream
        private boolean writeMsg(String msg) {
            // if client is still connected send the message to it
            if (!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                sOutput.writeObject(msg);
                Writer output;
            } // informing about an error
            catch (IOException e) {
                display("Error sending message to " + username);
                display(e.toString());
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int portNumber = 6666;
        Server server = new Server(portNumber);
        server.start();
        for (int i = 0; i < 100; i++) {
            if (server.serverOnline) {
                server = new Server(portNumber++);
                server.start();
            }
        }
    }
}
