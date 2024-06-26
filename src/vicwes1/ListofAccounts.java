package vicwes1;

import java.io.Serializable;
import java.util.ArrayList;

public class ListofAccounts implements Serializable {

    // Skapa ett objct med kontonummer, saldo, kontotyp och ränta i String.
    private final int accountNr;
    private final double balance,interest;
    private final String accountType;
    ArrayList<String> time;

    public ListofAccounts(int accountNr, double balance, String accountType, double interest, ArrayList<String> time) {
        this.accountNr = accountNr;
        this.balance = balance;
        this.accountType = accountType;
        this.interest = interest;
        this.time = time;
    }

    public int getAccountNr() {
        return accountNr;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public ArrayList<String> getTime() {
        return time;
    }

    @Override
    public String toString() {
        return
                "{accountNr='" + accountNr + '\'' +
                ", balance='" + balance + '\'' +
                ", accountType='" + accountType + '\'' +
                ", interest='" + interest + '\'' +
                ", time=" + time +
                '}';
    }
}
