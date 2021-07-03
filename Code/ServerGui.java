

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ServerGui extends JPanel{
    static JLabel [][] lablearray;   //label array to make the table
    JLabel labelauction;
    JPanel gridPanel1;               //panel to store the grid
    JPanel gridPanel2;
    JPanel panel;                   //panel to store the exit, reset buttons
    static JTextArea textarea;      //text area to track the session
    static String [][] stock;       //array of contaning initial details of the eight items
    JScrollPane sbrText;            //scroll to scroll the text area
    private static DecimalFormat df;
    
    public ServerGui(){
    	super(new BorderLayout());
    	
    	
    	
    	//setting the grid panel for the values
        gridPanel1 = new JPanel();               
        gridPanel1.setLayout(new GridLayout(8, 3));
        gridPanel1.setBackground(Color.WHITE);
        gridPanel1.setPreferredSize(new Dimension(1000, 600));
        
        //setting the grid panel for header
        gridPanel2 = new JPanel();               
        gridPanel2.setLayout(new GridLayout(1, 3));
        gridPanel2.setBackground(Color.blue);
        gridPanel2.setPreferredSize(new Dimension(1000, 40));
        
        //intitializing the table
        lablearray = new JLabel [9][3];
        SetHeader();
    	LabelInitializer();           

        //setting the text area
    	textarea = new JTextArea("", 10, 65);
 		textarea.setLineWrap(true);
 	    textarea.setEditable(false);
 		 
        Font font1 = new Font("Serif", Font.BOLD, 18);       //setting the font for the text area
 		textarea.setFont(font1);
 		textarea.append("--------------------------------------------------------------------Auction Server-----------------------------------------------------------------------\n ");
 		textarea.append(" ---------------------------------------------------------------Bids will record below!---------------------------------------------------------------\n");
 		
 		//setting the scroll for text area
 		sbrText = new JScrollPane(textarea);
 		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

 		//panel for the scrolling text area
        panel = new JPanel();
    	panel.setPreferredSize(new Dimension(1000, 248));
        panel.add(sbrText, BorderLayout.CENTER);
        
        //adding the panel to the layout
        add(panel, BorderLayout.SOUTH);
        add(gridPanel2, BorderLayout.NORTH);
    	add(gridPanel1, BorderLayout.CENTER);
    	
    }
    
    //this method will set the header of the table
    public void SetHeader() {
    	
    	
    	//symbol feild
    	JLabel label1 = new JLabel();
    	lablearray[0][0] = label1;
    	label1.setPreferredSize(new Dimension(50, 100));
    	label1.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
    	gridPanel2.add(label1);
    	label1.setText("Symbol");
    	label1.setForeground(Color.white);
    	label1.setHorizontalAlignment(SwingConstants.CENTER);
        
        //company name feild
        JLabel label2 = new JLabel();
    	lablearray[0][1] = label2;
    	label2.setPreferredSize(new Dimension(50, 100));
    	label2.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
    	gridPanel2.add(label2);
    	label2.setText("Company Name");
    	label2.setForeground(Color.white);
    	label2.setHorizontalAlignment(SwingConstants.CENTER);

    	//current price feild
    	JLabel label3 = new JLabel();
    	lablearray[0][2] = label3;
    	label3.setPreferredSize(new Dimension(50, 100));
    	label3.setForeground(Color.white);
    	label3.setHorizontalAlignment(SwingConstants.CENTER);
    	label3.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
    	gridPanel2.add(label3);
    	label3.setText("Current Price");
    }
    
    //this method is to initialize the table 
    public void LabelInitializer() {          
    	for(int i = 1; i < 9; i++) {
    		for(int j = 0; j < 3; j++) {
    			JLabel label = new JLabel();
    			lablearray[i][j] = label; 
    		    label.setPreferredSize(new Dimension(200, 200));
    		    label.setFont( new Font("Serif", Font.PLAIN, 14) );
		        label.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
		        gridPanel1.add(label);
		        
    		}
    	}
    }
    
    //this will update the table with initial values
    public static void IntialTableUpdater(String [][] array) {
    	
    	for(int i = 1; i <9; i++ ) {
    		for(int j = 0; j < 3; j++) {
    		
    			lablearray[i][j].setText(array[i-1][j]);
    			
    		}
    	}
    }
    
    //this will update the text area when successfull bids happens
    public static void BidTracker(String [] array2) {
    	df = new DecimalFormat("0.00");
   
    	float price = Float.parseFloat(array2[2]);
    	java.time.LocalTime.now();  
    	textarea.append(java.time.LocalTime.now()+"  "+array2[0]+" placed highest bid successfully on "+array2[1]+" ! Now Current highest bid of "+ array2[1]+" is " +df.format(price)+"$.\n");
    	
    	
    }
    
    //this method will update the price in the table
    public static void PriceUpdater() {
    	df = new DecimalFormat("0.00");
    	
    	
    		lablearray[1][2].setText(df.format(ReadCsv.returnPrice("FB"))+"$");
    	
    		lablearray[2][2].setText(df.format(ReadCsv.returnPrice("VRTU"))+"$");
    	
    		lablearray[3][2].setText(df.format(ReadCsv.returnPrice("MSFT"))+"$");
    	
    		lablearray[4][2].setText(df.format(ReadCsv.returnPrice("GOOGL"))+"$");
    	
    		lablearray[5][2].setText(df.format(ReadCsv.returnPrice("YHOO"))+"$");
    	
    		lablearray[6][2].setText(df.format(ReadCsv.returnPrice("XLNX"))+"$");
    	
    		lablearray[7][2].setText(df.format(ReadCsv.returnPrice("TSLA"))+"$");
    	
    		lablearray[8][2].setText(df.format(ReadCsv.returnPrice("TXN"))+"$");
    		
    	
    }
    
public static void runGui(String [][] stockArray) { 
		
		JFrame frame = new JFrame("Auction Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ServerGui();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        //calling the method to update the table initially
        IntialTableUpdater(stockArray);
        
        
	}
}
