package vicwes1;

import java.util.ArrayList;

public abstract class Account {

    // I den här klassen skapar vi konton, med saldo,kontonummer,
    // lista med sparkonton, lista med kreditkonton och en lista med tid och datum.
    // Det är en abstact klass, men den är "parent class" till SavingsAccount och CreditAccout.
    // I och med den har en abstact metod (timeDate) så kommer båda subclass att implemetera samma metod.

    private double saldo;
    private int kontonummer;
    protected static int sistakontonummer = 1000;
    private double ranta;
    private String kontotyp;
    private boolean onceayear;
    private ArrayList<String> allTheTime = new ArrayList<>();


    public Account(double saldo, ArrayList<String> allTheTime) {
        this.saldo = saldo;
        this.allTheTime = allTheTime;
    }

    public Account(double saldo) {
        this.saldo = saldo;
    }
    public Account (){
    }

    public abstract String timeDate();

    public ArrayList<String> getAllTheTime() {
        return allTheTime;
    }

    public boolean isOnceayear() {
        return onceayear;
    }

    public void setOnceayear(boolean onceayear) {
        this.onceayear = onceayear;
    }

    public double getRanta() {
        return ranta;
    }
    public String getKontotyp() {
        return kontotyp;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getKontonummer() {
        return kontonummer;
    }
}