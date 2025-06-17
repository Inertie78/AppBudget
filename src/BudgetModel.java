import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

public class BudgetModel extends AbstractTableModel {
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	private static final long serialVersionUID = 1L;
	
	//déclaration des listes d'account
	private final List<Account> accountList;
	private List<Account> filteredData;
	
	//déclaration des variables String qui seront utilisées dans la classe
	private String day, month, year, date, libelle;
	//déclaration des variables float qui seront utilisées dans la classe 
	private Float soldeCredit, soldeDebit, solde;    
	
	public BudgetModel() {
		
		this.accountList = new ArrayList<Account>();
		this.filteredData = new ArrayList<Account>();
	}
     // crée la JTable en donnant un nom au colonnes 
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
	 
	 //fonction pour la récéption des valeurs selon la saisie utilisateur dans le champs montant
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
	
	 //empêche l'édition des lignes saisies dans la JTable
	 @Override
	 public boolean isCellEditable(int row, int col) {
		 return col != 0 && col != 1 && col != 2 && col != 3 ;
	 }

	//retourne le nombre de cellule addichée
	@Override
	public int getRowCount() {                                     
		
		return filteredData.size();
	}

	//retourne 5 pour définir le nombre de colonne (qui est fixe)
	@Override
	public int getColumnCount() {
		
		return 5;
	}

	//ajoute une ligne dans la JTable et notifie la nouvelle ligne via une des fontions "fireTable"
	public void addEntry( String libelle,  Float credit, Float debit) {
		String date = day + " " + month + " " + year;
		Account account = new Account(date, libelle, credit, debit);
		
		this.accountList.add(account);
		this.filteredData = this.accountList;
	
	    fireTableRowsInserted(this.filteredData.size() - 1, this.filteredData.size() - 1);
	    
	    sumCalculation(true, this.filteredData);        
	 }
	
	public void clearTable() {
		//permet de supprimer les lignes de la Table
		this.accountList.clear();
		this.filteredData = this.accountList;
	
		fireTableRowsInserted(this.filteredData.size() - 1, this.filteredData.size() - 1);
		 //passe la fonction d'addition, sans mettre à jour le solde total
		sumCalculation(true, this.filteredData);

	}
	
	public void removeRow(int index) {
	    if (index >= 0 && index < this.filteredData.size()) {			
	    	//Supprime une  ligne du tableau et supprime un account de accountList et informe des changements            
	    	this.accountList.remove(this.filteredData.get(index));
	    	
	    	this.filteredData = this.accountList;                         
	    	
	    	sumCalculation(true, this.filteredData);
	    	
	        fireTableRowsDeleted(index, index);
	        
	        applyFilters(this.date, this.libelle);
	    }
	}
	
	//fonction qui filte les lignes de la Table en fonction du Combobox des dates
	public Set<String> getFilteredDates(String date) {
		this.date = date;
	    return this.filteredData.stream()
	        .filter(row -> date == null || date.equals("Select date") || row.getDate().equals(date))
	        .map(row -> (String) row.getDate())
	        .collect(Collectors.toSet());
	}

	//fonction qui filte les lignes de la Table en fonction du Combobox des Libellés
	public Set<String> getFilteredLibelles(String libelle) {
		this.libelle = libelle;
	    return this.filteredData.stream()
	        .filter(row -> libelle == null || libelle.equals("Select libellé") || row.getLibelle().equals(libelle))
	        .map(row -> (String) row.getLibelle())
	        .collect(Collectors.toSet());
	}
	
	//Applique le filtre et crée un objet account qui contient le résultat des filtres 
	//Fait la somme des totaux crédits et débits du nouvel objet et transmet le changement
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
 
    	// boucle de calcul des sommes de l'objet account
        for (Account account : accList) {
        	// ajoute pour chaque ligne affichée le montant crédit de la ligne
        	this.soldeCredit += account.getCredit();
        	// ajoute pour chaque ligne affichée le montant débit de la ligne
        	this.soldeDebit += account.getDebit();
        }
        
        //le solde n'est mis à jour que si le contenu de la table n'est pas filté
        if(boolSolde) {                                               
        	
        	this.solde = this.soldeCredit - this.soldeDebit;
        	this.support.firePropertyChange("Solde", oldSolde, this.solde);
        }
        
        this.support.firePropertyChange("Solde Credit", oldSoldeCredit, this.soldeCredit);
        this.support.firePropertyChange("Solde Debit", oldSoldeDebit,  this.soldeDebit);		
		
	}
	
	//série de fonction qui servent à retourner ou à modifier les valeurs des variables voulues
	
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
	
	//Fonction qui va permettre d'enregistrer des listener à qui les firePropertyChange 
	//Vont être envoyés
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);                          
    }
}