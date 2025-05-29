
//a mettre dans MainApp va disparaitre

//import java.awt.*;
package appBudget;

 import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class  FenetrePrincipale extends JFrame
					implements ActionListener, MouseListener, MouseMotionListener{
		

	private static final long serialVersionUID = 1L;
	private WindowMenu wm;

	public FenetrePrincipale(String titre, int larg, int haut)
	{
		
		
		super(titre);

		// Créer la barre de menus
		wm = new WindowMenu(this);
		// Ajouter la barre de menus à la fenêtre
		setJMenuBar(wm);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Taille et positon de la fenêtre principale
		setSize(larg, haut);
		setLocation(50, 100);

// Inscrire les événements
		addMouseListener(this);
		addMouseMotionListener(this);
		
		
		
		JPanel main_panel = new JPanel();
		
		main_panel.setLayout(new BorderLayout());
		main_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
		main_panel.add(WindowForm.createFormPanel(), BorderLayout.WEST);
		
		add(main_panel, BorderLayout.WEST); 


		setVisible(true);
	}

//************************************************************************
// Les 2 méthodes de MouseListener
	public void mouseMoved(MouseEvent e)
	{
		System.out.println("mouseMoved " + e.getX() + ", " + e.getY() +
												", " + e.getClickCount());
	}

	public void mouseDragged(MouseEvent e)
	{
		System.out.println("mouseDragged " + e.getX() + ", " + e.getY() +
												", " + e.getClickCount());
	}

//************************************************************************
// Les 5 méthodes de MouseMotionListener
	public void mouseClicked(MouseEvent e)
	{
		System.out.println("mouseClicked " + e.getX() + ", " + e.getY() +
												", " + e.getClickCount());
	}

	public void mousePressed(MouseEvent e)
	{
		System.out.println("mousePressed " + e.getX() + ", " + e.getY() +
												", " + e.getClickCount());
	}

	public void mouseReleased(MouseEvent e)
	{
		System.out.println("mouseReleased " + e.getX() + ", " + e.getY() +
												", " + e.getClickCount());
	}

	public void mouseEntered(MouseEvent e)
	{
		System.out.println("mouseEntered " + e.getX() + ", " + e.getY() +
												", " + e.getClickCount());
	}

	public void mouseExited(MouseEvent e)
	{
		System.out.println("mouseExited " + e.getX() + ", " + e.getY() +
												", " + e.getClickCount());
	}

//************************************************************************
// La méthode de ActionListener
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == mf.menuNouveau)
		{
			new MonMessage(this, "Message", "menuNouveau");
		}
		else if(source == mf.menuQuitter)
		{
			System.exit(0);
		}
		else if(source == mf.menuApropos)
		{
			new MonMessage(this, "Message", "menuApropos");
		}
	}
}
