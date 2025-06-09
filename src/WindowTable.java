import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import java.util.TreeSet;

public class WindowTable extends JPanel implements PropertyChangeListener{	
	private static final long serialVersionUID = 1L;

	public JTable table;
	
	public JComboBox<String> comboxDates;
	public JComboBox<String> comboxLibelle;
	
	private JLabel labelTotalCredit;
	private JLabel labelTotalDebit;
	private JLabel labelBalance;
	
	private BudgetController controller;

	//private static TableRowSorter<BudgetModel> sorter;
	
	public WindowTable(BudgetModel model, BudgetController controller){
		
		this.controller = controller;
		
		setBorder(new EmptyBorder(50, 25, 50, 0));
		setLayout(new BorderLayout());
		
		table = new JTable(model){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
		        java.awt.Component c = super.prepareRenderer(renderer, row, column);
		        if (!isRowSelected(row)) {
		            c.setBackground(row % 2 == 0 ? controller.colorColumn_01: controller.colorColumn_02); // Gris clair
		        } else {
		            c.setBackground(getSelectionBackground());
		        }
		        return c;
		    }
		};

        table.setFont(this.controller.tahomaFont12);
	    
	    // Centrer le texte dans les cellules
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Add custom renderer and editor for the "Action" column
     	table.getColumn("Action").setCellRenderer(new ButtonRenderer(this.controller));
     	table.getColumn("Action").setCellEditor(new ButtonEditor(this, this.controller, new JCheckBox()));

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
		String s1[] = { "Select date"};
		comboxDates = new JComboBox<>(s1);
		comboxDates.setFont(this.controller.tahomaFont12);
		comboxDates.setForeground(this.controller.colorSelect);
		comboxDates.setSelectedIndex(0);
		
		JLabel labelComboBoxDate = new JLabel("Select date");
		labelComboBoxDate.setFont(this.controller.tahomaFont12);
		
		comboxDates.addActionListener(_ -> { onFilterChanged(); });
		
		JPanel panelComboBoxDate = new JPanel();
		panelComboBoxDate.setBorder(new EmptyBorder(0, 0, 0, 50));
		panelComboBoxDate.add(labelComboBoxDate);
		panelComboBoxDate.add(comboxDates);
		
		String s2[] = {"Select libellé"};
		comboxLibelle = new JComboBox<>(s2);
		comboxLibelle.setFont(this.controller.tahomaFont12);
		comboxLibelle.setForeground(this.controller.colorSelect);
		comboxLibelle.setSelectedIndex(0);
		
		JLabel labelComboBoxLibelle = new JLabel("Select libellé");
		labelComboBoxLibelle.setFont(this.controller.tahomaFont12);

		JPanel panelComboBoxLibelle = new JPanel();
		panelComboBoxLibelle.add(labelComboBoxLibelle);
		panelComboBoxLibelle.add(comboxLibelle);
		
		comboxLibelle.addActionListener(_ -> { onFilterChanged(); });	
		
		JPanel panelComboBox = new JPanel();
		//panelComboBox.setLayout(new BoxLayout(panelComboBox, BoxLayout.Y_AXIS));
		panelComboBox.setBorder(new EmptyBorder(0, 0, 25, 0));
		panelComboBox.add(panelComboBoxDate);
		panelComboBox.add(panelComboBoxLibelle);

		add(panelComboBox, BorderLayout.NORTH);
		     
        //Ajout des infos sur le budget
        JPanel panelInfo = new JPanel();
        panelInfo.setBorder(new EmptyBorder(25, 0, 0, 0));
        
        panelInfo.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10,10,10,10);
	    
	    gbc.gridy = 0;
	    
	    JLabel labelTotal = new JLabel("Total");
        labelTotal.setFont(this.controller.tahomaFont12);
        labelTotal.setForeground(this.controller.colorSelect);
        gbc.gridx = 0;
        panelInfo.add(labelTotal, gbc);
        
        labelTotalCredit = new JLabel("CHF " + "0.0");
        labelTotalCredit.setFont(this.controller.tahomaFont12);
        labelTotalCredit.setForeground(this.controller.colorSelect);
        gbc.gridx = 2;
        panelInfo.add(labelTotalCredit, gbc);
        
        labelTotalDebit = new JLabel("CHF " + "0.0");
        labelTotalDebit.setFont(this.controller.tahomaFont12);
        labelTotalDebit.setForeground(this.controller.colorSelect);
        gbc.gridx = 3;
        panelInfo.add(labelTotalDebit, gbc);

        gbc.gridy = 1;
        
        JLabel labelSolde = new JLabel("Solde:");
        labelSolde.setFont(this.controller.tahomaFont12);
        labelSolde.setForeground(this.controller.colorSelect);
        gbc.gridx = 0;
        panelInfo.add(labelSolde, gbc);
        
        labelBalance = new JLabel("CHF " + "0.0");
        labelBalance.setFont(this.controller.tahomaFont12);
        labelBalance.setForeground(this.controller.colorSelect);
        gbc.gridx = 2;
        panelInfo.add(labelBalance, gbc);
    
		add(panelInfo, BorderLayout.SOUTH);
		
		model.addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
		labelTotalCredit.setText("CHF " + String.valueOf(this.controller.getAccountCredit()));
		labelTotalDebit.setText("CHF " + String.valueOf(this.controller.getAccountDebit()));
		labelBalance.setText("CHF " + String.valueOf(this.controller.getAccountSolde()));	
		
		// Vérifie si la date est déjà dans le combo
		String date = this.controller.getDate();
		comboxDates = checkCombobox(comboxDates, date);

		String libelle = this.controller.getLibelle();
		comboxLibelle = checkCombobox(comboxLibelle, libelle);
	}

	// Vérifie si la valeur est déjà dans le combobox
	private JComboBox<String> checkCombobox(JComboBox<String> combobox, String data){
	 
     	boolean existe = false;

     	for (int i = 0; i < combobox.getItemCount(); i++) {
     	    if (combobox.getItemAt(i).equals(data) || data == "") {
     	    	existe = true;
     	        break;
     	    }
     	}

     	if (!existe) {
     		combobox.addItem(data);
     	}
     	return combobox;
	}
	
	private void onFilterChanged() {
        String selectedDate = (String) comboxDates.getSelectedItem();
        String selectedLabel = (String) comboxLibelle.getSelectedItem();

        controller.applyFilters(selectedDate, selectedLabel);
        updateComboBoxes(selectedDate, selectedLabel);
    }

    private void updateComboBoxes(String selectedDate, String selectedLabel) {
        // Mise à jour du combo Libellé selon la Date sélectionnée
    	Set<String> labels = new TreeSet<>();
        labels = this.controller.getFilteredLibelles(selectedLabel);
        comboxLibelle.removeAllItems();
        comboxLibelle.addItem("Select libellé");
        for (String label : labels) comboxLibelle.addItem(label);
        comboxLibelle.setSelectedItem(selectedLabel);

        // Mise à jour du combo Date selon le Libellé sélectionné
        Set<String> dates = new TreeSet<>();
        dates = this.controller.getFilteredDates(selectedDate);
        comboxDates.removeAllItems();
        comboxDates.addItem("Select date");
        for (String date : dates) comboxDates.addItem(date);
        comboxDates.setSelectedItem(selectedDate);
    }
}

//Pour ajouter un bouton dans uns cellule du tableau
class ButtonRenderer extends JButton implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public ButtonRenderer(BudgetController controller) {

	    setText("Remove");
	    setFont(controller.tahomaFont12);
	    setForeground(controller.colorSelect);
	 }
	
	 @Override
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	     return this;
	 }
}

//Custom editor for the button
class ButtonEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 1L;
	private JButton button;

	 private int row;

	 public ButtonEditor(WindowTable parent, BudgetController controller, JCheckBox checkBox) {
	     super(checkBox);

	     button = new JButton("Remove");
	     button.setFont(controller.tahomaFont12);
	     button.setForeground(controller.colorSelect);
	     
    
	     button.addActionListener(new ActionListener() {
	    	@Override
           	public void actionPerformed(ActionEvent e) {
        	  
               int result = JOptionPane.showConfirmDialog(
            	   parent,
                   "La ligne va être supprimée. Voulez-vous continuer ?",
                   "Confirmation",
                   JOptionPane.YES_NO_CANCEL_OPTION,
                   JOptionPane.QUESTION_MESSAGE
               );
               
               if(result == 0) {
            	   // Remove line in table
            	   controller.removeRow(row);
            	   
            	   if(parent.comboxDates.getItemCount() > row + 1) {
            		   parent.comboxDates.removeItemAt(row + 1);
            	   }
            	   
            	   if(parent.comboxLibelle.getItemCount() > row + 1) {
            		   parent.comboxLibelle.removeItemAt(row + 1);
            	   }
               }   
	    	}
       });
	 };
	
	 @Override
	 public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	     this.row = row; // Store the row index
	     
	     return button;
	 }
	
	 @Override
	 public Object getCellEditorValue() {
	     return "Delete";
	 }
}