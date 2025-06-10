package appBudget;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.*;






public class MainApp extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	
	private static final long serialVersionUID = 1L;
	private WindowMenu wm;
	private WindowMessages message;

	
	public static void main(String[] args) {

		
		try {
			//UIManager.setLookAndFeel(new FlatLightLaf());
			//UIManager.setLookAndFeel(new FlatDarkLaf());
			UIManager.setLookAndFeel(new FlatDarculaLaf());
			//UIManager.setLookAndFeel(new FlatIntelliLaf());
		} catch (Exception e) {
			System.err.println("Erreur lors du chargement de FlatLaf");
		}
		
		EventQueue.invokeLater(new Runnable() {                     //attends que tout soit fini avant d'aller plus loin 
			public void run() {
				try {				
									
					MainApp myFrame = new MainApp();
					myFrame.createFrame("Mon Budget", 1000, 1000);
					myFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				
				}
				
			}
		});
	}
	
	public void createFrame(String titre, int larg, int haut) {
		
		//crée la fenêtre avec titre, taille et position
		setTitle(titre);
		setSize(larg, haut);
		setLocation(50, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Ajoute la barre de menus à la fenêtre
		wm = new WindowMenu(this);
		setJMenuBar(wm);
		
		//crée et ajoute un Panneau à la fenêtre
		
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout());
		main_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
		main_panel.add(WindowForm.createFormPanel(), BorderLayout.WEST);
		
		add(main_panel, BorderLayout.WEST);
		
	}

//Les 2 méthodes de MouseListener
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
//Les 5 méthodes de MouseMotionListener
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
//La méthode de ActionListener
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == wm.menuNew)
		{
			new WindowMessages(this, "Message", "Tu as cliqué sur le menu Nouveau");
		}
		
		else if(source == wm.menuOpen)
		{
			new WindowMessages(this, "Message", "Tu as cliqué sur le menu Ouvrir");}
		
		else if(source == wm.menuSave)
		{
			new WindowMessages(this, "Message", "Tu as cliqué sur le menu Sauvegarder");}
		
		else if(source == wm.menuQuit)
		{
			System.exit(0);
		}
		else if(source == wm.menuApropos)
		{
			new WindowMessages(this, "Message", "Application de gesiton de budget créée par Christophe Debons et Guillaume Leibzig, dans le cadre d'un CAS");
		}
	}
}


		
		


	
