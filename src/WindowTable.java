import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WindowTable extends JPanel implements PropertyChangeListener{	
	private static final long serialVersionUID = 1L;
	
	public JComboBox<String> comboBox;
	private JTable table;
	private JLabel labelTotalCredit;
	private JLabel labelTotalDebit;
	private JLabel labelSolde;
	private BudgetModel model;

	public WindowTable(BudgetModel model, BudgetController controller){
		
		setBorder(new EmptyBorder(50, 25, 50, 0));
		setLayout(new BorderLayout());
		
		this.model = model;
		
		String s1[] = { "Select date"};
		comboBox = new JComboBox<>(s1);
		comboBox.setFont(this.model.tahomaFont12);
		comboBox.setForeground(this.model.colorSelect);
		comboBox.setSelectedIndex(0);
		//comboBox.addItemListener(parent);
		
		JLabel labelComboBox = new JLabel("Select date");
		labelComboBox.setFont(this.model.tahomaFont12);

		
		JPanel panelComboBox = new JPanel();
		panelComboBox.setBorder(new EmptyBorder(0, 0, 25, 0));
		panelComboBox.add(labelComboBox);
		panelComboBox.add(comboBox);
		add(panelComboBox, BorderLayout.NORTH);
		
		
		JTable table = new JTable(this.model);
		//table.setDefaultRenderer(Object.class, new AlternatingRowRenderer());
        table.setFont(this.model.tahomaFont12);
        
        // Centrer le texte dans les cellules
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Add custom renderer and editor for the "Action" column
     	table.getColumn("Action").setCellRenderer(new ButtonRenderer(model));
     	table.getColumn("Action").setCellEditor(new ButtonEditor(this, model, new JCheckBox()));
        
        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        //Ajout des infos sur le budget
        JPanel panelInfo = new JPanel();
        panelInfo.setBorder(new EmptyBorder(25, 0, 0, 0));
        
        panelInfo.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10,10,10,10);
	    
	    gbc.gridy = 0;
	    
	    JLabel labelTotal = new JLabel("Total");
        labelTotal.setFont(this.model.tahomaFont12);
        labelTotal.setForeground(this.model.colorSelect);
        gbc.gridx = 0;
        panelInfo.add(labelTotal, gbc);
        
        labelTotalCredit = new JLabel("CHF " + "0.0");
        labelTotalCredit.setFont(model.tahomaFont12);
        labelTotalCredit.setForeground(model.colorSelect);
        gbc.gridx = 2;
        panelInfo.add(labelTotalCredit, gbc);
        
        labelTotalDebit = new JLabel("CHF " + "0.0");
        labelTotalDebit.setFont(model.tahomaFont12);
        labelTotalDebit.setForeground(model.colorSelect);
        gbc.gridx = 3;
        panelInfo.add(labelTotalDebit, gbc);

        gbc.gridy = 1;
        
        labelSolde = new JLabel("Solde:");
        labelSolde.setFont(model.tahomaFont12);
        labelSolde.setForeground(model.colorSelect);
        gbc.gridx = 0;
        panelInfo.add(labelSolde, gbc);
        
        JLabel labelBaalance = new JLabel("CHF " + "0.0");
        labelBaalance.setFont(model.tahomaFont12);
        labelBaalance.setForeground(model.colorSelect);
        gbc.gridx = 2;
        panelInfo.add(labelBaalance, gbc);
        
		add(panelInfo, BorderLayout.SOUTH);
		
		
	}
	
	public void setRow(BudgetModel model, String date, String libelle, String credit, String debit) {
		
		// Centrer le texte dans les cellules
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Add custom renderer and editor for the "Action" column
		table.getColumn("Action").setCellRenderer(new ButtonRenderer(model));
		table.getColumn("Action").setCellEditor(new ButtonEditor(this, model, new JCheckBox()));
		
		comboBox.addItem(date);
		//JOptionPane.showMessageDialog(contentPanel, "Donnée ajoutée");
		
		// Appliquer un rendu personnalisé sur une JTable existante
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		                                                   boolean hasFocus, int row, int column) {
		        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		        if (!isSelected) {
		            component.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE); // Alternance des couleurs
		        } else {
		            component.setBackground(Color.CYAN); // Couleur pour la sélection
		        }

		        return component;
		    }
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		 labelTotalCredit.setText("CHF " + model.getAccountCredit());
	     labelTotalDebit.setText("CHF " + model.getAccountDebit());
	     labelSolde.setText("CHF " + model.getAccountSolde());		
	}
}

//Custom renderer for the button
class ButtonRenderer extends JButton implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public ButtonRenderer(BudgetModel model) {

	    setText("Remove");
	    setFont(model.tahomaFont12);
	    setForeground(model.colorSelect);
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

	 public ButtonEditor(WindowTable parent, BudgetModel model, JCheckBox checkBox) {
	     super(checkBox);


	     button = new JButton("Remove");
	     button.setFont(model.tahomaFont12);
	     button.setForeground(model.colorSelect);
    
	     button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
            //parent.tableModel.removeRow(row);
               
               // Remove an item by value
               parent.comboBox.removeItemAt(row + 1);
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

class AlternatingRowRenderer extends DefaultTableCellRenderer {
 
	private static final long serialVersionUID = 1L;

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (!isSelected) {
            component.setBackground(row % 2 == 0 ? Color.YELLOW : Color.WHITE);
        } else {
            component.setBackground(Color.BLUE);
        }

        return component;
    }
}