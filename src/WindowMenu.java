import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

//classe qui crée la barre des menus et titre
public class WindowMenu  extends JMenuBar{
	
	static final long serialVersionUID = 1L;

	public WindowMenu(WindowMain parent, WindowTable windowTable, BudgetController controller){
		
		NeSaOP nesaop = new NeSaOP();
		
		String path = (String) new File("").getAbsolutePath() + "/data";
		
		//créer la menu Fichier
		JMenu fileMenu = new JMenu("Fichier");
		fileMenu.setFont(controller.tahomaFont14);
		
		// Créer les items du menu Fichier
		JMenuItem newMenu = new JMenuItem("Nouveau");
		JMenuItem openMenu = new JMenuItem("Ouvrir");
		JMenuItem saveMenu = new JMenuItem("Sauver");
		JMenuItem quitMenu = new JMenuItem("Quitter");

		//police personnalisée
		newMenu.setFont(controller.tahomaFont12);
		openMenu.setFont(controller.tahomaFont12);
		saveMenu.setFont(controller.tahomaFont12);
		quitMenu.setFont(controller.tahomaFont12);
		
		//inscrire l'événement nouveau
		newMenu.addActionListener((e) -> {
			parent.restartUI();
		});
		
		//inscrire l'événement ouvrir
		openMenu.addActionListener((e) -> {
			
			controller.clearTable();
			
			windowTable.refreshButtonRenderer();
			
			JFileChooser fileChooser = new JFileChooser(path);
			FileNameExtensionFilter filter = new FileNameExtensionFilter( "Fichiers csv.", "csv");
			fileChooser.setFileFilter(filter);
			fileChooser.setDialogTitle("Importation d'un fichier csv");
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					nesaop.loadRows(file, parent, controller);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(parent, "Erreur lors la lecture du fichier csv" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
      	
		});
		
		//inscrire l'événement sauver
		saveMenu.addActionListener((e) -> {
			
			if (windowTable.table.getRowCount() > 0) {
				JFileChooser fileChooser = new JFileChooser(path);
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					nesaop.saveTableToCSV(controller, parent, file);
				}
			}else {
				//Message la table est vide
				JOptionPane.showMessageDialog(parent, "La table est vide", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		//inscrire l'événement quitter
		quitMenu.addActionListener((e) -> {
	        	System.exit(0);       	
	    });
		

		//ajouter les items au menu Fichier
		fileMenu.add(newMenu);
		fileMenu.add(openMenu);
		fileMenu.add(saveMenu);
		//ajouter un séparateur
		fileMenu.addSeparator(); 
		fileMenu.add(quitMenu);
		
		//ajouter le menu Fichier à la barre de menu
		add(fileMenu);
		
		//créer le menu Aide
		JMenu helpMenu = new JMenu("Help");
		
		//police personnalisée
		helpMenu.setFont(controller.tahomaFont14);

		//créer l'item du menu Aide
		JMenuItem aboutMenu = new JMenuItem("About");
		
		//police personnalisée
		aboutMenu.setFont(controller.tahomaFont12);

		//ajouter l'item au menu Aide
		helpMenu.add(aboutMenu);

		add(Box.createHorizontalGlue());
		
		//ajouter le menu Aide à la barre de menu
		add(helpMenu);
		
		aboutMenu.addActionListener((e) -> {
			String message = "<html>Création d'un budget personnel<br><br>Version de l'appilcation 1.0<br><br>Christophe et Guillaume</html>";
			new WindowMessages(parent, controller, "About", message);  	
		});
	}
}