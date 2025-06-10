//c'est la vue du formulaire 

package appBudget;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

;

		
		public class WindowForm {
			
			
		    public static JPanel createFormPanel() {
		        JPanel panel_g = new JPanel(new GridBagLayout());
		        GridBagConstraints gbc = new GridBagConstraints();
		        gbc.insets = new Insets(15, 15, 15, 15);
		        gbc.fill = GridBagConstraints.HORIZONTAL;

		        String[] labels = {"Libellé :", "Montant :", "Date :"};
		        JTextField[] fields = new JTextField[labels.length];

		        for (int i = 0; i < labels.length; i++) {
		            gbc.gridx = 0;
		            gbc.gridy = i;
		            panel_g.add(new JLabel(labels[i]), gbc);
		            gbc.gridx = 1;
		            
		            if (i<2) {
		            	fields[i] = new JTextField(15);
		            	panel_g.add(fields[i], gbc);
		            }
		            	else {
		            		 DatePicker datePicker = new DatePicker();
		            		 panel_g.add(datePicker, gbc);
		            }
		            
		         
		        }
		       

		        gbc.gridx = 0;
		        gbc.gridy = labels.length;
		        gbc.gridwidth = 1;
		        gbc.weightx = 0.7;
		        JButton credit = new JButton("Crédit");
		        credit.addActionListener(e -> JOptionPane.showMessageDialog(panel_g, "Montant saisi"));
		        panel_g.add(credit, gbc);
		        
		        gbc.gridx = 1;
		        gbc.gridy = labels.length;
		        gbc.gridwidth = 1;
		        gbc.weightx = 0.3;
		        JButton debit = new JButton("Débit");
		        debit.addActionListener(e -> JOptionPane.showMessageDialog(panel_g, "Montant saisi"));
		        panel_g.add(debit, gbc);

		        return panel_g;
		    }
		
	}