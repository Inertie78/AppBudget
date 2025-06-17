import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

//classe de mise en place de la fenêtre principale
public class WindowMain extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public WindowMain(String title, int width, int height){

		super(title);
		

		this.setLocation(50, 100);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//instancier les classes
		BudgetModel model = new BudgetModel();
		BudgetController controller = new BudgetController(model);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		//taille et positon de la fenêtre principale
		setBounds(50, 100, width, height);
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		
		//instancie un formulaire et l'ajoute au panneau
		contentPanel.add(new WindowForms(controller), BorderLayout.WEST);
		
		//ajout d'une table au panneau
		WindowTable windowTable = new WindowTable(model, controller);
		contentPanel.add(windowTable, BorderLayout.CENTER);
		
		//ajouter le panneau avec le formulaire et la table à la fenêtre
		setContentPane(contentPanel);
		
		//instancie un menu et l'ajoute à la page
		setJMenuBar(new WindowMenu(this, windowTable, controller));
		
		 pack();
		 setVisible(true);
	}
	
	public void restartUI() {
		//ferme la fenêtre actuelle
	    dispose(); 
	    SwingUtilities.invokeLater(() -> {
	    	WindowMain nouvelleFenetre = new WindowMain ("Application de gestion d'un budget personnel", 1200, 600);
	        nouvelleFenetre.setVisible(true);
	    });
	}
}