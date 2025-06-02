import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

public class WindowMain extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public WindowMain(String title, int width, int height){

		super(title);
		
		this.setLocation(50, 100);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BudgetModel model = new BudgetModel();
		BudgetController controller = new BudgetController(model);
		
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 // Taille et positon de la fenêtre principale
		setBounds(50, 100, width, height);
		
		// Ajouter la barre de menus à la fenêtre
		setJMenuBar(new WindowMenu(this, model, controller));
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		
		contentPanel.add(new WindowForms(model, controller), BorderLayout.WEST);
		
		// add une table	
		contentPanel.add(new WindowTable(model, controller), BorderLayout.CENTER);
		
		setContentPane(contentPanel);
		
		 pack();
		 setVisible(true);
	}
	
	// Ajoute les valeurs à la table
	//private void appendTable(Boolean creditCLick) {
		// List<String> stringArray = wf.getValue(creditCLick);
		//if(stringArray.get(4) == "false") {
		//	JOptionPane.showMessageDialog(contentPanel, "Veuillez remplir tout les champs !");
		//}else {
			//(String date, String libelle, String credit, String debit)
			//wt.setRow(stringArray.get(0), stringArray.get(1), stringArray.get(2), stringArray.get(3));
		//}
	//}
}