import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WindowTable extends JPanel implements PropertyChangeListener{	
	private static final long serialVersionUID = 1L;
	
	public JTable table;
	public JComboBox<String> comboxDates;
	
	private JLabel labelTotalCredit;
	private JLabel labelTotalDebit;
	private JLabel labelBalance;
	
	private BudgetController controller;

	private static TableRowSorter<BudgetModel> sorter;

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
		            c.setBackground(row % 2 == 0 ? new java.awt.Color(80, 80, 80): new java.awt.Color(60, 60, 60)); // Gris clair
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

		sorter = new TableRowSorter<>(model);
		
	    table.setRowSorter(sorter);
     	
        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
		String s1[] = { "Select date"};
		comboxDates = new JComboBox<>(s1);
		comboxDates.setFont(this.controller.tahomaFont12);
		comboxDates.setForeground(this.controller.colorSelect);
		comboxDates.setSelectedIndex(0);
		
		JLabel labelComboBox = new JLabel("Select date");
		labelComboBox.setFont(this.controller.tahomaFont12);

		
		JPanel panelComboBox = new JPanel();
		panelComboBox.setBorder(new EmptyBorder(0, 0, 25, 0));
		panelComboBox.add(labelComboBox);
		panelComboBox.add(comboxDates);
		add(panelComboBox, BorderLayout.NORTH);
		
		comboxDates.addActionListener(_ -> {
	        String selectedDate = (String) comboxDates.getSelectedItem();
	        if ("Select date".equals(selectedDate)) {
	            sorter.setRowFilter(null); // retire le filtre
	        } else {
	            sorter.setRowFilter(RowFilter.regexFilter("^" + selectedDate + "$", 0)); // colonne 2 = date
	        }
	    });
		
        
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
     	boolean existe = false;
     	String date = this.controller.getDate();

     	for (int i = 0; i < comboxDates.getItemCount(); i++) {
     	    if (comboxDates.getItemAt(i).equals(date) || date == "") {
     	        existe = true;
     	        break;
     	    }
     	}

     	if (!existe) {
     		comboxDates.addItem(date);
     	}
	}
}

//Custom renderer for the button
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
        	   //Ajouter un message pour demander si on est sur de vouloir supprimer la ligne.
               // Remove line in table
        	   controller.removeRow(row);
        	   if(parent.comboxDates.getItemCount() > 1) {
        		   parent.comboxDates.removeItemAt(row + 1);
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