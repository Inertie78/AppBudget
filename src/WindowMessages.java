import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WindowMessages extends JDialog{

	private static final long serialVersionUID = 1L;
	
	public WindowMessages(JFrame parent, BudgetController controller, String title, String texte){

		super(parent, title, true);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
		
		JPanel labelPane = new JPanel();

		// Créer le label pour le texte
		JLabel label = new JLabel(texte, JLabel.CENTER);
		label.setFont(controller.tahomaFont12);
		labelPane.add(label);
		
		labelPane.setBorder(new EmptyBorder(0, 0, 25, 0));

		contentPane.add(labelPane);
		
		JPanel boutonPane = new JPanel();
		
		// Créer le bouton pour fermer la fenêtre
		JButton boutonOk = new JButton("Ok");
		boutonOk.setFont(controller.tahomaFont12);
		boutonOk.addActionListener((e) -> {
			dispose();
	    });
		
		boutonOk.setPreferredSize(new Dimension(200, 25));
		
		boutonPane.add(boutonOk);
		
		contentPane.add(boutonPane);
		
		add(contentPane);

		//Initialisez cette boîte de dialogue à sa taille préférée
		pack();
		
		// Position du parent à l'écran
		Point p = parent.getLocationOnScreen();
		// Position de la boîte de dialogue au centre de la fenêtre parente
		int posX = p.x + parent.getWidth()/2 - this.getWidth()/2;
		int posY = p.y + parent.getHeight()/2 - this.getHeight()/2;
		setLocation(posX, posY);

		setVisible(true);
	}
}