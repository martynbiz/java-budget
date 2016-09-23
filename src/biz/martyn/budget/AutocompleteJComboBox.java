package biz.martyn.budget;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class AutocompleteJComboBox extends JComboBox<Object> {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Searchable < String, String > searchable;
	
    public AutocompleteJComboBox(Searchable < String, String > s) {
        super();
        this.searchable = s;
        setEditable(true);
        Component c = getEditor().getEditorComponent();
        if (c instanceof JTextComponent) {
            final JTextComponent tc = (JTextComponent) c;
            tc.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent arg0) {}
                @Override
                public void insertUpdate(DocumentEvent arg0) {
                    update();
                }
                @Override
                public void removeUpdate(DocumentEvent arg0) {
                    update();
                }
                public void update() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                        	ArrayList<String> founds = new ArrayList<String> (searchable.search(tc.getText()));
                        	HashSet<String> foundSet = new HashSet<String> ();
                            for (String s: founds) {
                                foundSet.add(s.toLowerCase());
                            }
                            Collections.sort(founds);
                            setEditable(false);
                            removeAllItems();
                            if (!foundSet.contains(tc.getText().toLowerCase())) {
                                addItem(tc.getText());
                            }
                            for (String s: founds) {
                                addItem(s);
                            }
                            setEditable(true);
                            setPopupVisible(true);
                            tc.requestFocus();
//                            tc.setCaretPosition(tc.getText().length()); 
                        }
                    });
                }
//				@Override
//				public void changedUpdate(DocumentEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//				@Override
//				public void insertUpdate(DocumentEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//				@Override
//				public void removeUpdate(DocumentEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
            });
            tc.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent arg0) {
                    if (tc.getText().length() > 0) {
                        setPopupVisible(true);
                    }
                }
                @Override
                public void focusLost(FocusEvent arg0) {}
            });
        } else {
            throw new IllegalStateException("Editing component is not a JTextComponent!");
        }
    }
}
