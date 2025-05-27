package appBudget;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.formdev.flatlaf.*;






public class MainApp {

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
					FenetrePrincipale win = new FenetrePrincipale("Mon Budget", 1000, 1000);
					win.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
		
		


	
