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
}