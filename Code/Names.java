

import java.util.HashMap;
import java.util.HashSet;



public class Names {
	private static HashSet<String> ItemSet = new HashSet<String>();  //hash set to store the entered names 
	
	public static void addName(String name) {//adding a name to set
		ItemSet.add(name);
	}
	
	public static boolean isExistname(String name) {  //checking whether name is existing
		return ItemSet.contains(name);
	}
}
