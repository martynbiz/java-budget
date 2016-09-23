package biz.martyn.budget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * 
 * @author martyn
 *
 */
public class FileAdapter implements StorageAdapter {
    
    File f = new File("data/transactions.ser");
	
	@SuppressWarnings("unchecked")
	public ArrayList<Transaction> loadTransactions() {
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
        ObjectInputStream in = null;
        
		try {
	    	
	    	// create a new file 
	    	if(!f.exists()) { 
	    		
//	    		// fill with some test data 
////	    		ArrayList<Transaction> transactions = new ArrayList<>();
//	    		transactions.add(new Transaction("Internet", "2016-09-20", -28));
//	    		transactions.add(new Transaction("Groceries", "2016-09-20", -26));
	    		
	    		writeTransactions(transactions);
	    	}

	        FileInputStream fis = new FileInputStream(f);
	        in = new ObjectInputStream(fis);
	        transactions = (ArrayList<Transaction>) in.readObject();
	        System.out.println(transactions);
	        
	    } 
	    catch(IOException e){
	    	System.out.println("IOException");
	    } 
	    catch(ClassNotFoundException e) {
	    	System.out.println("ClassNotFoundException");
	    }
		finally {
			if (in != null) {
				try {
					in.close();
				} catch(IOException e){
			    	System.out.println("IOException");
			    } 
			}
		}
		
		return transactions;
	}
	
	public boolean writeTransactions(ArrayList<Transaction> transactions) {
		
		ObjectOutputStream out = null;
		boolean result = false;
		try {
	    	
	    	FileOutputStream fos = new FileOutputStream(f);
	    	out = new ObjectOutputStream(fos);
	        out.writeObject(transactions);
	        
	        result = true;
	        
	    } 
	    catch(IOException e){
	    	System.out.println("IOException");
	    } 
		finally {
			if (out != null) {
				try {
					out.close();
				} catch(IOException e){
			    	System.out.println("IOException");
			    } 
			}
		}
		
		return result;
	}
}
