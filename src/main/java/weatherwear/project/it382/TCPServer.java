import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class TCPServer{
  public static void main(String args[]) throws Exception{
  
    String clientSentence;
    String capitalizedSentence;
    
    //get port number from what user enters in command line
    //int portNumber = Integer.parseInt(args[0]);
    
    //create serversocket with the port number entered
    ServerSocket welcomeSocket = new ServerSocket(12346);
    
    System.out.println("Server running");
    ArrayList<String> summaryList;
    //while the server is running, accept connection sockets.
    //take an input stream from the client then be prepared to write to client
    while(true){
      
      summaryList = new ArrayList<>();
        
      Socket connectionSocket = welcomeSocket.accept();
      System.out.println("Connection Receieved");
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
      
      //get line user enters into the client and then uppercase the sentence
      while((clientSentence = inFromClient.readLine()) != null){
          System.out.println(clientSentence);
          summaryList.add(clientSentence);
      }
      
      for(int i=0;i < summaryList.size(); i++){
          System.out.println(summaryList.get(i));
      }
      
      //send back to client
      outToClient.writeBytes("message receieved from client");
      outToClient.flush();
      outToClient.close();
      connectionSocket.close();
    }
    
  
  }
}