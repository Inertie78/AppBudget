package appBudget;
import java.awt.*;
import javax.swing.*;

public class WindowMenu  extends JMenuBar
{
	public JMenuItem menuQuit, menuNew, menuOpen, menuSave, menuApropos;
	private MainApp parent;

	public WindowMenu(MainApp _parent)
	{
		parent = _parent;
		
		// Créer la menu Fichier
		JMenu menuFichier = new JMenu("Fichier");

		// Créer les items du menu Fichier
		menuNew = new JMenuItem("Nouveau");
		menuOpen = new JMenuItem("Ouvrir");
		menuSave = new JMenuItem("Enregistrer");
		menuQuit = new JMenuItem("Quitter");

		// Inscrire les événements
		menuNew.addActionListener(parent);
		menuOpen.addActionListener(parent);
		menuSave.addActionListener(parent);
		menuQuit.addActionListener(parent);

		// Ajouter les items au menu Fichier
		menuFichier.add(menuNew);
		menuFichier.addSeparator();
		menuFichier.add(menuOpen);
		menuFichier.addSeparator();
		menuFichier.add(menuSave);
		menuFichier.addSeparator();  
		menuFichier.add(menuQuit);

		// Créer le menu Aide
		JMenu menuAide = new JMenu("Aide");

		// Créer l'item du menu Aide
		menuApropos = new JMenuItem("A propos");

		// Inscire l'événement
		menuApropos.addActionListener(parent);

		// Ajouter l'item au menu Aide
		menuAide.add(menuApropos);

		// Ajouter le menu Fichier à la barre de menu
		add(menuFichier);
		// Ajouter le menu Aide à la barre de menu
		add(menuAide);
	}
}