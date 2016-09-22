package biz.martyn.budget;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

// TODO public?
public class Transaction {
	
	// TODO these should be accessed by accessors
	// TODO validation?
	String category; 
	String desc; 
	String date; 
	int amount; 
	String [] errors = {};
	
	// TODO integer ought to be a float
	public Transaction() { //(String desc, String date, int amount, String category) {
//		this.desc = desc;
//		this.date = date;
//		this.amount = amount;
//		this.category = category;
	}
	
//	public Transaction(String description, String date, int amount) {
//		this(description, date, amount, "");
//	}
	
	public boolean save() {
		
		// TODO validation
		
		
		// TODO fetch transactions from transactions file
		ArrayList<Transaction> transactions = new ArrayList();
		transactions.add(0, this);
		
		System.out.println(transactions.toString());
		
		// TODO write transactions to storage
		
		
		
		return false;
	}
	
	public String [] getErrors() {
		return errors;
	}
	
	
}
