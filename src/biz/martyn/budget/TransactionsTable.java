package biz.martyn.budget;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * Table of transactions 
 */
public class TransactionsTable extends JTable implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @var Transactions
	 */
	Transactions transactions;

	public TransactionsTable(Transactions transactions) {
		
		this.transactions = transactions;
		
		// changes to transactions data will automatically update table 
		transactions.addObserver(this);
		
		// set the table model 
		Object [] columnNames = { "Description", "Date", "Amount" };
		setModel( new DefaultTableModel(columnNames, 0) );
		
		update();

//		this.getModel().addTableModelListener(new TableModelListener() {
//
//			@Override		
//		    public void tableChanged(TableModelEvent e) {
//		       System.out.println(e);
//		    }
//		});
	}

	@Override
	public void update() {
		
		DefaultTableModel tableModel = (DefaultTableModel) this.getModel();
		
		// rebuild the table rows 
		tableModel.setRowCount(0);
		for (Transaction t : transactions.get()) {
			tableModel.addRow( t.toStringArray() );
		}
	}
}
