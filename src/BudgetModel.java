import java.awt.Color;
import java.awt.Font;
import java.util.*;

import javax.swing.table.AbstractTableModel;

public class BudgetModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private final List<Account> accounttList;
	
	private String day, month, year;
	
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
		String date = day + "." + month + "." + year;
		Account account = new Account(date, libelle, credit, debit);
		accounttList.add(account);
		
	    fireTableRowsInserted(accounttList.size() - 1, accounttList.size() - 1);
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
	
	public Float getAccountCredit(){
        Float value = 0.0f;
		if (!accounttList.isEmpty()) {
			Account ac = accounttList.get(accounttList.size());
			value =  ac.getSoldeCredit();
        }
		return value;
    }
  
	public Float getAccountDebit(){
		Float value = 0.0f;
		if (!accounttList.isEmpty()) {
			Account ac = accounttList.get(accounttList.size());
			value =  ac.getSoldeDebit();
        }
		return value;
    }
    
	public Float getAccountSolde(){
		Float value = 0.0f;
		if (!accounttList.isEmpty()) {
			Account ac = accounttList.get(accounttList.size());
			value =  ac.getSolde();
        }
		return value;	
    }
}