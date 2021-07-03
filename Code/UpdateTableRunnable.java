

import java.net.Socket;

public class UpdateTableRunnable  implements Runnable{

    //this thread will run from the start to end of the server
    //this will update the table in every 500ms
	@Override
	public void run() {
		
		try {
		  while(true) {
			Thread.sleep(500);
			ServerGui.PriceUpdater();  //price updating method
		  }
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}
    
}
