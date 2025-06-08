import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.JOptionPane;

public class WindowForms extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private DatePicker dp;
	private String[] labels = {"Libellé", "Montant", "Date"};
	private JTextField[] fields = new JTextField[labels.length];
	
	public WindowForms(BudgetController controller){
	
		String[] values = {"", ""};
		
		setFont(controller.tahomaFont12);
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
            label.setFont(controller.tahomaFont12);
            add(label, gbc);
            
            gbc.gridx = 1;
            if(labels[i] == "Date") {
            	dp = new DatePicker(controller);
            	add(dp, gbc);
            }else if(labels[i] == "Montant"){
            	JPanel montantPanel = new JPanel();
            	fields[i] = new JTextField(values[i], 5);
            	fields[i].setFont(controller.tahomaFont12);
            	fields[i].setForeground(controller.colorSelect);
            	JLabel labelMontant = new JLabel("CHF");
            	montantPanel.add(fields[i]);
            	montantPanel.add(labelMontant);
            	add(montantPanel, gbc);
            	PlainDocument doc = (PlainDocument) fields[i].getDocument();
                doc.setDocumentFilter(new IntFilter());    
            }else {
            	fields[i] = new JTextField(values[i], 15);
            	fields[i].setFont(controller.tahomaFont12);
            	fields[i].setForeground(controller.colorSelect);
            	add(fields[i], gbc);
            }
        }
		
		gbc.gridx = 0;
        gbc.gridy = labels.length;

        JButton btnCredit = new JButton("Crédit");
        JButton btnDebit = new JButton("Débit");
        
        //Font menu add
        btnCredit.setFont(controller.tahomaFont12);
        btnDebit.setFont(controller.tahomaFont12);

        // Inscrire les événements+
        btnCredit.addActionListener(_ -> {
        	if(checkInput(controller)) {
        		controller.addEntry((String) fields[0].getText(), Float.parseFloat(fields[1].getText()), 0.0f);
        		clearFields();
        	}else {
        		//Rajouter message d'erreur
        		JOptionPane.showMessageDialog(this,"Merci de renseigner toute les entrées", "Error entrée", JOptionPane.ERROR_MESSAGE);
        		clearFields();
        	}
        });

        btnDebit.addActionListener(_ -> {
			if(checkInput(controller)) {
			     controller.addEntry((String) fields[0].getText(), 0.0f, Float.parseFloat(fields[1].getText()));
			     clearFields();
			}else {
				//Rajouter message d'erreur
				JOptionPane.showMessageDialog(this,"Merci de renseigner toute les entrées", "Error entrée", JOptionPane.ERROR_MESSAGE);
				clearFields();		
			}
        });

        add(btnCredit, gbc);
        
        gbc.gridx = 1;
        add(btnDebit, gbc);
	}
	
	private Boolean checkInput(BudgetController controller) {
		Boolean check = false;
		String day = controller.getDay();
		String month = controller.getMonth();
		String year = controller.getYear();
		
		for (int i = 0; i < fields.length - 1; i++) { 
			String value = fields[i].getText();
			if (!value.isEmpty() && day != null && month != null  && year != null) {
				check = true;
			}else {
				check =  false;
			}
		}

		return check;
	}
	
	private void clearFields() {
		for (int i = 0; i < fields.length-1; i++) {fields[i].setText("");}
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

	      if (check(sb.toString())) {
	         super.insertString(fb, offset, string, attr);
	      } else {
	         // warn the user and don't allow the insert
	      }
	   }

	   private boolean check(String text) {
	      try {
	    	  if(text == "") {
	    		  return true;
	    	  }else {
	    		  Float.parseFloat(text);
	    		  return true;
	    	  }
	         
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

	      if (check(sb.toString())) {
	         super.replace(fb, offset, length, text, attrs);
	      } else {
	         // warn the user and don't allow the insert
	      }

	   }
	}