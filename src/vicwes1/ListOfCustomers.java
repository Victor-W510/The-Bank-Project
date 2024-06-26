package vicwes1;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfCustomers implements Serializable{

    // Skapa variabler som kommer hålla för-och efternamn,pressonnumme
    // och en lista med ListofAccounts objekt
    private final String pno,fName,lName;
    protected ArrayList<ListofAccounts> list;

    public ListOfCustomers(String pno, String fName, String lName, ArrayList<ListofAccounts> list) {
        this.pno = pno;
        this.fName = fName;
        this.lName = lName;
        this.list = list;
    }

    public String getPno() {
        return pno;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public ArrayList<ListofAccounts> getList() {
        return list;
    }

    @Override
    public String toString() {
        return
                "{pno='" + pno + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", list=" + list + '}';
    }
}
