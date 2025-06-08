import java.awt.Color;
import java.awt.Font;
import java.util.Set;

public class BudgetController {
	
	private BudgetModel model;
	
	public final Font tahomaFont12 = new Font("Tahoma", Font.PLAIN, 12);
	public final Font tahomaFont14 = new Font("Tahoma", Font.PLAIN, 14);
	public final Color colorSelect = Color.cyan;
	public final Color colorColumn_01 = new java.awt.Color(80, 80, 80);
	public final Color colorColumn_02 = new java.awt.Color(60, 60, 60);
	
	
	public BudgetController(BudgetModel model) {
        this.model = model;    
    }
	
	public void addEntry(String libelle,  Float credit, Float debit) {
        // ici tu pourrais ajouter validation des données
        model.addEntry(libelle, credit, debit);
    }
	
	public void removeRow(int index) {
	    model.removeRow(index);
	}
	
	 public void applyFilters(String date, String label) {
	        model.applyFilters(date, label);
	 }
	 
	public Set<String> getFilteredDates(String date) {
	    return model.getFilteredDates(date);
	}

	public Set<String> getFilteredLibelles(String libelle) {
	    return model.getFilteredLibelles(libelle);
	}

	public String getDay() {
        return model.getDay();
    }
	
	public void setDay(String value) {
        model.setDay(value);
    }
	
	public String getMonth() {
        return model.getMonth();
    }
	
	public void setMonth(String value) {	
        model.setMonth(value);
    }
	
	public String getYear() {
        return model.getYear();
    }
	
	public void setYear(String value) {
        model.setYear(value);
    }
	
	public int getRowCount() {
        return model.getRowCount();
    }
	
	public int getColumnCount() {
        return model.getColumnCount();
    }
	
	public Object getValueAt(int row, int col) {
        return model.getValueAt(row, col);
    }
	
	public Float getAccountCredit() {
        return model.getAccountCredit();
    }
	
	public Float getAccountDebit() {
        return model.getAccountDebit();
    }
	
	public Float getAccountSolde() {
        return model.getAccountSolde();
    }
	
	public String getDate() {
        return model.getDate();
    }
	
	public String getLibelle() {
        return model.getLibelle();
    }
}