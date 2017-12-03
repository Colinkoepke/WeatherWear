import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class TCPServer{
  public static void main(String args[]) throws Exception{
  
    String clientSentence;
    
    //create serversocket with the port number entered
    ServerSocket welcomeSocket = new ServerSocket(12346);
    
    System.out.println("Server running");
    ArrayList<String> summaryList = new ArrayList<>();
    
    //while the server is running, accept connection sockets.
    //take an input stream from the client then be prepared to write to client
    while(true){
      
      Socket connectionSocket = welcomeSocket.accept();
      summaryList = new ArrayList<>();
      System.out.println("Connection Receieved");
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
      
      int count = 0;
      while(count < 10){
	  clientSentence = inFromClient.readLine();
          System.out.println("FROM CLIENT:" + clientSentence);
          summaryList.add(clientSentence);
          count++;
      }
      
      ArrayList<String> cList = TCPServer.findResult(summaryList);
      
      for(int i = 0; i< cList.size(); i++){
	System.out.println(cList.get(i));
        outToClient.writeBytes(cList.get(i)+ '\n');
      }
      
      
      outToClient.flush();
      inFromClient.close();
      outToClient.close();
      connectionSocket.close();
      //break;
    }
    //welcomeSocket.close();
  
  }
  
  
  
  public static ArrayList<String> findResult(ArrayList<String> data){
      ArrayList<String> a = new ArrayList<>();
      ArrayList<String> t = new ArrayList<>();
      ArrayList<String> l = new ArrayList<>();
      ArrayList<String> m = new ArrayList<>();
      ArrayList<String> finalList = new ArrayList<>();
      for(int i = 0; i< data.size(); i++){
      
	 switch(i){
	 
	    case 2:
	      m.add(data.get(i));
	      break;
	      
	    case 3:
	      if(Double.parseDouble(data.get(i)) == 1){
		m.add("Prepare for rain showers");
		a.add("umbrella");
	      }else{
		m.add("No rain today");
	      }
	      break;
	      
	    case 4:
		  m.add(data.get(i));
	      break;
	      
	    case 5:
	      m.add(data.get(i));
		  if(Double.parseDouble(data.get(i)) < 30){
		    a.add("gloves");
		    a.add("winter hat");
		    t.add("winter coat");
		    l.add("long pants");
		    l.add("heavy shoes");
		  }else if(Double.parseDouble(data.get(i)) > 30 && Double.parseDouble(data.get(i)) < 65){
		    t.add("light jacket");
		    l.add("long pants");
		    l.add("light shoes");
		  }else if(Double.parseDouble(data.get(i)) > 65 && Double.parseDouble(data.get(i)) < 90){
		    t.add("tshirt");
		    l.add("shorts");
		    l.add("light shoes");
		  }else if(Double.parseDouble(data.get(i)) > 90){
		    t.add("tank top");
		    l.add("shorts");
		    l.add("sandals"); 
		  }
	      break;
	      
	    case 6:
	      if(Double.parseDouble(data.get(i)) > .33 && Double.parseDouble(data.get(i)) < .66 ){
		m.add("Moderate Humidity");
	      }else if(Double.parseDouble(data.get(i)) > .66){
		m.add("High Humidity");
	      }else{
		m.add("Low Humidity");
	      }
	      break;
	      
	    case 7:
	      if(Double.parseDouble(data.get(i)) > 5 && Double.parseDouble(data.get(i)) < 10){
		m.add("Moderate Winds");
	      }else if(Double.parseDouble(data.get(i)) > 10){
		m.add("High Winds");
	      }else{
		m.add("Low Winds");
	      }
	      break;
	      
	    case 8:
	      m.add(data.get(i));
	      break;
	      
	    case 9:
	      if(Double.parseDouble(data.get(i)) == 1){
		m.add("High UV Index");
		a.add("Sun Glasses");
		a.add("Sun Block");
	      } else {
		m.add("Normal UV Index");
	      }
	      break;  
	    default:
	      break;
	 }
      }
      
      finalList.addAll(m);
      finalList.addAll(a);
      finalList.addAll(t);
      finalList.addAll(l);
      return finalList;
  }
  
}