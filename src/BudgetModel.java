import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

public class BudgetModel extends AbstractTableModel {
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	private static final long serialVersionUID = 1L;
	
	private final List<Account> accountList;
	private List<Account> filteredData;
	
	private String day, month, year, date, libelle;
	private Float soldeCredit, soldeDebit, solde;
	
	public BudgetModel() {
		
		//Crée une liste de transactions
		this.accountList = new ArrayList<Account>();
		//Une copie avec filtre de accountlist
		this.filteredData = new ArrayList<Account>();
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
		Account account = this.filteredData.get(row);
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

	//Crée une nouvelle transaction et l'ajoute à la liste des transactions
	public void addEntry( String libelle,  Float credit, Float debit) {		
		String date = day + " " + month + " " + year;
		Account account = new Account(date, libelle, credit, debit);
		
		this.accountList.add(account);
		this.filteredData = this.accountList;
		
		//Mets à jour la table
	    fireTableRowsInserted(this.filteredData.size() - 1, this.filteredData.size() - 1);
	    
	    //Calcule les soldes de la table
	    sumCalculation(true, this.filteredData);        
	 }
	
	//Vide la table
	public void clearTable() {
		this.accountList.clear();
		this.filteredData = this.accountList;
	
		fireTableRowsInserted(this.filteredData.size() - 1, this.filteredData.size() - 1);
		sumCalculation(true, this.filteredData);

	}
	
	//Supprime une ligne de la table
	public void removeRow(int index) {
	    if (index >= 0 && index < this.filteredData.size()) {			
		
			//Delete ligne du tableau et delete account de accountList
	    	this.accountList.remove(this.filteredData.get(index));
	    	
	    	this.filteredData = this.accountList;
	    	
	    	sumCalculation(true, this.filteredData);
	    	
	        fireTableRowsDeleted(index, index);
	        
	        applyFilters(this.date, this.libelle);
	    }
	}
	
	//Filtre sur les dates
	public Set<String> getFilteredDates(String date) {
		this.date = date;
	    return this.filteredData.stream()
	        .filter(row -> date == null || date.equals("Select date") || row.getDate().equals(date))
	        .map(row -> (String) row.getDate())
	        .collect(Collectors.toSet());
	}

	//Filtre sur le libellé
	public Set<String> getFilteredLibelles(String libelle) {
		this.libelle = libelle;
	    return this.filteredData.stream()
	        .filter(row -> libelle == null || libelle.equals("Select libellé") || row.getLibelle().equals(libelle))
	        .map(row -> (String) row.getLibelle())
	        .collect(Collectors.toSet());
	}
	
	// Applique les filtres séléctionnés
	public void applyFilters(String date, String libelle) {
		this.filteredData = this.accountList.stream()
            .filter(row ->
                (date == null || date.equals("Select date") || row.getDate().equals(date)) &&
                (libelle == null || libelle.equals("Select libellé") || row.getLibelle().equals(libelle))
            )
            .toList();   
        
		//Mets à jour la table
        fireTableDataChanged();
        
        //Calcule les soldes de la table
        sumCalculation(false, this.filteredData);     
    }
	
	 //Calcule soldes
	private void sumCalculation(Boolean boolSolde, List<Account> accList) {
		
		Float oldSoldeCredit = this.soldeCredit;
        Float oldSoldeDebit = this.soldeDebit;
        
        Float oldSolde = this.solde;
        
        soldeCredit = 0.0f;
    	soldeDebit = 0.0f;
 
        for (Account account : accList) {
        	this.soldeCredit += account.getCredit();
        	this.soldeDebit += account.getDebit();	
        }
        
        if(boolSolde) {
        	
        	this.solde = this.soldeCredit - this.soldeDebit;
        	this.support.firePropertyChange("Solde", oldSolde, this.solde);
        }
        
        this.support.firePropertyChange("Solde Credit", oldSoldeCredit, this.soldeCredit);
        this.support.firePropertyChange("Solde Debit", oldSoldeDebit,  this.soldeDebit);		
		
	}
	
	public Float getSoldeCredit() {
        return this.soldeCredit;
    }
	
	public Float getSoldeDebit() {
        return this.soldeDebit;
    }
	
	public Float getSolde() {
        return this.solde;
    }

	public void setDay(String value) {
		this.day = value;
    }
	
	public void setMonth(String value) {	
		this.month = value;
    }
	
	public void setYear(String value) {
		this.year = value;
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
	
	public String getDate() {
		String date = "";
		if (!this.filteredData.isEmpty()) {
			Account account = this.filteredData.get(this.filteredData.size() - 1);
			date =  account.getDate();
        }
		return date;
	}
	
	public String getLibelle() {
		String libelle = "";
		if (!this.filteredData.isEmpty()) {
			Account account = this.filteredData.get(this.filteredData.size() - 1);
			libelle =  account.getLibelle();
        }
		return libelle;
	}
	
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}