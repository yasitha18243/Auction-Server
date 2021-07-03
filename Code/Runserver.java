
import java.text.DecimalFormat;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Runserver {
	static ReadCsv obj;
	static String [][] stockArray;
	private static DecimalFormat df;
	
	public static void main(String[] args) {
		System.out.println("Server Started");//printing the message to inidicate the server has been started
		df = new DecimalFormat("0.00");
		obj = new ReadCsv();//making the object for reading the .csv file

		String [] s = {"FB", "VRTU","MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"}; //string array of the symbols of the items
				
		
		stockArray = new String[8][3];  //array to store the details of the items
		
		//storing the item details in the array
		 for(int i =0; i < 8; i++) {
			 stockArray[i][0] = s[i];
			 stockArray[i][1] = ReadCsv.returnName(s[i]);
			 String price = df.format(ReadCsv.returnPrice(s[i]));
			 stockArray[i][2] = price+"$"; 
		 }

		 ServerGui obj1 = new ServerGui();
		 obj1.runGui(stockArray);     //running server Gui
		
	     UpdateTableRunnable con2 = new UpdateTableRunnable();
		 new Thread(con2).start();  //starting the thread which update the table
			   
		 ServerApp S = new ServerApp();
		 S.runServer();  //starting the server
		 
	}
	
	
	
}
