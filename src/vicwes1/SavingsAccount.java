package vicwes1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SavingsAccount extends Account{

    //Det här blir objectet för Sparkonto.
    //Här har vi ränta och  kontonummer men också onceaYear som innebär att man bara har ett gratis utag /år.
    // Efter första utaget så kommer den ändras till true.
    private final double ranta = 1.2;
    private final String kontotyp = "Sparkonto";
    private int kontonummer;
    private boolean onceayear = false;

    public SavingsAccount(double Saldo, ArrayList<String> allTheTime) {
        super(Saldo,allTheTime);
        Account.sistakontonummer++;
        kontonummer = Account.sistakontonummer;
    }

    public SavingsAccount(){
    }

    public SavingsAccount(double Saldo) {
        super(Saldo);
        Account.sistakontonummer++;
        kontonummer = Account.sistakontonummer;
    }

    @Override
    public String timeDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public double getRanta() {
        return ranta;
    }

    public String getKontotyp() {
        return kontotyp;
    }

    @Override
    public int getKontonummer() {
        return kontonummer;
    }

    public boolean isOnceayear() {
        return onceayear;
    }

    public void setOnceayear(boolean onceayear) {
        this.onceayear = onceayear;
    }

    @Override
    public String toString() {
        return
                "saldo=" + getSaldo() + "kr" +
                        ", ranta=" + ranta +
                        ", kontonummer=" + getKontonummer() +
                        ", kontotyp='" + kontotyp + '\'' + "\n" +
                        '}';
    }
}