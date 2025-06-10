//a mettre dans MainApp


package appBudget;


import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
		public MyPanel() {
	
        
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.add(WindowForm.createFormPanel(), BorderLayout.WEST);
      
       
        
    }
}

			/*JMenuItem open = new JMenuItem("Open");
			JMenuItem save = new JMenuItem("Save");
			JMenuItem new1 = new JMenuItem("New");
			JMenuItem quit = new JMenuItem("Quit");

			file.add(new1);
			file.add(open);
			file.add(save);
			file.add(quit);
			setJMenuBar(barre);
			contentPane.setLayout(new BorderLayout());

			// Zone gauche
			JPanel panel_gauche = new JPanel();
			panel_gauche.setLayout(new GridLayout(3, 1));
			panel_gauche.setPreferredSize(new Dimension(400, 500));	
			contentPane.add(panel_gauche, BorderLayout.WEST);
			
			// Panneau gauche up 
			
			JPanel panel_gu = new JPanel();
			panel_gu.setLayout(new BorderLayout());
			//panel_gu.setPreferredSize(new Dimension(220, 500));	
			//panel_gu.setBackground(Color.PINK);
			panel_gauche.add(panel_gu);
			
			
			
			// Panneau gauche centre 
			
			JPanel panel_gc = new JPanel();
			panel_gc.setLayout(new GridLayout(3, 2, 10, 50));
			panel_gc.setBorder(new EmptyBorder(50, 50, 50, 50));
			//panel_gc.setPreferredSize(new Dimension(60, 500));	
			//panel_gc.setBackground(Color.YELLOW);
			panel_gauche.add(panel_gc);
			
			// Panneau gauche down
			
			JPanel panel_gd = new JPanel();
			panel_gd.setLayout(new GridLayout(2, 4, 10, 50));
			panel_gd.setBorder(new EmptyBorder(10, 10, 200, 50));
			//panel_gd.setPreferredSize(new Dimension(220, 500));	
			//panel_gd.setBackground(Color.BLUE);
			panel_gauche.add(panel_gd);
			
			// Zone droite
			
			JPanel panel_droite = new JPanel();
			panel_droite.setLayout(new BorderLayout());
			panel_droite.setPreferredSize(new Dimension(600, 500));		
			panel_droite.setBackground(Color.YELLOW);
			contentPane.add(panel_droite, BorderLayout.EAST);
			
		
			
			Panel panel_centre = new JPanel();
			panel_centre.setLayout(new BorderLayout());
			panel_droite.setBackground(Color.BLACK);
			contentPane.add(panel_centre, BorderLayout.CENTER);
			
			JLabel libelle = new JLabel("Libellé : ");
			libelle.setHorizontalAlignment(SwingConstants.RIGHT);
			//libelle.setSize(5, 50);
			panel_gc.add(libelle);
			
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.LEFT);
			panel_gc.add(textField);
			textField.setColumns(10);
			
			JLabel montant = new JLabel("Montant : ");
			montant.setHorizontalAlignment(SwingConstants.RIGHT);
			libelle.setSize(5, 50);
			panel_gc.add(montant);
			
			textField_2 = new JTextField();
			textField_2.setHorizontalAlignment(SwingConstants.LEFT);
			panel_gc.add(textField_2);
			textField_2.setColumns(10);
			
			JLabel date = new JLabel("Date : ");
			date.setHorizontalAlignment(SwingConstants.RIGHT);
			libelle.setSize(5, 50);
			panel_gc.add(date);
			
			
			textField_1 = new JTextField();
			textField_1.setHorizontalAlignment(SwingConstants.LEFT);
			panel_gc.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel place_gd1 = new JLabel();
			libelle.setSize(5, 50);
			panel_gd.add(place_gd1);
			
			JLabel place_gd2 = new JLabel();
			libelle.setSize(5, 50);
			panel_gd.add(place_gd2);
			
			JButton credit = new JButton("Crédit");
			credit.setSize(5, 50);
			panel_gd.add(credit);
			
			JButton debit = new JButton("Débit");
			credit.setSize(5, 50);
			panel_gd.add(debit);
			
			
			JLabel place_gd3 = new JLabel();
			libelle.setSize(5, 50);
			panel_gd.add(place_gd3);
			
			JLabel place_gd4 = new JLabel();
			libelle.setSize(5, 50);
			panel_gd.add(place_gd4);
			
			JLabel place_gd5 = new JLabel();
			libelle.setSize(5, 50);
			panel_gd.add(place_gd5);
			
			JLabel place_gd6 = new JLabel();
			libelle.setSize(5, 50);
			panel_gd.add(place_gd6);
			
			JLabel test = new JLabel("Test : ");
			libelle.setSize(5, 50);
			panel_droite.add(test, BorderLayout.CENTER);*/
			
			
		
