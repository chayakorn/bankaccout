package int101.banking;

import int101.base.Person;
import java.math.BigDecimal;

public class BankAccount {
    private static int nextAccountNo;
    private final int accountNo;
    private final String accountName;
    private final Person accountOwner;
    public AccountHistory history;
    private BigDecimal balance;

    public BankAccount(String accountName, Person accountOwner) {
        this.accountNo = nextAccountNo++;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.balance = new BigDecimal(0);
        this.history = new AccountHistory(0);
    }

    /* ToDo: 
       - call the above constructor to the the job.
       - use "firstname lastname" of accountOwner as the accountName;
    */
    public BankAccount(Person accountOwner) {
        this(accountOwner.getFirstname()+accountOwner.getLastname(),accountOwner);
        
        // ToDo: add your code here
         // ** remove this line
    }
    
    public BankAccount deposit(double amount) {
        if (amount<=0) return null;
        BigDecimal i =new BigDecimal(amount);
        balance = balance.add(new BigDecimal(amount));
        this.history.append(new AccountTransaction(TransactionType.DEPOSIT, i));
        return this;
    }
    
    public BankAccount withdraw(double amount) {
        if (amount<=0) return null;
        if (balance.doubleValue()<amount) return null;
        BigDecimal i = new BigDecimal(amount);
        balance = balance.subtract(new BigDecimal(amount));
        this.history.append(new AccountTransaction(TransactionType.WITHDRAW, i));
        return this;
    }
    
    /* ToDo:
       - check if true account is not null first 
       - try withdraw from this account first (call withdraw()); if fails, return null.
       - deposit to the other account (call deposit()); if fails, return null.
       - if everything is ok, return this (for method chaining).
    */
    public BankAccount transferTo(BankAccount to, double amount) {
        if(to==null){
            return null;}
        if(withdraw(amount)==null){
            return null;
        }else if(deposit(amount)==null){
            return null;
        }
        withdraw(amount);
        BigDecimal i =new BigDecimal(amount);
        this.history.append(new AccountTransaction(TransactionType.TRANSFER_OUT,i));
        to.deposit(amount);
        to.history.append(new AccountTransaction(TransactionType.TRANSFER_IN,i));
        return this;
    }

    public Person getAccountOwner() { return accountOwner; }

    @Override
    public String toString() {
        return "BankAccount[" + accountNo + ":" + accountName + "=" + balance + ']';
    }
    
}
