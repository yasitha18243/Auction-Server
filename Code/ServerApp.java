

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
      private int port = 2000;
      ServerSocket serverSocket;
      
      public void runServer() {
    	 try {
    	   serverSocket = new ServerSocket(port);	
    			 
    	 }catch(IOException e) {
    		 System.out.println(e.getMessage());
    	 }
    	 
    	 while (true) {
    		 try {
    			 Socket clientSocket = serverSocket.accept();    //accepting the client connection
    			 ConnectionRunnable con = new ConnectionRunnable(clientSocket);
    			 new Thread(con).start();                //starting the connection thread
    			 
    			 
    		 }catch(IOException e){
    			 System.out.println(e.getMessage());
    		 }
    	 }
    	 
    	
 			
 		
         
      }
}
