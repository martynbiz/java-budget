package biz.martyn.budget.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transaction;
import biz.martyn.budget.models.Transactions;
import biz.martyn.budget.table.TransactionTableModel;
import biz.martyn.budget.ui.components.NewTransactionDialog;

public class TransactionsPanel extends JPanel {
	
	public TransactionsPanel(final Transactions transactions, Funds funds, final ResourceBundle bundle) {
		
		setLayout(new BorderLayout());
		
		// transactions table
		Object [] columnIdentifiers = { 
			bundle.getString("th_id"), 
			bundle.getString("th_description"), 
			bundle.getString("th_date"), 
			bundle.getString("th_category"), 
			bundle.getString("th_amount") 
		};
		final JTable table = new JTable(new TransactionTableModel(transactions, columnIdentifiers));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		
		final NewTransactionDialog newTransactionDialog = new NewTransactionDialog(transactions, bundle);
		newTransactionDialog.setLocationRelativeTo(this);
		
		JButton newTransactionBtn = new JButton(bundle.getString("btn_new_transaction"));
	    newTransactionBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (transactions.getFund() == null) {
		    		JOptionPane.showMessageDialog(null, bundle.getString("alert_select_fund"));
		    	}
				
				newTransactionDialog.setVisible(true);
			}
	    });
	    
	    JButton deleteTransactionBtn = new JButton(bundle.getString("btn_delete_transaction"));
		deleteTransactionBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int[] selectedRows = table.getSelectedRows();
				
				int n = JOptionPane.showConfirmDialog(
					    null,
					    "Delete transaction",
					    "Are you sure you want to delete?",
					    JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					for(int i=0;i<selectedRows.length;i++){
						String id = (String) model.getValueAt(selectedRows[i], 0);
						transactions.deleteById(id);
					}
				}
			}
	    });
//		deleteTransactionBtn.setEnabled(false);
		
		buttonPanel.add(newTransactionBtn);
		buttonPanel.add(deleteTransactionBtn);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
