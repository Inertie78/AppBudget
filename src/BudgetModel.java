import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

public class BudgetModel extends AbstractTableModel {
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	private static final long serialVersionUID = 1L;
	
	private final List<Account> accountList;
	
	private String day, month, year;
	
	private float credit, debit, soldeCredit, soldeDebit, solde;
	
	private List<Account> filteredData;
	

	public BudgetModel() {
		
		accountList = new ArrayList<Account>();
		filteredData = new ArrayList<Account>();
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
		Account account = filteredData.get(row);
		Float credit = account.getCredit();
		Float debit = account.getDebit();
		if(credit == 0) {credit = null;}
		if(debit == 0) {debit = null;}
		
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
		return filteredData.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	public void addEntry( String libelle,  Float credit, Float debit) {
		if(accountList.isEmpty()) {
			soldeCredit = credit;
			soldeDebit = debit;
			solde = this.soldeCredit - this.soldeDebit;
		}else {
			Account account = accountList.get(accountList.size() - 1);
			soldeCredit = account.getSoldeCredit() + credit;
			soldeDebit = account.getSoldeDebit() + debit;
			solde = account.getSolde() + (credit - debit);
		}
		
		String date = day + " " + month + " " + year;
		Account account = new Account(date, libelle, credit, debit, soldeCredit, soldeDebit, solde);
		
		accountList.add(account);
		filteredData = accountList;
	
	    fireTableRowsInserted(filteredData.size() - 1, filteredData.size() - 1);
	    
	    Float old = this.credit;
	    
        this.credit = credit;
        support.firePropertyChange("Credit", old, this.credit);
        
        old = this.debit;
        this.debit = debit;
        support.firePropertyChange("Debit", old,  this.debit);
        
	 }
	
	public void removeRow(int index) {
	    if (index >= 0 && index < accountList.size()) {
	    	
	    	//Mise à jour du compte
	    	Account accountRemove = accountList.get(index);
	    	Float soldeCreditRemove = accountRemove.getSoldeCredit() + credit;
	    	Float soldeDebitRemove = accountRemove.getSoldeDebit() + debit;

			Account acc = accountList.get(accountList.size() - 1);
			
			Float soldeCredit = acc.getSoldeCredit() - soldeCreditRemove;
			Float soldeDebit = acc.getSoldeDebit() - soldeDebitRemove;
			Float solde = acc.getSolde() - (credit - debit);
			
			acc.setSoldeCredit(soldeCredit);
			acc.setSoldeDebit(soldeDebit);
			acc.setSolde(solde);

			//Delete ligne du tableau et delete account de accountList
	    	accountList.remove(index);
	    	filteredData = accountList;
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
	
	public Set<String> getFilteredDates(String date) {
	    return filteredData.stream()
	        .filter(row -> date == null || date.equals("Select date") || row.getDate().equals(date))
	        .map(row -> (String) row.getDate())
	        .collect(Collectors.toSet());
	}


	public Set<String> getFilteredLibelles(String libelle) {
	    return filteredData.stream()
	        .filter(row -> libelle == null || libelle.equals("Select libellé") || row.getLibelle().equals(libelle))
	        .map(row -> (String) row.getLibelle())
	        .collect(Collectors.toSet());
	}
	
	public void applyFilters(String date, String label) {
        filteredData = accountList.stream()
            .filter(row ->
                (date == null || date.equals("Select date") || row.getDate().equals(date)) &&
                (label == null || label.equals("Select libellé") || row.getLibelle().equals(label))
            )
            .toList();     
        fireTableDataChanged();
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
		if (!filteredData.isEmpty()) {
			Account account = filteredData.get(filteredData.size() - 1);
			value =  account.getSoldeCredit();
        }
		return value;
    }
	
	public String getDate() {
		String date = "";
		if (!filteredData.isEmpty()) {
			Account account = filteredData.get(filteredData.size() - 1);
			date =  account.getDate();
        }
		return date;
	}
	
	public String getLibelle() {
		String libelle = "";
		if (!filteredData.isEmpty()) {
			Account account = filteredData.get(filteredData.size() - 1);
			libelle =  account.getLibelle();
        }
		return libelle;
	}
  
	public Float getAccountDebit(){
		Float value = 0.0f;
		if (!filteredData.isEmpty()) {
			Account account = filteredData.get(filteredData.size() - 1);
			value =  account.getSoldeDebit();
        }
		return value;
    }
    
	public Float getAccountSolde(){
		Float value = 0.0f;
		if (!accountList.isEmpty()) {
			Account ac = filteredData.get(filteredData.size() - 1);
			value =  ac.getSolde();
        }
		return value;	
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}