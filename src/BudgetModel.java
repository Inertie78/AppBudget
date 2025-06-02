
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class BudgetModel {
	
	String day;
	String month;
	String year;

	private ArrayList<String> dateList;
	private ArrayList<String> libelleList;
	private ArrayList<String> creditList;
	private ArrayList<String> debitList;
	
	public String accountCredit = "150";
	public String accountDebit = "100";
	public String accountSolde = "50";
	
	public final Font tahomaFont12 = new Font("Tahoma", Font.PLAIN, 12);
	public final Font tahomaFont14 = new Font("Tahoma", Font.PLAIN, 14);
	public final Color colorSelect = Color.cyan;
	
	public BudgetModel() {
		dateList = new ArrayList<String>();
		libelleList = new ArrayList<String>();
		creditList = new ArrayList<String>();
		debitList = new ArrayList<String>();
	}
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	 
	public List<String> getDate() {
		return this.dateList;
	}
	
	public void setDay(String value) {
        day = value;
    }
	
	public void setMonth(String value) {
		
        month = value;
    }
	
	public void setYear(String value) {
        year = value;
    }


	public void setDate() {
		String date = day + " " + month + " " + year;
	    this.dateList.add(date);
	}
	
	public List<String> getLibelle() {
		return this.libelleList;
	}

	public void setLibelle(String libelle) {
	    this.libelleList.add(libelle);
	}
	
	public List<String> getCredit() {
	    return this.creditList;
	}
	
	public void setCredit(String credit) {
	    this.creditList.add(credit);
	}
	
	public List<String> getDedit() {
	    return this.debitList;
	}
	
	public void setDedit(String debit) {
	    this.debitList.add(debit);
	}
	
	public void removeItem(int index) {
		dateList.remove(index);
		libelleList.remove(index);
		creditList.remove(index);
		debitList.remove(index);	
	}
	
	public String getAccountCredit() {
	    return this.accountCredit;
	}
	
	public void setAccountCredit(String accountCredit) {
	    this.accountCredit = accountCredit;
	}
	
	public String getAccountDebit() {
	    return this.accountDebit;
	}
	
	public void setAccountDebit(String accountDebit) {
	    this.accountDebit = accountDebit;
	}
	
	public String getAccountSolde() {
	    return this.accountSolde;
	}
	
	public void serAccountSolde(String accountSolde) {
	    this.accountSolde = accountSolde;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
	    support.addPropertyChangeListener(listener);
	}
}