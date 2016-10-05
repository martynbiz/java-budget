package biz.martyn.budget.ui;

import java.awt.BorderLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import biz.martyn.budget.ui.components.FundsTabPanel;
import biz.martyn.budget.ui.components.TransactionsTabPanel;
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
		
		tabs.addTab(bundle.getString("tab_transactions"), new TransactionsTabPanel(transactions, funds, bundle));
		tabs.addTab(bundle.getString("tab_funds"), new FundsTabPanel(funds, bundle));
		tabs.addTab(bundle.getString("tab_overview"), new OverviewTabPanel(transactions, funds, bundle));
		
		getContentPane().add(new TopPanel(funds, transactions, bundle), BorderLayout.NORTH);
		getContentPane().add(tabs, BorderLayout.CENTER);
		getContentPane().add(new BottomPanel(transactions, bundle), BorderLayout.SOUTH);
		
		setSize(800, 500);
		setVisible(true);
	}
}
