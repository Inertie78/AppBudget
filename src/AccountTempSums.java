//classe qui 



public class AccountTempSums {
    private final float totalCredit;
    private final float totalDebit;
    

    public AccountTempSums(float totalCredit, float totalDebit) {
        this.totalCredit = totalCredit;
        this.totalDebit  = totalDebit;
       
    }

    public float getTotalCredit() { return totalCredit; }
    public float getTotalDebit()  { return totalDebit;  }
   
}
