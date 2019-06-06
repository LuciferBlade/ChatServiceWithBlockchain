# 4th task for multithreading programing course
  4th task for multithreading programing course

## Useful links for creating similar applications
* https://www.geeksforgeeks.org/socket-programming-in-java/
* http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
* https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa

## Project Members
  * Mindaugas Burvys
  
## Project

# Project name "Chat service 'Kalbėkime'" expanded with blockchain

# About the project 
* Project was initialy created as a simple chat application between server and client by synchronizing the messages in order to prevent errors in server handling as well as, having access with multiple client to chat on multiple local ports (as chat channels).
* Later on this application was decided to be expanded with blockchain, as per task for multithreading programing course as it already has synchronization of messages, allows to have multiple clients and is quite simply and fundamentaly strongly made based on ChatMessage template which allows easy message exchange between server and client preventing even more possible errors and mistakes by server or the client.
* Chat application portion of the code is pretty simple, it has login autentications using username and passwords, simplified registration and log out functionality. Also it has functions to clear screen, check all client list and read the backlog of sent messages on this port.
* Blockchain itself is synchronized, blocks between clients are managed by the server in order to keep same list of blocks between all of them. Using the clientGUI application you can add blocks to the list, check your block which are present on the client side, add "faulty" block in order to check if synchronization and block management works, validate the blocks on your side and, if they are bad, new blocks are gonna get obtained from the server, check block list on the server and finally get "Vote count" based on values of valid blockchain.

## Application used to create a project
* NetBeans - Used mainly to write a code, develop GUI and everything else.


