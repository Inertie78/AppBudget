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
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		
		contentPanel.add(new WindowForms(controller), BorderLayout.WEST);
		
		// add une table
		WindowTable windowTable = new WindowTable(model, controller);
		contentPanel.add(windowTable, BorderLayout.CENTER);
		
		setContentPane(contentPanel);
		
		// Ajouter la barre de menus à la fenêtre
		setJMenuBar(new WindowMenu(this, windowTable, controller));
		
		 pack();
		 setVisible(true);
	}
	
	public void restartUI() {
	    dispose(); // ferme la fenêtre actuelle
	    SwingUtilities.invokeLater(() -> {
	    	WindowMain nouvelleFenetre = new WindowMain ("Application de gestion d'un budget personnel", 1200, 600);

	        nouvelleFenetre.setVisible(true);
	    });
	}
}