package biz.martyn.budget.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import biz.martyn.budget.models.Fund;
import biz.martyn.budget.models.Transaction;

/**
 * 
 * @author martyn
 *
 */
public class FileAdapter implements StorageAdapter {
    
//    File transactionsFile = new File("data/transactions.ser");
    File fundsFile = new File("data/funds.ser");
	
	public List<Transaction> loadTransactions(Fund fund) {
		return loadFile(getTransactionsFile(fund));
	}

	public boolean writeTransactions(List<Transaction> transactions, Fund fund) {
		return writeFile(getTransactionsFile(fund), transactions);
	}
	
	public List<Fund> loadFunds() {
		return loadFile(getFundsFile());
	}
	
	public boolean writeFunds(List<Fund> funds) {
		return writeFile(getFundsFile(), funds);
	}
	
	private File getFundsFile() {
		File file = new File("data/funds.ser");
		return file;
	}
	
	private File getTransactionsFile(Fund fund) {
		File file = new File("data/transactions/" + fund.name + ".ser");
		return file;
	}
	
	@SuppressWarnings("unchecked")
	private <T> List<T> loadFile(File file) {
		
		List<T> obj = new ArrayList<>();
		
        ObjectInputStream in = null;
        
		try {
	    	
			// create a new file, dir
	    	if(!file.exists()) { 
	    		file.getParentFile().mkdirs();
	    		writeFile(file, obj);
	    	}

	        FileInputStream fis = new FileInputStream(file);
	        in = new ObjectInputStream(fis);
	        obj = (List<T>) in.readObject();
	        
	    } 
	    catch(IOException e){
	    	System.out.println("IOException read");
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
	
	private <T> boolean writeFile(File file, List<T> obj) {
		
		ObjectOutputStream out = null;
		boolean result = false;
		try {
	    	
	    	FileOutputStream fos = new FileOutputStream(file);
	    	out = new ObjectOutputStream(fos);
	        out.writeObject(obj);
	        
	        result = true;
	        
	    } 
	    catch(IOException e){
	    	System.out.println("IOException write");
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
