package biz.martyn.budget.ui;

import java.awt.BorderLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import biz.martyn.budget.ui.components.TransactionsFilterToolbar;
import biz.martyn.budget.ui.components.TransactionsToolbar;
import biz.martyn.budget.models.Fund;
import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transactions;

public class BudgetFrame extends JFrame {
	
	private final ResourceBundle bundle = ResourceBundle.getBundle("biz.martyn.budget.i18n.Text", Locale.ENGLISH);
	private final JTabbedPane tabs = new JTabbedPane();
	
	public BudgetFrame(Funds funds, Transactions transactions) {
		super("Budget"); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		Fund defaultFund = funds.getDefaultFund();
		if (defaultFund != null) {
			transactions.setFund(defaultFund);
		}
		
		TransactionsToolbar toolbar = new TransactionsToolbar(transactions, funds, bundle);
		TransactionsFilterToolbar filterPanel = new TransactionsFilterToolbar(transactions, bundle);
		
		tabs.addTab("Overview", new OverviewPanel(transactions, funds, bundle));
		tabs.addTab("Graph", new GraphPanel(transactions, funds, bundle));
		
		getContentPane().add(toolbar, BorderLayout.NORTH);
		getContentPane().add(tabs, BorderLayout.CENTER);
		getContentPane().add(filterPanel, BorderLayout.SOUTH);
		
		setSize(800, 500);
		setVisible(true);
	}
}
