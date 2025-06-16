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

	public WindowTable(BudgetModel model, BudgetController controller){
		
		this.controller = controller;
		
		setBorder(new EmptyBorder(50, 25, 50, 0));
		setLayout(new BorderLayout());
		
		//Crée l'objet table
		table = new JTable(model){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//Pour alterner  les couleurs des lignes 
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

		//Police de la table
        table.setFont(this.controller.tahomaFont12);
	    
	    // Centrer le texte dans les cellules
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Ajouter un moteur de rendu et un éditeur personnalisés pour la colonne « Action »
        refreshButtonRenderer();

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
		String s1[] = { "Select date"};
		this.comboxDates = new JComboBox<>(s1);
		this.comboxDates.setFont(this.controller.tahomaFont12);
		this.comboxDates.setForeground(this.controller.colorSelect);
		this.comboxDates.setSelectedIndex(0);
		
		JLabel labelComboBoxDate = new JLabel("Select: ");
		labelComboBoxDate.setFont(this.controller.tahomaFont12);
		
		// Inscrire l'événement date
		this.comboxDates.addActionListener(e -> { onFilterChanged(); });
		
		JPanel panelComboBoxSelect = new JPanel();
		panelComboBoxSelect.setBorder(new EmptyBorder(0, 0, 0, 50));
		panelComboBoxSelect.add(labelComboBoxDate);
		panelComboBoxSelect.add(this.comboxDates);
		
		String s2[] = {"Select libellé"};
		this.comboxLibelle = new JComboBox<>(s2);
		this.comboxLibelle.setFont(this.controller.tahomaFont12);
		this.comboxLibelle.setForeground(this.controller.colorSelect);
		this.comboxLibelle.setSelectedIndex(0);
		
		// Ajout du combobox libellé au paneau
		panelComboBoxSelect.add(this.comboxLibelle);
		
		// Inscrire l'événement libelle 
		this.comboxLibelle.addActionListener(e -> { onFilterChanged(); });	
		
		JPanel panelComboBox = new JPanel();
		panelComboBox.setBorder(new EmptyBorder(0, 0, 25, 0));
		panelComboBox.add(panelComboBoxSelect);

		// Ajout du panneau combobox à la fenêtre
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
        
        this.labelTotalCredit = new JLabel("CHF " + "0.0");
        this.labelTotalCredit.setFont(this.controller.tahomaFont12);
        this.labelTotalCredit.setForeground(this.controller.colorSelect);
        gbc.gridx = 2;
        panelInfo.add(this.labelTotalCredit, gbc);
        
        this.labelTotalDebit = new JLabel("CHF " + "0.0");
        this.labelTotalDebit.setFont(this.controller.tahomaFont12);
        this.labelTotalDebit.setForeground(this.controller.colorSelect);
        gbc.gridx = 3;
        panelInfo.add(this.labelTotalDebit, gbc);

        gbc.gridy = 1;
        
        JLabel labelSolde = new JLabel("Solde:");
        labelSolde.setFont(this.controller.tahomaFont12);
        labelSolde.setForeground(this.controller.colorSelect);
        gbc.gridx = 0;
        panelInfo.add(labelSolde, gbc);
        
        this.labelBalance = new JLabel("CHF " + "0.0");
        this.labelBalance.setFont(this.controller.tahomaFont12);
        this.labelBalance.setForeground(this.controller.colorSelect);
        gbc.gridx = 2;
        panelInfo.add(this.labelBalance, gbc);
    
		add(panelInfo, BorderLayout.SOUTH);
		
		model.addPropertyChangeListener(this);
	}

	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
		this.labelTotalCredit.setText("CHF " + String.valueOf(this.controller.getSoldeCredit()));
		this.labelTotalDebit.setText("CHF " + String.valueOf(this.controller.getSoldeDebit()));
		this.labelBalance.setText("CHF " + String.valueOf(this.controller.getSolde()));	
		
		// Vérifie si la date est déjà dans le combo
		String date = this.controller.getDate();
		this.comboxDates = checkCombobox(this.comboxDates, date);

		String libelle = this.controller.getLibelle();
		this.comboxLibelle = checkCombobox(this.comboxLibelle, libelle);

	}
	
	
	public void refreshButtonRenderer() {
		table.getColumn("Action").setCellRenderer(new ButtonRenderer(this.controller));
     	table.getColumn("Action").setCellEditor(new ButtonEditor(this, this.controller, new JCheckBox()));
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
	
	//Est appelé si les filtres changent et applique les filtres
	private void onFilterChanged() {
        String selectedDate = (String) this.comboxDates.getSelectedItem();
        String selectedLabel = (String) this.comboxLibelle.getSelectedItem();

        controller.applyFilters(selectedDate, selectedLabel);
        updateComboBoxes(selectedDate, selectedLabel);
    }

	//Mets à jour les filtres en fonction de leur sélection respective
    private void updateComboBoxes(String selectedDate, String selectedLabel) {
        // Mise à jour du combo Libellé selon la Date sélectionnée
    	Set<String> labels = new TreeSet<>();
        labels = this.controller.getFilteredLibelles(selectedLabel);
        this.comboxLibelle.removeAllItems();
        this.comboxLibelle.addItem("Select libellé");
        for (String label : labels) this.comboxLibelle.addItem(label);
        this.comboxLibelle.setSelectedItem(selectedLabel);

        // Mise à jour du combo Date selon le Libellé sélectionné
        Set<String> dates = new TreeSet<>();
        dates = this.controller.getFilteredDates(selectedDate);
        this.comboxDates.removeAllItems();
        this.comboxDates.addItem("Select date");
        for (String date : dates) this.comboxDates.addItem(date);
        this.comboxDates.setSelectedItem(selectedDate);
    }
}

//Pour ajouter un bouton dans uns cellule du tableau
class ButtonRenderer extends JButton implements TableCellRenderer {
	private static final long serialVersionUID = 1L;
  
	private BudgetController controller;

	public ButtonRenderer(BudgetController controller) {
			this.controller = controller;
			
	 }
	
	 @Override
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setForeground(controller.colorSelect);	
		setOpaque(true); 
		
		try {
			ImageIcon trashIcon = new ImageIcon("assets/trash_gray.png");
			Image trash = trashIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			ImageIcon scaledTrash = new ImageIcon(trash);
			setIcon(scaledTrash);
		 }
		 catch (Exception ex) {
			 System.err.println("Icone poubelle introuvable :" + ex.getMessage());
		 }
		 return this;
	 }
}

//Editeur personnalisé pour le bouton
class ButtonEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 1L;
	
	private JButton button;

	 private int row;

	 public ButtonEditor(WindowTable parent, BudgetController controller, JCheckBox checkBox) {
	     super(checkBox);

	     this.button = new JButton();
	     this.button.setOpaque(true);
	     
	     this.button.setForeground(controller.colorSelect);
	     
	     this.button. setOpaque(true);
	     
	     try {ImageIcon trashIcon = new ImageIcon("assets/trash_gray.png");

		 	Image trash = trashIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	    	ImageIcon scaledTrash = new ImageIcon(trash);
	     	button.setIcon(scaledTrash);
	     }
	     catch (Exception ex) {
	    	 System.err.println("Icone poubelle introuvable :" + ex.getMessage());
	     }
	     
    
	     this.button.addActionListener(new ActionListener() {
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
               
               fireEditingStopped();
	    	}
       });
	 };
	
	 @Override
	 public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	     this.row = row; // Store the row index
	     
	     return this.button;
	 }
	
	 @Override
	 public Object getCellEditorValue() {
	     return "Delete";
	 }
}