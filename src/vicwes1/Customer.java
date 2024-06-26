package vicwes1;

import java.util.ArrayList;

public class Customer {

    // Customer är den andra klassen i hirarkin, och skapar en kund,
    // med för-och efternamn och personnummer.
    private String forNamn,efterNamn,persinNummer;
    private Account account;
    private final ArrayList<Account> allyouraccouts = new ArrayList<>();
    private final ArrayList<String> allTheTime = new ArrayList<>();


    public Customer(String forNamn, String efterNamn, String persinNummer) {
        this.forNamn = forNamn;
        this.efterNamn = efterNamn;
        this.persinNummer = persinNummer;
    }

    public ArrayList<Account> getAllyouraccouts() {
        return allyouraccouts;
    }

    public String getForNamn() {
        return forNamn;
    }

    public String getEfterNamn() {
        return efterNamn;
    }

    public String getPersinNummer() {
        return persinNummer;
    }

    public void setForNamn(String forNamn) {
        this.forNamn = forNamn;
    }

    public void setEfterNamn(String efterNamn) {
        this.efterNamn = efterNamn;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "forNamn='" + forNamn + '\'' +
                ", efterNamn='" + efterNamn + '\'' +
                ", persinNummer='" + persinNummer + '\'' +
                ", account=" + account +
                ", allyouraccouts=" + allyouraccouts +
                ", allTheTime=" + allTheTime +
                '}';
    }
}