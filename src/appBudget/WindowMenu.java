package appBudget;
import java.awt.*;
import javax.swing.*;

public class WindowMenu  extends JMenuBar
{
	public JMenuItem menuQuitter, menuNouveau, menuApropos;
	private FenetrePrincipale parent;

	public WindowMenu(FenetrePrincipale _parent)
	{
		parent = _parent;
		
		// Créer la menu Fichier
		JMenu menuFichier = new JMenu("Fichier");

		// Créer les items du menu Fichier
		menuNouveau = new JMenuItem("Nouveau");
		menuQuitter = new JMenuItem("Quitter");

		// Inscrire les événements
		menuNouveau.addActionListener(parent);
		menuQuitter.addActionListener(parent);

		// Ajouter les items au menu Fichier
		menuFichier.add(menuNouveau);
		menuFichier.addSeparator();  // Ajouter un séparateur
		menuFichier.add(menuQuitter);

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