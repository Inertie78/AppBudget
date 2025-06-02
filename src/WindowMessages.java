import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WindowMessages extends JDialog{

	private static final long serialVersionUID = 1L;
	public JButton boutonOk;
	
	public WindowMessages(JFrame parent, BudgetModel model, String title, String texte){
		// true --> dialogue modal
		
		super(parent, title, true);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
		
		JPanel labelPane = new JPanel();

		// Créer le label pour le texte
		JLabel label = new JLabel(texte, JLabel.CENTER);
		label.setFont(model.tahomaFont12);
		labelPane.add(label);
		
		labelPane.setBorder(new EmptyBorder(0, 0, 25, 0));

		contentPane.add(labelPane);
		
	
		JPanel boutonPane = new JPanel();
		// Créer le bouton
		boutonOk = new JButton("Ok");
		boutonOk.setFont(model.tahomaFont12);
		boutonOk.addActionListener((_) -> {
			dispose();
	    });
		
		boutonOk.setPreferredSize(new Dimension(200, 25));
		
		boutonPane.add(boutonOk);
		
		contentPane.add(boutonPane);
		
		add(contentPane);

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
}