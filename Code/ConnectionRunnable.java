

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;

public class ConnectionRunnable implements Runnable{
	
	 private Socket Socketconnection;
	 private static DecimalFormat df;
	 private static String s1;
	  
	 public ConnectionRunnable (Socket clientSocket) {
		 Socketconnection = clientSocket;
	 }

	@Override
	public void run() {
		
		try {
			 PrintWriter out = new PrintWriter(new OutputStreamWriter(Socketconnection.getOutputStream()));  //making objects to outputstreams and input streams
			 BufferedReader in = new BufferedReader(new InputStreamReader(Socketconnection.getInputStream()));
	         
	         int count1 = 0;
	         int count2 = 0;
	         df = new DecimalFormat("0.00");
	         
	         out.println("Welcome to Auction Server!");  
	         out.flush();
	         out.println("Enter your name: ");    //printing message to getting the name
	         out.flush();
	         String name = in.readLine();        //storing the name
	         
	         if(Names.isExistname(name)) {          //this will run when the name is already existing
	        	 while(Names.isExistname(name)) {
	        		 out.println("Your name is already existing....Enter a different name:  ");  //printing the message to get the name
		        	 out.flush();
		        	 name = in.readLine();
	        	 }
	        	 Names.addName(name);//adding the name to the set
	        	 
                
	         }
	         else{
	        	 Names.addName(name);
	        	
	         }
	         
	         out.println("Enter the Symbol of the security which you are willing to bid:  ");   //printing the message to get the symbol
	         out.flush();
	         String symbol = in.readLine(); //storing the symbol
	         
	        
	         
	         if(ReadCsv.isContain(symbol)) {   //if the symbol is correct this will run
	         	 
	        	 out.println("Current cost of the security:  "+ df.format(ReadCsv.returnPrice(symbol))+"$");
	        	 out.flush();
	         }
	         else if(symbol.equals("Exit")){
	        	 Socketconnection.close();
	         }
	         else {
	         	
	           while(!ReadCsv.isContain(symbol)){
	        	out.println("Symbol is invalid...Enter the symbol again: ");  //if the symbol is not containing asking the symbol again
	        	out.flush();
	             symbol = in.readLine();
               }

               out.println("Current cost of the security:  "+ df.format(ReadCsv.returnPrice(symbol))+"$");	//showing the current cost of security
               out.flush();        	 
	         }
	         
	         out.println("Place your bid: ");   //asking to place the bid
	         out.flush();   
	         String bidprice = in.readLine();   //storing the bid
	         
	         if(bidCheck(symbol, bidprice)) {   //checking whether the bid is acceptable
	        	 out.println("Your bid is acceptable. Type 'confirm' and press enter to add the changes. If you are not willing to confirm type 'Exit' and press enter to quit!");
	        	 count1++;
	        	 out.flush();
	         }
	         else {     //if bid is not acceptable this will run               
	        	 out.println("Your bid is not enough, Current cost of the security is : "+ df.format(ReadCsv.returnPrice(symbol))+"$"+" Bid again or else type Exit and press enter to quit!");
	        	 count2++;
                 out.flush();
                 out.println("\nPlace your bid: "); 
                 out.flush();
	         }
           
	         
	         
	         s1 = in.readLine();
	         
	         if(count1 ==1){    //if bid is acceptable this will run
	         if(s1.equals("confirm")) {
	        	 
	        	 updateCollection(symbol, bidprice);  //updating the hashmap
	        	 updateditem(name, symbol, bidprice); //updating the text area in gui
	        	 out.println("Your bid is succefully added to the stock!...closing the connection");
	        	 out.flush();
	        	 Socketconnection.close();
	        	 
	         }
	         else if(s1.equals("Exit")){
	         	  out.println("Your are exiting from the server!");
	         	  out.flush();
                Socketconnection.close(); 
	         }
	        }

	        else if(count2 == 1){ //if the bid is not acceptable this will run

	         if(s1.equals("Exit")){
	         	 out.println("You are exiting from the server!");
	         	 out.flush();
	        	 Socketconnection.close();
	         }
	         else{
	         	if(bidCheck(symbol, s1)){  //checking whether the second bidding is acceptable
                   out.println("Your bid is acceptable. Type 'confirm' and press enter to add the changes. If you are not willing to confirm type 'Exit' and press enter to quit!");
                   out.flush();
	         	}

	         	String s2;
	         	s2 = in.readLine();

	         	if(s2.equals("confirm")){//this will update the stock if confirmation is given
	         	
                 updateCollection(symbol, s1);  //update the hashemap
                 updateditem(name, symbol, s1); //update the text area in gui
	        	 out.println("Your bid is succefully added to the stock!...closing the connection");
	        	 out.flush();
	        	 Socketconnection.close();
	         	}

	         	else if(s2.equals("Exit")){
                 out.println("You are exiting from the server!");
	         	 out.flush();
	        	 Socketconnection.close();
	         	}
	         }
	        }
	         
	         
	         
	         
	         
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//this will check if the bid is acceptable
	public synchronized boolean bidCheck(String symbol, String bidprice) {
		float B_price = Float.parseFloat(bidprice);
		if(ReadCsv.returnPrice(symbol) < B_price) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//this will update the price of the items in hashmap
	public synchronized void updateCollection(String symbol, String bidprice) {
	     float new_price = Float.parseFloat(bidprice);
		 ReadCsv.updatePrice(symbol, new_price);

	}
    //this will return the array to get the details to update the text area
	public static void updateditem(String nameofbidder,String sym,String price) {
		String [] updateArray = new String[3];

			updateArray[0] = nameofbidder;
			updateArray[1] = sym;
			updateArray[2] = price;
			ServerGui.BidTracker(updateArray);
		
		
		
	}

}
