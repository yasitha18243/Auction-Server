

import java.util.Random;

public class StockItems {
	
	private String symbol;
	private String securityName;
	private float price;
	
	public StockItems(String symbol, String securityName, float price) {  //making the constructor to set the item objects
		this.symbol = symbol;
		this.securityName = securityName;
		this.price = price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getSecurityName() {
		return securityName;
	}
	
	public float getPrice() {
		return price;
	}

}
