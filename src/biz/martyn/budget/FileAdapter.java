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
    
    File transactionsFile = new File("data/transactions.ser");
    File fundsFile = new File("data/funds.ser");
	
	public ArrayList<Transaction> loadTransactions() {
		
		return loadFile(transactionsFile);
	}
	
	public boolean writeTransactions(ArrayList<Transaction> transactions) {
		
		return writeFile(transactionsFile, transactions);
	}
	
	public ArrayList<Fund> loadFunds() {
		
		return loadFile(fundsFile);
	}
	
	public boolean writeFunds(ArrayList<Fund> funds) {
		
		return writeFile(fundsFile, funds);
	}
	
	@SuppressWarnings("unchecked")
	private <T> ArrayList<T> loadFile(File file) {
		
		ArrayList<T> obj = new ArrayList<>();
		
        ObjectInputStream in = null;
        
		try {
	    	
	    	// create a new file 
	    	if(!file.exists()) { 
	    		
//	    		// fill with some test data 
////	    		ArrayList<Transaction> transactions = new ArrayList<>();
//	    		transactions.add(new Transaction("Internet", "2016-09-20", -28));
//	    		transactions.add(new Transaction("Groceries", "2016-09-20", -26));
	    		
	    		writeFile(file, obj);
	    	}

	        FileInputStream fis = new FileInputStream(file);
	        in = new ObjectInputStream(fis);
	        obj = (ArrayList<T>) in.readObject();
	        
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
		
		return obj;
	}
	
	private <T> boolean writeFile(File file, ArrayList<T> obj) {
		
		ObjectOutputStream out = null;
		boolean result = false;
		try {
	    	
	    	FileOutputStream fos = new FileOutputStream(file);
	    	out = new ObjectOutputStream(fos);
	        out.writeObject(obj);
	        
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
