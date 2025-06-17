import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

public class BudgetModel extends AbstractTableModel {
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	private static final long serialVersionUID = 1L;
	
	private final List<Account> accountList;        //déclaration des listes d'account
	private List<Account> filteredData;
	
	private String day, month, year, date, libelle;  //déclaration des variables String qui seront utilisées dans la classe
	private Float soldeCredit, soldeDebit, solde;    //déclaration des variables float qui seront utilisées dans la classe 
	
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
	 // Elle permet de renseigner le bon champs en retournant via switch les valeurs dans l'objet account
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
	 public boolean isCellEditable(int row, int col) {            //empêche l'édition des lignes saisies dans la JTable
		 return col != 0 && col != 1 && col != 2 && col != 3 ;
	 }

	@Override
	public int getRowCount() {                                    //retourne le nombre de cellule addichée  
		
		return filteredData.size();
	}

	@Override
	public int getColumnCount() {                                //retourne 5 pour définir le nombre de colonne (qui est fixe)
		
		return 5;
	}

	public void addEntry( String libelle,  Float credit, Float debit) {		//ajoute une ligne dans la JTable et notifie la nouvelle ligne via une des fontions "fireTable"
		String date = day + " " + month + " " + year;
		Account account = new Account(date, libelle, credit, debit);
		
		this.accountList.add(account);
		this.filteredData = this.accountList;
	
	    fireTableRowsInserted(this.filteredData.size() - 1, this.filteredData.size() - 1);
	    
	    sumCalculation(true, this.filteredData);        
	 }
	
	public void clearTable() {
		this.accountList.clear();                                            //permet de supprimer les lignes de la Table
		this.filteredData = this.accountList;
	
		fireTableRowsInserted(this.filteredData.size() - 1, this.filteredData.size() - 1);
		sumCalculation(true, this.filteredData);                  //passe la fonction d'addition, sans mettre à jour le solde total

	}
	
	public void removeRow(int index) {
	    if (index >= 0 && index < this.filteredData.size()) {			
		
			                                                                  
	    	this.accountList.remove(this.filteredData.get(index));         //Supprime une  ligne du tableau et supprime un account de accountList et informe des changements
	    	
	    	this.filteredData = this.accountList;                         
	    	
	    	sumCalculation(true, this.filteredData);
	    	
	        fireTableRowsDeleted(index, index);
	        
	        applyFilters(this.date, this.libelle);
	    }
	}
	
	public Set<String> getFilteredDates(String date) {                    //fonction qui filte les lignes de la Table en fonction du Combobox des dates
		this.date = date;
	    return this.filteredData.stream()
	        .filter(row -> date == null || date.equals("Select date") || row.getDate().equals(date))
	        .map(row -> (String) row.getDate())
	        .collect(Collectors.toSet());
	}


	public Set<String> getFilteredLibelles(String libelle) {              //fonction qui filte les lignes de la Table en fonction du Combobox des Libellés
		this.libelle = libelle;
	    return this.filteredData.stream()
	        .filter(row -> libelle == null || libelle.equals("Select libellé") || row.getLibelle().equals(libelle))
	        .map(row -> (String) row.getLibelle())
	        .collect(Collectors.toSet());
	}
	
	public void applyFilters(String date, String libelle) {             //applique le filtre et crée un objet account qui contient le résultat des filtres 
		this.filteredData = this.accountList.stream()                   //fait la somme des totaux crédits et débits du nouvel objet et transmet le changement 
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
 
        for (Account account : accList) {                             // Fonction de calcul des sommes de l'objet account
        	this.soldeCredit += account.getCredit();                  // ajoute pour chaque ligne affichée le montant crédit de la ligne et en fait une nouvelle variable
        	this.soldeDebit += account.getDebit();	                  // ajoute pour chaque ligne affichée le montant débit de la ligne et en fait une nouvelle variable
        }
        
        if(boolSolde) {                                               //le solde n'est mis à jour que si le contenu de la table n'est pas filté
        	
        	this.solde = this.soldeCredit - this.soldeDebit;
        	this.support.firePropertyChange("Solde", oldSolde, this.solde);
        }
        
        this.support.firePropertyChange("Solde Credit", oldSoldeCredit, this.soldeCredit);
        this.support.firePropertyChange("Solde Debit", oldSoldeDebit,  this.soldeDebit);		
		
	}
	
	public Float getSoldeCredit() {                                   //série de fonction qui servent à retourner ou à modifier les valeurs des variables voulues
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
	
    public void addPropertyChangeListener(PropertyChangeListener listener) {       //fonction qui va permettre d'enregistrer des listener à qui les firePropertyChange 
        this.support.addPropertyChangeListener(listener);                          //vont être envoyés
    }
}