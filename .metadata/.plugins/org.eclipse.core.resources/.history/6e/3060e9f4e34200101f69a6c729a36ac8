import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import javax.swing.table.AbstractTableModel;

public class BudgetModel extends AbstractTableModel {
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	private static final long serialVersionUID = 1L;
	
	private final List<Account> accounttList;
	
	private String day, month, year;
	
	private float credit, debit, soldeCredit, soldeDebit, solde;
	
	public final Font tahomaFont12 = new Font("Tahoma", Font.PLAIN, 12);
	public final Font tahomaFont14 = new Font("Tahoma", Font.PLAIN, 14);
	public final Color colorSelect = Color.cyan;
	
	public BudgetModel() {
		
		accounttList = new ArrayList<Account>();
	}

	 @Override
	 public String getColumnName(int col) {
	    return switch (col) {
	        case 0 -> "Date";
	        case 1 -> "Libellé";
	        case 2 -> "Crédit";
	        case 3 -> "Débit";
	        case 4 -> "Action";
	        default -> "";
	    };
	 }
	 
	@Override
    public Object getValueAt(int row, int col) {
		Account account = accounttList.get(row);
		Float credit = account.getCredit();
		Float debit = account.getDebit();
		if(credit == 0) {
			credit = null;
		}else {
			debit = null;
		}
        return switch (col) {
            case 0 -> account.getDate();
            case 1 -> account.getLibelle();
            case 2 -> credit;
            case 3 -> debit;
            default -> null;
        };
    }
	
	 @Override
	 public boolean isCellEditable(int row, int col) {
		 return col != 0 && col != 1 && col != 2 && col != 3 ;
	 }

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return accounttList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	public void addEntry( String libelle,  Float credit, Float debit) {
		if(accounttList.isEmpty()) {
			soldeCredit = credit;
			soldeDebit = debit;
			solde = this.soldeCredit - this.soldeDebit;
		}else {
			Account acc = accounttList.get(accounttList.size() - 1);
			soldeCredit = acc.getSoldeCredit() + credit;
			soldeDebit = acc.getSoldeDebit() + debit;
			solde = acc.getSolde() + (credit - debit);
		}
		
		String date = day + "." + month + "." + year;
		Account account = new Account(date, libelle, credit, debit, soldeCredit, soldeDebit, solde);
		
		accounttList.add(account);
		
	    fireTableRowsInserted(accounttList.size() - 1, accounttList.size() - 1);
	    
	    Float old = this.credit;
	    
        this.credit = credit;
        support.firePropertyChange("Credit", old, this.credit);
        
        old = this.debit;
        this.debit = debit;
        support.firePropertyChange("Debit", old,  this.debit);
        
	 }
	
	public void removeRow(int index) {
	    if (index >= 0 && index < accounttList.size()) {
	    	
	    	//Mise à jour du compte
	    	Account accountRemove = accounttList.get(index);
	    	Float soldeCreditRemove = accountRemove.getSoldeCredit() + credit;
	    	Float soldeDebitRemove = accountRemove.getSoldeDebit() + debit;

			Account acc = accounttList.get(accounttList.size() - 1);
			
			Float soldeCredit = acc.getSoldeCredit() - soldeCreditRemove;
			Float soldeDebit = acc.getSoldeDebit() - soldeDebitRemove;
			Float solde = acc.getSolde() - (credit - debit);
			
			acc.setSoldeCredit(soldeCredit);
			acc.setSoldeDebit(soldeDebit);
			acc.setSolde(solde);

			//Delete ligne du tableau et delete account de accountList
	    	accounttList.remove(index);
	        fireTableRowsDeleted(index, index);
	        
	        Float old = this.soldeCredit;
		    
	        this.soldeCredit = soldeCredit;
	        support.firePropertyChange("Solde Credit", old, this.soldeCredit);
	        
	        old = this.soldeDebit;
	        this.soldeDebit = soldeDebit;
	        support.firePropertyChange("Solde Debit", old,  this.soldeDebit);
	        
	        old = this.solde;
	        this.solde = solde;
	        support.firePropertyChange("Solde", old,  this.solde);
	    }
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
	
	public String getDay() {
		return this.day;
    }
	
	public String getMonth() {	
		return this.month;
    }
	
	public String getYear() {
        return this.year;
    }

	public Float getAccountCredit(){
        Float value = 0.0f;
		if (!accounttList.isEmpty()) {
			Account account = accounttList.get(accounttList.size() - 1);
			value =  account.getSoldeCredit();
        }
		return value;
    }
	
	public String getDate() {
		String date = "";
		if (!accounttList.isEmpty()) {
			Account account = accounttList.get(accounttList.size() - 1);
			date =  account.getDate();
        }
		return date;
	}
  
	public Float getAccountDebit(){
		Float value = 0.0f;
		if (!accounttList.isEmpty()) {
			Account account = accounttList.get(accounttList.size() - 1);
			value =  account.getSoldeDebit();
        }
		return value;
    }
    
	public Float getAccountSolde(){
		Float value = 0.0f;
		if (!accounttList.isEmpty()) {
			Account ac = accounttList.get(accounttList.size() - 1);
			value =  ac.getSolde();
        }
		return value;	
    }
	
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}