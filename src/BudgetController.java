
public class BudgetController {
	
	private BudgetModel model;
	
	public BudgetController(BudgetModel model) {
        this.model = model;
    }
	
	public void addTransaction(String libelle,  Float credit, Float debit) {
        // ici tu pourrais ajouter validation des données
        model.addEntry(libelle, credit, debit);
    }
	
	public void setDay(String value) {
        model.setDay(value);
    }
	
	public void setMonth(String value) {	
        model.setMonth(value);
    }
	
	public void setYear(String value) {
        model.setYear(value);
    }
}