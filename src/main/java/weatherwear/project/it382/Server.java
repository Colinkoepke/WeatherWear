package weatherwear.project.it382;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.*;
import java.net.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.DarkSkyClient;
import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.Latitude;
import tk.plogitech.darksky.forecast.Longitude;

class Server{

  public static void main(String argv[]) throws Exception{
  
    String clientSentence;
    
    //Getting the port number to create a server socket and message to send to client from command line
    int port = 12345;
   // String messageToClient = argv[1];
    
    //Creating the object welcomeSocket of type ServerSocket and will hold the port number 
    //ServerSocket will listen for a connection request from some client 
    ServerSocket welcomeSocket = new ServerSocket(port);
    System.out.println("Server Socket Listening For Connection Request At Port: " + port);
    
    //This function is logic for when am incoming connection request is made by a client 
    while(true){
      
      //After a connection request is made and TCP then establishes a direct virtual pipe between
      //the client and server
      Socket connectionSocket = welcomeSocket.accept();
      System.out.println("Establishing TCP connection");
      System.out.println("Socket Created At Port: " + port);
      
      //Creates streams attached to the socket
      //BufferedReader inFromClient provides process input from the client socket
      //DataOutputStream outToClient provides process output to the client socket
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
      
      //Takes the process input from the client and stores it to a String clientSentence then prints the message
      clientSentence = inFromClient.readLine();
      System.out.println("Client's Message: " + clientSentence);
      String[] city = clientSentence.split(" ");
      
      String userLocation = city[0] + ", " + city[1];
      //Sending back new person object 
      //'\n\' tells the client its done receiving data
      //messageToClient = "PERSON OBJECT";
      //outToClient.writeBytes(messageToClient);
      ArrayList<String> summaryList = Server.getSummary(userLocation);
      for(int i = 0; i < summaryList.size(); i++){
          System.out.println(summaryList.get(i));
          outToClient.writeBytes(summaryList.get(i));
      }

      
      //Close and flush the server socket so we do not get a socket exception
      outToClient.flush();
      outToClient.close();
      inFromClient.close();
      connectionSocket.close();
      //break;
    }
    //welcomeSocket.close();
   
  }
  
  private static ArrayList<String> getSummary(String location) throws ForecastException{
      
    ArrayList<String> summaryList = new ArrayList<>();
    
    GeoApiContext context = new GeoApiContext.Builder()
        .apiKey("AIzaSyDawKzmSN0iFisR-gcYFlgFDutAK8plQ6g")
        .build();
    GeocodingResult[] results = null;
      try {
          results = GeocodingApi.geocode(context,
                  location).await();
      } catch (ApiException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (InterruptedException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    System.out.println(gson.toJson(results[0].geometry.location.lat));

      Double longitude = Double.parseDouble(gson.toJson(results[0].geometry.location.lng));
      Double latitude = Double.parseDouble(gson.toJson(results[0].geometry.location.lat));
      
      ForecastRequest request = new ForecastRequestBuilder()
        .key(new APIKey("74fd1ea1853ba1568046485397a24395"))
        .time(Instant.now().minus(5, ChronoUnit.DAYS))
        .language(ForecastRequestBuilder.Language.en)
        .units(ForecastRequestBuilder.Units.us)
        .exclude(ForecastRequestBuilder.Block.minutely)
        .extendHourly()
        .location(new GeoCoordinates(new Longitude(longitude), new Latitude(latitude))).build();

    DarkSkyClient client = new DarkSkyClient();
    
    
    String forecast = client.forecastJsonString(request);
    System.out.println(forecast);
    
    JSONObject obj = new JSONObject(forecast);
    String n = obj.getString("timezone");
    System.out.println(n);
    summaryList.add(n + '\n');
    
    
    System.out.println(obj.getJSONObject("currently").getInt("time"));
    summaryList.add(Integer.toString(obj.getJSONObject("currently").getInt("time")) + "\n");
    
    System.out.println(obj.getJSONObject("currently").getString("summary"));
    summaryList.add(obj.getJSONObject("currently").getString("summary") + '\n');
    
    System.out.println(obj.getJSONObject("currently").getDouble("precipProbability"));
    summaryList.add(Double.toString(obj.getJSONObject("currently").getDouble("precipProbability")) + '\n');
    //System.out.println(obj.getJSONObject("currently").getString("precipType"));
    //summaryList.add(obj.getJSONObject("currently").getString("precipType") + '\n');
    System.out.println(obj.getJSONObject("currently").getDouble("temperature"));
    summaryList.add(Double.toString(obj.getJSONObject("currently").getDouble("temperature")) + '\n');
    System.out.println(obj.getJSONObject("currently").getDouble("apparentTemperature"));
    summaryList.add(Double.toString(obj.getJSONObject("currently").getDouble("apparentTemperature")) + '\n');
    System.out.println(obj.getJSONObject("currently").getDouble("humidity"));
    summaryList.add(Double.toString(obj.getJSONObject("currently").getDouble("humidity")) + '\n');
    System.out.println(obj.getJSONObject("currently").getDouble("windSpeed"));
    summaryList.add(Double.toString(obj.getJSONObject("currently").getDouble("windSpeed")) + '\n');
    System.out.println(obj.getJSONObject("currently").getDouble("cloudCover"));
    summaryList.add(Double.toString(obj.getJSONObject("currently").getDouble("cloudCover")) + '\n');
    System.out.println(obj.getJSONObject("currently").getInt("uvIndex"));
    summaryList.add(Double.toString(obj.getJSONObject("currently").getInt("uvIndex")) + '\n');
    
    
    
    
    //how to parse JSOn http://theoryapp.com/parse-json-in-java/
    //API library https://github.com/200Puls/darksky-forecast-api
    return summaryList;
  }
}
