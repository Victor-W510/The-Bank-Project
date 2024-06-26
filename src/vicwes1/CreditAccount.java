package vicwes1;

import java.util.ArrayList;

public class CreditAccount extends Account {

    //Ränta (0,5% på eventuellt insatta pengar), skuldränta (7% på eventuellt lånade pengar)
    // Credit är precis som SavingsAccount en child class till Account.
    //Ränta (0,5% på eventuellt insatta pengar), skuldränta (7% på eventuellt lånade pengar)
    private final int kontonummer;
    private final String kontotyp = "Kreditkonto";
    private double ranta = 0.5;

    public CreditAccount(double saldo, ArrayList<String> allTheTime) {
        super(saldo, allTheTime);
        Account.sistakontonummer ++;
        kontonummer = Account.sistakontonummer;    }

    public CreditAccount(double saldo) {
        super(saldo);
        Account.sistakontonummer ++;
        kontonummer = Account.sistakontonummer;
    }
    @Override
    public int getKontonummer() {
        return kontonummer;
    }
    public String getKontotyp() {
        return kontotyp;
    }
    public double getRanta() {
        if (this.getSaldo() < 0)
            this.ranta = 7.0;

        return ranta;
    }
    @Override
    public String timeDate() {
        return null;
    }
    @Override
    public String toString() {
        return "saldo=" + getSaldo() + "kr" +
                ", kontonummer=" + getKontonummer() +
                ", kontotyp='" + kontotyp + '\'' + "\n" +
                '}';
    }
}

