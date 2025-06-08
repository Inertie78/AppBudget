import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class WindowMenu  extends JMenuBar{
	
	static final long serialVersionUID = 1L;

	public WindowMenu(WindowMain parent, WindowTable windowTable, BudgetController controller){
		
		NeSaOP nesaop = new NeSaOP();
		
		// Créer la menu Fichier
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(controller.tahomaFont14);
		
		// Créer les items du menu Fichier
		JMenuItem newMenu = new JMenuItem("New");
		JMenuItem openMenu = new JMenuItem("Open");
		JMenuItem saveMenu = new JMenuItem("Save");
		JMenuItem quitMenu = new JMenuItem("Quit");

		//Font menu add
		newMenu.setFont(controller.tahomaFont12);
		openMenu.setFont(controller.tahomaFont12);
		saveMenu.setFont(controller.tahomaFont12);
		quitMenu.setFont(controller.tahomaFont12);
		
		// Inscrire les événements
		newMenu.addActionListener((_) -> {
			parent.restartUI();
		});
		
		openMenu.addActionListener((_) -> {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					nesaop.loadRows(file, windowTable, controller);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(windowTable, "Erreur lors la lecture du fichier csv" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
      	
		});
		
		saveMenu.addActionListener((_) -> {
			
			if (windowTable.table.getRowCount() > 0) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					nesaop.saveTableToCSV(controller, windowTable, file);
				}
			}else {
				//Message la table est vide
				JOptionPane.showMessageDialog(windowTable, "La table est vide", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		quitMenu.addActionListener((_) -> {
	        	System.exit(0);       	
	    });
		

		// Ajouter les items au menu Fichier
		fileMenu.add(newMenu);
		fileMenu.add(openMenu);
		fileMenu.add(saveMenu);
		fileMenu.addSeparator();  // Ajouter un séparateur
		fileMenu.add(quitMenu);
		
		// Ajouter le menu Fichier à la barre de menu
		add(fileMenu);
		
		// Créer le menu Aide
		JMenu helpMenu = new JMenu("Help");
		
		helpMenu.setFont(controller.tahomaFont14);

		// Créer l'item du menu Aide
		JMenuItem aboutMenu = new JMenuItem("About");
		
		//Font menu add
		aboutMenu.setFont(controller.tahomaFont12);

		// Inscire l'événement
		//aboutMenu.addActionListener(parent);

		// Ajouter l'item au menu Aide
		helpMenu.add(aboutMenu);

		add(Box.createHorizontalGlue());
		
		// Ajouter le menu Aide à la barre de menu
		add(helpMenu);
		
		aboutMenu.addActionListener((_) -> {
			String message = "<html>Création d'un budget personnel<br><br>Version de l'appilcation 1.0<br><br>Christophe et Guillaume</html>";
			new WindowMessages(parent, controller, "About", message);  	
		});
	}
}