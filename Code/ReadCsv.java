

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ReadCsv {
	 
	private static HashMap<String, StockItems> ItemCollection;   //hashmap to store the read details from the .csv file

	public ReadCsv(){
		
		        ItemCollection = new HashMap <String, StockItems>();
				String file = "stocks.csv";
				BufferedReader fileReader = null;
				
				
				//Delimiter used in CSV file
				final String DELIMITER = ",";
				try 
				{
					String line = "";
					//Create the file reader
					fileReader = new BufferedReader(new FileReader(file));
					
					//Read the file line by line
					while ((line = fileReader.readLine()) != null) 
					{
						//Get all cell items available in line
						String[] cellitem = line.split(DELIMITER);

						Random rand = new Random(); //variable to set random price 
						float price = (float) (200.00*(rand.nextFloat())); //getting random price

						StockItems item = new StockItems(cellitem[0], cellitem[1], price);
						ItemCollection.put(cellitem[0], item);   //storing the items in to the hashmap
						
					
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
				} 
				finally 
				{
					try {
						fileReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				
	
			}
	//this method will check the given symbol is existing
	public static boolean isContain(String symbol) {
		return ItemCollection.containsKey(symbol);
	}
	
	//this method will update the price of the items in hashmap according to the symbol
	public static void updatePrice(String symbol, float price) {
		StockItems item = ItemCollection.get(symbol);
	       item.setPrice(price);
	       ItemCollection.put(symbol, item);
	}
	//this will return the price to the item according to the symbol
	public static float returnPrice(String symbol) {
		StockItems item = ItemCollection.get(symbol);
		return item.getPrice();
	}
	
	//this will return the company name of the symbol
	public static String returnName(String symbol) {
		StockItems item = ItemCollection.get(symbol);
		return item.getSecurityName();
	}
	
	

}
