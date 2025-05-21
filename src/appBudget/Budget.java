package appBudget;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Budget extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Budget frame = new Budget();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Budget() {
		
		
		setTitle("My Budget");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600, 600);

        Container c = getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc_container = new GridBagConstraints();
    
           // Zone haute
        JPanel panneau_haut = new JPanel();
        panneau_haut.setLayout(new GridBagLayout());
        panneau_haut.setPreferredSize(new Dimension(1600, 200));
        

        gbc_container.gridx = 0;
        gbc_container.gridy = 0;
        gbc_container.gridheight = 1;  // occupe 1 ligne
        gbc_container.gridwidth = 1;
        gbc_container.fill = GridBagConstraints.BOTH;
        gbc_container.weightx = 1.0;
        gbc_container.weighty = 0.2;
        c.add(panneau_haut, gbc_container);

           // Zone Basse
        JPanel panneau_bas = new JPanel();
        panneau_bas.setLayout(new GridBagLayout());
        panneau_bas.setPreferredSize(new Dimension(600, 300));
        GridBagConstraints gbc_panneau_bas = new GridBagConstraints();
        

        gbc_panneau_bas.gridx = 0;
        gbc_panneau_bas.gridy = 1;
        gbc_panneau_bas.gridheight = 1;  // occupe 1 ligne
        gbc_panneau_bas.gridwidth = 1;
        gbc_panneau_bas.anchor = GridBagConstraints.WEST;
        gbc_panneau_bas.fill = GridBagConstraints.BOTH;
        gbc_panneau_bas.weightx = 1.0;
        gbc_panneau_bas.weighty = 0.8;
        c.add(panneau_bas, gbc_panneau_bas);

        // Zone bleue
        JPanel panneau_bleu = new JPanel();
        panneau_bleu.setBackground(new Color(64, 224, 208));
        //panneau_bleu.setPreferredSize(new Dimension(500, 100));
        GridBagConstraints gbc_panneau_bleu = new GridBagConstraints();
        
        
        gbc_panneau_bleu.gridx = 0;
        gbc_panneau_bleu.gridy = 0;
        gbc_panneau_bleu.gridheight = 3;
        gbc_panneau_bleu.gridwidth = 1;
        gbc_panneau_bleu.anchor = GridBagConstraints.WEST;
        gbc_panneau_bleu.fill = GridBagConstraints.BOTH;
        gbc_panneau_bleu.weightx = 0.2;
        gbc_panneau_bleu.weighty = 1.0;
        panneau_haut.add(panneau_bleu, gbc_panneau_bleu);
        
        JPanel panneau_gris = new JPanel();
        //panneau_gris.setBackground(Color.GRAY);
        panneau_gris.setLayout(new GridBagLayout());
        //panneau_gris.setPreferredSize(new Dimension(100,  100));
        GridBagConstraints gbc_panneau_gris = new GridBagConstraints();

        gbc_panneau_gris.gridx = 1;
        gbc_panneau_gris.gridy = 0;
        gbc_panneau_gris.gridwidth = 1;
        gbc_panneau_gris.gridheight = 1;  // occupe 1 ligne
        gbc_panneau_gris.ipadx = 0; // padding horizontal interne
        gbc_panneau_gris.ipady = 0;
        gbc_panneau_gris.anchor = GridBagConstraints.NORTHEAST;
        gbc_panneau_gris.fill = GridBagConstraints.BOTH;
        gbc_panneau_gris.weightx = 0.8;
        gbc_panneau_gris.weighty = 1.0;
        panneau_haut.add(panneau_gris, gbc_panneau_gris);


         // Bouton "efface"
        JButton ok = new JButton("OK");
        GridBagConstraints gbc_ok = new GridBagConstraints();
        
        gbc_ok.gridx = 0;
        gbc_ok.gridy = 0;
        gbc_ok.gridheight = 1;
        gbc_ok.ipadx = 1; // padding horizontal interne
        gbc_ok.ipady = 1; // padding vertical interne
        gbc_ok.anchor = GridBagConstraints.CENTER;
        gbc_ok.fill = GridBagConstraints.NONE;
        panneau_gris.add(ok, gbc_ok);

        // Bouton "demarre"
        JButton cancel = new JButton("Cancel");
        GridBagConstraints gbc_cancel = new GridBagConstraints();
        
        gbc_cancel.gridx = 0;
        gbc_cancel.gridy = 1;
        gbc_cancel.ipadx = 0;
        gbc_cancel.ipady = 0;
        gbc_cancel.anchor = GridBagConstraints.CENTER;
        gbc_cancel.fill = GridBagConstraints.NONE;
        panneau_gris.add(cancel, gbc_cancel);
        
        //GridBagLayout gbl_panneau_bleu = new GridBagLayout();
        //gbl_panneau_bleu.columnWidths = new int[]{210, 46, 29, 0};
        //gbl_panneau_bleu.rowHeights = new int[]{14, 0, 0, 0};
        //gbl_panneau_bleu.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        //gbl_panneau_bleu.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        //panneau_bleu.setLayout(gbl_panneau_bleu);
        
        JLabel libelle = new JLabel("Libellé");
        libelle.setHorizontalAlignment(SwingConstants.LEFT);
        
        GridBagConstraints gbc_libelle = new GridBagConstraints();
                
        gbc_libelle.gridx = 0;
        gbc_libelle.gridy = 0;
        gbc_libelle.gridheight = 1;
        gbc_libelle.gridwidth = 1;
        gbc_libelle.ipadx = 0; // padding horizontal interne
        gbc_libelle.ipady = 0; // padding vertical interne
        gbc_libelle.anchor = GridBagConstraints.WEST;
        gbc_libelle.fill = GridBagConstraints.BOTH;
        gbc_libelle.weightx = 1.0;
        gbc_libelle.weighty = 0.3;
        gbc_libelle.insets = new Insets(10, 10, 5, 5);
        panneau_bleu.add(libelle, gbc_libelle);
        
        textField = new JTextField();
        GridBagConstraints gbc_textFieldLibelle = new GridBagConstraints();
        
        gbc_textFieldLibelle.gridx = 1;
        gbc_textFieldLibelle.gridy = 0;
        gbc_textFieldLibelle.anchor = GridBagConstraints.WEST;
        gbc_textFieldLibelle.fill = GridBagConstraints.BOTH;
        gbc_textFieldLibelle.weightx = 1.0;
        gbc_textFieldLibelle.weighty = 0.3;
        gbc_textFieldLibelle.insets = new Insets(0, 0, 5, 5);
        panneau_bleu.add(textField, gbc_textFieldLibelle);
        textField.setColumns(10);
        
        JLabel montant = new JLabel("Montant");
        GridBagConstraints gbc_montant = new GridBagConstraints();
        
        gbc_montant.gridx = 0;
        gbc_montant.gridy = 1;
        gbc_montant.gridheight = 1;
        gbc_montant.gridwidth = 1;
        gbc_montant.anchor = GridBagConstraints.WEST;
        gbc_montant.fill = GridBagConstraints.BOTH;
        gbc_montant.weightx = 1.0;
        gbc_montant.weighty = 0.3;
        gbc_montant.insets = new Insets(10, 10, 5, 5);
        panneau_bleu.add(montant, gbc_montant);
             
        
        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_montant = new GridBagConstraints();
        gbc_textField_montant.gridx = 1;
        gbc_textField_montant.gridy = 1;
        gbc_textField_montant.anchor = GridBagConstraints.WEST;
        gbc_textField_montant.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_montant.weightx = 1.0;
        gbc_textField_montant.weighty = 0.3;
        gbc_textField_montant.insets = new Insets(0, 0, 5, 5);
        panneau_bleu.add(textField_1, gbc_textField_montant);
        textField_1.setColumns(10);
        
        JLabel date = new JLabel("Date");
        GridBagConstraints gbc_date = new GridBagConstraints();
        
        gbc_date.gridx = 0;
        gbc_date.gridy = 2;
        gbc_date.gridheight = 1;
        gbc_date.gridwidth = 1;
        gbc_date.anchor = GridBagConstraints.WEST;
        gbc_date.fill = GridBagConstraints.HORIZONTAL;
        gbc_date.weightx = 1.0;
        gbc_date.weighty = 0.3;
        gbc_date.insets = new Insets(10, 10, 0, 5);
        panneau_bleu.add(date, gbc_date);
        
        textField_2 = new JTextField();
        GridBagConstraints gbc_textField_date = new GridBagConstraints();
        
        gbc_textField_date.gridx = 1;
        gbc_textField_date.gridy = 2;
        gbc_textField_date.anchor = GridBagConstraints.WEST;
        gbc_textField_date.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_date.weightx = 1.0;
        gbc_textField_date.weighty = 0.3;
        gbc_textField_date.insets = new Insets(0, 0, 0, 5);
        panneau_bleu.add(textField_2, gbc_textField_date);
        textField_2.setColumns(10);
        

        

        JPanel panneau_gris2 = new JPanel();
        //panneau_gris2.setBackground(Color.GRAY);
        panneau_gris2.setLayout(new GridBagLayout());
        panneau_gris2.setPreferredSize(new Dimension(100,  300));
        GridBagConstraints gbc_panneau_gris2 = new GridBagConstraints();

        gbc_panneau_gris2.gridx = 0;
        gbc_panneau_gris2.gridy = 0;
        gbc_panneau_gris2.gridwidth = 1;
        gbc_panneau_gris2.gridheight = 1;  // occupe 1 ligne
        gbc_panneau_gris2.anchor = GridBagConstraints.NORTHWEST;
        gbc_panneau_gris2.fill = GridBagConstraints.BOTH;
        gbc_panneau_gris2.weightx = 0.1;
        gbc_panneau_gris2.weighty = 1.0;
        panneau_bas.add(panneau_gris2, gbc_panneau_gris2);

                // Checkbox "relief"
        JCheckBox choix1 = new JCheckBox("choix 1");
        GridBagConstraints gbc_choix1 = new GridBagConstraints();
        
        gbc_choix1.gridx = 0;
        gbc_choix1.gridy = 0;
        gbc_choix1.gridwidth = 1;
        gbc_choix1.ipadx = 0;
        gbc_choix1.ipady = 0;
        gbc_choix1.anchor = GridBagConstraints.NORTHWEST;
        gbc_choix1.fill = GridBagConstraints.NONE;
        panneau_gris2.add(choix1, gbc_choix1);

        // Checkbox "gras"
        JCheckBox choix2 = new JCheckBox("choix 2");
        GridBagConstraints gbc_choix2 = new GridBagConstraints();
        
        gbc_choix2.gridx = 0;
        gbc_choix2.gridy = 2;
        gbc_choix2.gridwidth = 1;
        gbc_choix2.ipadx = 0;
        gbc_choix2.ipady = 0;
        gbc_choix2.anchor = GridBagConstraints.NORTHWEST;
        gbc_choix2.fill = GridBagConstraints.NONE;
        panneau_gris2.add(choix2, gbc_choix2);

        JPanel panneau_blanc = new JPanel();
        panneau_blanc.setBackground(Color.WHITE);
        panneau_blanc.setLayout(new GridBagLayout());
        panneau_blanc.setPreferredSize(new Dimension(500,  300));
        GridBagConstraints gbc_panneau_blanc = new GridBagConstraints();

        gbc_panneau_blanc.gridx = 1;
        gbc_panneau_blanc.gridy = 0;
        gbc_panneau_blanc.gridwidth = 1;
        gbc_panneau_blanc.gridheight = 1;  // occupe 1 ligne
        gbc_panneau_blanc.anchor = GridBagConstraints.NORTHWEST;
        gbc_panneau_blanc.fill = GridBagConstraints.BOTH;
        gbc_panneau_blanc.weightx = 0.9;
        gbc_panneau_blanc.weighty = 1.0;
        panneau_bas.add(panneau_blanc, gbc_panneau_blanc);

        setVisible(true);
    }

    }
