import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WindowForms extends JPanel implements PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	
	private DatePicker dp;
	private String[] labels = {"Libellé", "Montant", "Date"};
	private JTextField[] fields = new JTextField[labels.length];
	
	public WindowForms(BudgetModel model, BudgetController controller){
		
		String[] values = {"", ""};
		setFont(model.tahomaFont12);
		setBorder(new EmptyBorder(0, 0, 0, 25));
		setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10,10,10,10);
	    gbc.anchor = GridBagConstraints.WEST;
		
		for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;

            JLabel label = new JLabel();
            label.setText(labels[i] + ":");
            label.setFont(model.tahomaFont12);
            add(label, gbc);
            
            gbc.gridx = 1;
            if(labels[i] == "Date") {
            	dp = new DatePicker(model, controller);
            	add(dp, gbc);
            }else if(labels[i] == "Montant"){
            	JPanel montantPanel = new JPanel();
            	fields[i] = new JTextField(values[i], 5);
            	JLabel labelMontant = new JLabel("CHF");
            	montantPanel.add(fields[i]);
            	montantPanel.add(labelMontant);
            	add(montantPanel, gbc);
            	PlainDocument doc = (PlainDocument) fields[i].getDocument();
                doc.setDocumentFilter(new IntFilter());    
            }else {
            	fields[i] = new JTextField(values[i], 15);
            	fields[i].setFont(model.tahomaFont12);
            	fields[i].setForeground(model.colorSelect);
            	add(fields[i], gbc);
            }
        }
		
		gbc.gridx = 0;
        gbc.gridy = labels.length;

        JButton btnCredit = new JButton("Crédit");
        JButton btnDebit = new JButton("Débit");
        
        //Font menu add
        btnCredit.setFont(model.tahomaFont12);
        btnDebit.setFont(model.tahomaFont12);

        // Inscrire les événements+
        btnCredit.addActionListener(_ -> {
        	if(checkInput()) {
        		
        	}else {}
        });

        btnDebit.addActionListener(_ -> {
			if(checkInput()) {
			        		
			}else {}
        });

        model.addPropertyChangeListener(this);
 
        add(btnCredit, gbc);
        
        gbc.gridx = 1;
        add(btnDebit, gbc);
	}
	
	private Boolean checkInput() {
		Boolean check = false;
		for (int i = 0; i < labels.length; i++) { 
			String value = fields[i].getText();
			if (!value.isEmpty()) {
				check = false;
			}else {
				check =  true;
			}
		}
		return check;
	}
	
	// public List<String> getValue(Boolean creditCLick){
	// List<String> stringArray = new ArrayList<String>();
	//	 String date= "", libelle = "", debit = "", credit = "", boolValue = "";
	//	 for (int i = 0; i < labels.length; i++) {
			 //		String key = labels[i];
	//	if(key == "Date") {
				//			String day = String.valueOf(dp.dayComboBox.getSelectedItem());
	//	String month = String.valueOf(dp.monthComboBox.getSelectedItem());
	//	String year = String.valueOf(dp.yearComboBox.getSelectedItem());
	//		date = day + " " + month + " " + year;
				
				//	}else {
	//String value = fields[i].getText();
	//	if (!value.isEmpty()) {
	//		if(key == "Montant") {
	//				if(creditCLick) {
	//				credit = value;
	//				} else {debit = value;}
	//			} else {libelle = value;}
	//			fields[i].setText("");
	//			boolValue = "true";
	//	        } else {
	//	        	boolValue = "false";           
	//		        }	
	//		}
	//	}
	//		stringArray.add(date);
		//	stringArray.add(libelle);
	//		stringArray.add(credit);
	//	stringArray.add(debit);
		//		stringArray.add(boolValue);
			
	//      return stringArray;
	//  }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub	
	}
}

//Classe pour filtrer que les entiers positifs. 
class IntFilter extends DocumentFilter {
	   @Override
	   public void insertString(FilterBypass fb, int offset, String string,
	         AttributeSet attr) throws BadLocationException {

	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.insert(offset, string);

	      if (test(sb.toString())) {
	         super.insertString(fb, offset, string, attr);
	      } else {
	         // warn the user and don't allow the insert
	      }
	   }

	   private boolean test(String text) {
	      try {
	         Integer.parseInt(text);
	         return true;
	      } catch (NumberFormatException e) {
	         return false;
	      }
	   }

	   @Override
	   public void replace(FilterBypass fb, int offset, int length, String text,
	         AttributeSet attrs) throws BadLocationException {

	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.replace(offset, offset + length, text);

	      if (test(sb.toString())) {
	         super.replace(fb, offset, length, text, attrs);
	      } else {
	         // warn the user and don't allow the insert
	      }

	   }
	}