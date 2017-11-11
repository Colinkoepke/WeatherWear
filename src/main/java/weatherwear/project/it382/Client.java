package weatherwear.project.it382;

import java.io.*;
import java.net.*;

//tcp client
class Client{


  public static void main(String argv[])throws Exception{
    
    String state;
    String city;
    String hostMessage;
    
    //Getting the server host name and port number to send a connection request too 
    String serverHostName = argv[0];
    int port = Integer.parseInt(argv[1]);
    
    //Creates a stream object to be used for the user to send the server a message 
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    
    //Instantiates the TCP connection between client and host by the user provided host name and port number
    //which the client performs a DNS loopup to establish the TCP connection
    Socket clientSocket = new Socket(serverHostName, port);
    
    //Creates stream objects attached to the socket
    //the outputstream provides process output to the socket and the bufferedreader provides process input from the socket
    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    
    //Uses the stream inFromUser to get the user's city and state
    System.out.println("Please enter your state");
    state = inFromUser.readLine();
    System.out.println("Please enter your city");
    city = inFromUser.readLine();
    //Combine into one string to send to server
    String sentence = state + " " + city;
    
    //Sends the user message from the client socket into the TCP pipe for the server socket to receive
    //'\n\' tells the server its done receiving data
    outToServer.writeBytes(sentence + '\n');
    System.out.println("Message sent to server.... waiting for response");
    
    //Takes characters from server and get placed to string hostMessage 
    hostMessage = inFromServer.readLine();
    System.out.println("FROM SERVER: " + hostMessage);
    
    //Closing the client socket
    System.out.println("Closing Client Connection");
    outToServer.close();
    inFromServer.close();
    clientSocket.close();
  }

}
