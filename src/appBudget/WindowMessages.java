

package appBudget;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class  WindowMessages extends JDialog implements ActionListener
{
	private MainApp parent;
	private JButton boutonOk;
	
	public WindowMessages(JFrame _parent, String title, String texte)
	{
		// true --> dialogue modal
		super(_parent, title, true);
		parent = (MainApp)_parent;

		setLayout(new GridLayout(2, 1));

		// Créer le label pour le texte
		JLabel label = new JLabel(texte, JLabel.CENTER);
		add(label);

		// Créer le bouton
		boutonOk = new JButton("Ok");
		boutonOk.addActionListener(this);
		add(boutonOk);

		//Initialize this dialog to its preferred size.
		pack();

		// Position du parent à l'écran
		Point p = parent.getLocationOnScreen();
		// Position de la boîte de dialogue au centre de la fenêtre parente
		int posX = p.x + parent.getWidth()/2 - this.getWidth()/2;
		int posY = p.y + parent.getHeight()/2 - this.getHeight()/2;
		setLocation(posX, posY);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if(source == boutonOk)
		{
			dispose();
		}
	}
}
