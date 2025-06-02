
public class BudgetController {
	
	private BudgetModel model;
	
	public BudgetController(BudgetModel model) {
        this.model = model;
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

    public void setDate() {
        model.setDate();
    }

    public void setLibelle(String value) {
        model.setLibelle(value);
    }
    
    public void setCredit(String value) {
        model.setCredit(value);
    }
    
    public void setDedit(String value) {
        model.setDedit(value);
    }
}