import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class NeSaOP {
	
	public void saveTableToCSV(BudgetController controller, JTable table, File file) {
		try (FileWriter fw = new FileWriter(file)) {

	        // Écrire les données
	        for (int row = 0; row < controller.getRowCount(); row++) {
	            for (int col = 0; col < controller.getColumnCount(); col++) {
	                fw.write(String.valueOf(controller.getValueAt(row, col)));
	                if (col < controller.getColumnCount() - 1) fw.write(",");
	            }
	            fw.write("\n");
	        }

	        fw.flush();
	        JOptionPane.showMessageDialog(null, "Fichier sauvegardé avec succès !");
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde : " + e.getMessage());
	    }
	}
	
	public void loadRows(File csvFile, BudgetController controller) throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
	        String line;
	        boolean isFirstLine = true;

	        while ((line = reader.readLine()) != null) {
	            String[] values = line.split(",", -1); // -1 to include empty values
	            
                String trav = values[0];
                String[] date = trav.split(" ", -2); // -1 to include empty values
              
                controller.setDay((String) date[0]);
                controller.setMonth((String) date[1]);
                controller.setYear((String) date[2]);
                
                Float credit = 0.0f;
                Float debit = 0.0f;
                System.out.println(isNumeric(values[2]));
                System.out.println(values[2]);
                    
                if(isNumeric(values[2])) {credit = Float.parseFloat(values[2]);}
                if(isNumeric(values[3])) {debit = Float.parseFloat(values[3]);}
                
                controller.addEntry(values[1], credit, debit);
	        }
	    }catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Erreur lors de l'ouvertur du fichier csv" + e.getMessage());
	    }
	}
	
	public static boolean isNumeric(String str) {
        try {
        	Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}