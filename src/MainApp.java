import javax.swing.*;

import com.formdev.flatlaf.*;

public class MainApp {
	public static void main(String param[]){
		try {
			//Pour changer le design de l'application
			UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {System.err.println("Erreur lors du chargement de FlatLaf");}

		
		SwingUtilities.invokeLater(() -> {
			new WindowMain ("Application de gestion d'un budget personnel", 1200, 600);
        });
	} // Fin de la méthode de main()
}