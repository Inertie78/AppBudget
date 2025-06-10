/*import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NeSaOP {
	
	public BudgetModel model;
	public WindowMain windowMain;
	
	public void jsonSave(File file) {
		try (Writer writer = new FileWriter(file)) {
	        Gson gson = new GsonBuilder()
	                        .setPrettyPrinting()    // pour un JSON indenté
	                        .create();
	        gson.toJson(model, writer);
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        // Afficher un message d’erreur à l’utilisateur
		    }
		}

	
	public void jsonOpen(File file) {
	    try (Reader reader = new FileReader(file)) {
	        Gson gson = new Gson();
	        BudgetModel loaded = gson.fromJson(reader, BudgetModel.class);
	        if (loaded != null) {
	            // Remplace les données de l’ancien modèle
	            model.getAccountList().clear();
	            model.getAccountList().addAll(loaded.getAccountList());
	            model.fireTableDataChanged();    // notifie la JTable
	            windowMain.onModelReloaded();      // méthode à implémenter pour rafraîchir combos, totaux…
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(
	            null,
	            "Erreur lors de l'ouverture :\n" + ex.getMessage(),
	            "Erreur",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	    Object src = e.getSource();
	    if (src == windowMenu.saveMenu) {
	        JFileChooser chooser = new JFileChooser();
	        chooser.setFileFilter(new FileNameExtensionFilter("Budget JSON (*.json)", "json"));
	        int ret = chooser.showSaveDialog(this);
	        if (ret == JFileChooser.APPROVE_OPTION) {
	            File file = chooser.getSelectedFile();
	            // assure-toi que l'extension .json est bien là
	            if (!file.getName().toLowerCase().endsWith(".json")) {
	                file = new File(file.getParentFile(), file.getName() + ".json");
	            }
	            jsonSave(file);
	        }
	    }
	    else if (src == windowMenu.openMenu) {
	        JFileChooser chooser = new JFileChooser();
	        chooser.setFileFilter(new FileNameExtensionFilter("Budget JSON (*.json)", "json"));
	        int ret = chooser.showOpenDialog(this);
	        if (ret == JFileChooser.APPROVE_OPTION) {
	            File file = chooser.getSelectedFile();
	            jsonOpen(file);
	        }
	    }
	    // … autres actions …
}*/