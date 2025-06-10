public class Account {
	private String date;
	private String libelle;
		
	private float credit, debit;

	public Account(String date, String libelle, float credit, float debit) {
		this.date = date;
		this.libelle = libelle;
		this.credit = credit;
		this.debit = debit;
    }
	
    public String getDate() { return this.date; }
    public String getLibelle() { return this.libelle; }
    
    public float getCredit() { return this.credit; }
    public float getDebit() { return this.debit; }
}