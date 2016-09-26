package biz.martyn.budget.table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import biz.martyn.budget.Observer;
import biz.martyn.budget.models.Transaction;
import biz.martyn.budget.models.Transactions;

public class TransactionTableModel extends DefaultTableModel implements Observer {
	
	Transactions transactions;
	
	public TransactionTableModel(final Transactions transactions, Object[] columnIdentifiers) {
		
		this.transactions = transactions;
		
		// changes to transactions data will automatically update table 
		transactions.addObserver(this);
		
		setColumnIdentifiers(columnIdentifiers);
		
		addTableModelListener(new TableModelListener() {

			@Override		
		    public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					
					String id = (String) getValueAt(e.getFirstRow(), 0);
					Transaction transaction = transactions.getById(id);
					String newValue = (String) getValueAt(e.getFirstRow(), e.getColumn());

					// map column to property, might need a better way to do this
					switch (e.getColumn()) {
					case 1: // desc
						transaction.desc = newValue;
						break;
					case 2: // date 
						transaction.date = newValue;
						break;
					case 3: // amount 
						transaction.category = newValue;
						break;
					case 4: // amount 
						transaction.amount = Integer.parseInt(newValue);
						break;
					}
					
					// it's difficult to do this internally so have to call these methods here 
					transactions.writeToFile();
					transactions.notifyObservers();
			    }
		    }
		});
		
		update();
	}

	@Override
	public void update() {
		
		// rebuild the table rows 
		setRowCount(0);
		for (String [] row : transactions.toStringArray()) {
			addRow(row);
		}
	}
}
