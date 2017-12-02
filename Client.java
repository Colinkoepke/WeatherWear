//package weatherwear.project.it382;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//tcp client
class Client{


  public static void main(String argv[])throws Exception{
    
    String state;
    String city;
    String hostMessage;
    String hostMessage2;
    List<String> apiMessages = new ArrayList<>();
    List<String> serverMessages = new ArrayList<>();
    
    
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
    while((hostMessage = inFromServer.readLine()) != null){
        System.out.println("FROM SERVER: " + hostMessage);
        apiMessages.add(hostMessage);
    }
    
    System.out.println("Weather Summary:");
    System.out.println("-----------------");
    System.out.println("Summary: " + apiMessages.get(2));
    System.out.println("Percip Prob: " + apiMessages.get(3));
    System.out.println("Temperature: " + apiMessages.get(4));
    System.out.println("Apparant Temperature: " + apiMessages.get(5));
    System.out.println("Humidity: " + apiMessages.get(6));
    System.out.println("Windspeed: " + apiMessages.get(7));
    System.out.println("Cloud Coverage: " + apiMessages.get(8));
    System.out.println("UV Index: " + apiMessages.get(9));
    System.out.println("-----------------");
    
    //Closing the client socket
    System.out.println("Closing Client Connection");
    outToServer.flush();
    outToServer.close();
    inFromServer.close();
    clientSocket.close();
    
    
    Socket clientSocket2 = new Socket(serverHostName, 12166);
    DataOutputStream outToServer2 = new DataOutputStream(clientSocket2.getOutputStream());
    BufferedReader inFromServer2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
      
    for(int i = 0; i < apiMessages.size(); i++){
	outToServer2.writeBytes(apiMessages.get(i)+ '\n');
	System.out.println(apiMessages.get(i));
	outToServer2.flush();
     }
     
     System.out.println("Message sent to server.... waiting for response");
      
      while((hostMessage2 = inFromServer2.readLine()) != null){
	 System.out.println("FROM SERVER: " + hostMessage2);
	 serverMessages.add(hostMessage2); 
      }
      System.out.println("Closing Client Connection");
      outToServer2.flush();
      outToServer2.close();
      inFromServer2.close();
      clientSocket2.close();
      
  }
}
