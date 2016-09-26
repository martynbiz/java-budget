package biz.martyn.budget.ui;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transactions;
import biz.martyn.budget.table.TransactionTableModel;

public class OverviewPanel extends JPanel {
	
	public OverviewPanel(Transactions transactions, Funds funds, ResourceBundle bundle) {
		
		setLayout(new BorderLayout());
		
		// transactions table
		Object [] columnIdentifiers = { 
			bundle.getString("th_id"), 
			bundle.getString("th_description"), 
			bundle.getString("th_date"), 
			bundle.getString("th_category"), 
			bundle.getString("th_amount") 
		};
		JTable table = new JTable(new TransactionTableModel(transactions, columnIdentifiers));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}
}
