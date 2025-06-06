public class Account {
	private String date;
	private String libelle;
	
	private float credit;
	private float debit;
	
	private float soldeCredit;
	private float soldeDebit;
	private float solde;
	
	public Account(String date, String libelle, 
					float credit, float debit) {
		this.date = date;
		this.libelle = libelle;
		this.credit = credit;
		this.debit = debit;
		
		setSoldeCredit();
		SetSoldeDebit();
		SetSolde();
    }

    public void setSoldeCredit() {
    	   soldeCredit += credit; 
    }

    public void SetSoldeDebit() {
    	soldeDebit += debit;
    }
    
    public void SetSolde() {
    	solde += (debit - soldeDebit);
    }
    
    public String getDate() { return date; }
    public String getLibelle() { return libelle; }
    
    public float getCredit() { return credit; }
    public float getDebit() { return debit; }
    
    public float getSoldeCredit() { return soldeCredit; }
    public float getSoldeDebit() { return soldeDebit; }
    public float getSolde() { return solde; }
}
