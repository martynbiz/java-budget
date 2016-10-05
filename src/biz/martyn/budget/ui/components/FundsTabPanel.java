package biz.martyn.budget.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

import biz.martyn.budget.models.Funds;

public class FundsTabPanel extends JPanel {
	
	private JButton newFundButton;
	private NewFundDialog fundsDialog;
	
	public FundsTabPanel(Funds funds, ResourceBundle bundle) {
		fundsDialog = new NewFundDialog(funds);
		fundsDialog.setLocationRelativeTo(this);
		
	    newFundButton = new JButton(bundle.getString("btn_new_fund"));
	    newFundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fundsDialog.setVisible(true);
			}
	    });
	    add(newFundButton);
	}
}
