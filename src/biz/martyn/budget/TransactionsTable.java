package biz.martyn.budget;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * Table of transactions 
 */
public class TransactionsTable extends JTable implements Observer {
	
	private static final long serialVersionUID = 1L;
	private Object [] columnNames = { "Description", "Date", "Amount" };
	
	DefaultTableModel tableModel;
	
	/**
	 * @var Transactions
	 */
	Transactions transactions;

	public TransactionsTable(Transactions transactions) {
		
		this.transactions = transactions;
		transactions.addObserver(this);
		
		tableModel = new DefaultTableModel(columnNames, 0);
		setModel(tableModel);
		update();

//		this.getModel().addTableModelListener(new TableModelListener() {
//
//			@Override		
//		    public void tableChanged(TableModelEvent e) {
//		       System.out.println(e);
//		    }
//		});
	}
	
//	public void render() {
//		
//	}

	@Override
	public void update() {
		tableModel.setRowCount(0);
		for (Transaction t : transactions.get()) {
			tableModel.addRow( t.toStringArray() );
		}
	}
}
