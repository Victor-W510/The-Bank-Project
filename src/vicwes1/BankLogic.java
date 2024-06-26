package vicwes1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class BankLogic {


    // Här Har Vi global NumberFormat för att vi kommer använda flera gånger om.
    // minArray är huvudpersonen i det här programmet. Det är här vi kommer lagra alla kunder.
    // Och en file, som vi kommer paara all kunder till.
    File file = new File("CustomerFile.ser");
    private final NumberFormat nF = NumberFormat.getCurrencyInstance(new Locale("sv","SE"));
    private final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private static final ArrayList<Customer> minArray = new ArrayList<>();


    public ArrayList<String> getAllCustomers() {
        // Här skapar vi en lista som vi sen lägger in alla kunder med deras respektive persinnummer,
        // för - och efternamn i den önskade ordningen.
        ArrayList<String> array = new ArrayList<>();

        for (Customer customer : minArray) {

            array.add(customer.getPersinNummer() + " " + customer.getForNamn() + " " + customer.getEfterNamn() + "\n");

        }
        return array;
    }
    public ArrayList getOneCustomer(String pNo) {
        // Skapar en lista som vi sen fyller med en kund.
        // Man Kan även använda sig av en vanlig String. Men jag valdse Array.
        ArrayList<String> string = new ArrayList<>();

        for (Customer customer : minArray) {
            if (customer.getPersinNummer().equals(pNo)) {
                String pno = String.valueOf(customer.getPersinNummer());
                String fornamn = String.valueOf(customer.getForNamn());
                String efternamn = String.valueOf(customer.getEfterNamn());
                string.add(pno);
                string.add(fornamn);
                string.add(efternamn);

            }
        }
        return string;
    }
    public void createCustomer(String name, String surname, String pNo) {

        //För att underlätta lite för oss själva så börjar vi med att skapa en
        // boolean type och sätter den till false och  x = 0. Det här gör vi för att
        // försäkra oss om at vi INTE har en kund med det pNo, och är så fallet så
        // skapar vi en kund med det pNo
        boolean answer = false;
        int x = 0;
        for (Customer customer : minArray) {
            if (customer.getPersinNummer().equals(pNo)){
                x++;
            }
        }
        if (x == 0) {
            Customer a = new Customer(name, surname, pNo);
            minArray.add(a);
            answer = true;
        }
        if(x == 1){
            JOptionPane.showMessageDialog(null, "Det finns redan en kund med det Personnummret",
                    "OJ DÅ!", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ArrayList<String> getCustomer(String pNo) {

        // Skapa en lista som vi sen samlar in alla information kopplad till angivet pNO
        // minArray -> rätt kund -> kundens alla sparkonton - > kundens alla kreditkonton


        ArrayList<String> a = new ArrayList<>();

        ArrayList<HelpGetCustomer> listofaccounts = new ArrayList<>();


        for (Customer customer : minArray) {

            if (customer.getPersinNummer().equals(pNo)) {
                // P Nummer      //för och efternamn

                String pno = String.valueOf(customer.getPersinNummer());
                String fornamn = String.valueOf(customer.getForNamn());
                String efternamn = String.valueOf(customer.getEfterNamn());
                String foroefternamn = pno + " " + fornamn + " " + efternamn + "\n";
                a.add(foroefternamn);


                /** Account */
                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {
                    //saldo
                    String d = String.valueOf(customer.getAllyouraccouts().get(j).getSaldo());
                    double dd = Double.parseDouble(d);

                    // kontonummer
                    int kontonummer = customer.getAllyouraccouts().get(j).getKontonummer();

                    //kontotyp
                    String kontotyp = String.valueOf(customer.getAllyouraccouts().get(j).getKontotyp());

                    //ränta
                    String ranta = String.valueOf(customer.getAllyouraccouts().get(j).getRanta());
                    double rantadouble = Double.parseDouble(ranta);

                    percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
                    String percentStr = percentFormat.format(rantadouble / 100);

                    listofaccounts.add(new HelpGetCustomer(kontonummer, nF.format(dd), kontotyp, percentStr));
                }

                listofaccounts.sort((o1, o2) -> Integer.compare(o1.accountNr(), o2.accountNr()));
                for (HelpGetCustomer helpGetCustomer : listofaccounts) {

                    int b = helpGetCustomer.accountNr();
                    String c = helpGetCustomer.accountType();
                    String d = helpGetCustomer.balance();
                    String e = helpGetCustomer.interest();
                    String f = b + " " + d + " " + c + " " + e;
                    a.add(f + "\n");
                }

            }

        }

        return a;


    }
    public void changeCustomerName(String name, String surname, String pNo) {

        // Börja med att sätta ret till false, för att sen gå igen om minArray
        // för att se om vi hittar någon kund med rätt pNo. Hittar vi en sån
        // kund så ska vi kolla om vi ska byta förnamnet, efternamnet,
        // båda  eller inget av dom.
        boolean ret = false;

        for (Customer account : minArray) {
            if (account.getPersinNummer().equals(pNo)) {

                if (name.isEmpty() && !surname.isEmpty()) {
                    account.setEfterNamn(surname);
                    ret = true;

                } else if (!name.isEmpty() && surname.isEmpty()) {
                    account.setForNamn(name);
                    ret = true;

                } else if (!name.isEmpty() && !surname.isEmpty()) {
                    account.setForNamn(name);
                    account.setEfterNamn(surname);
                    ret = false;

                } else {
                    ret = false;
                }
            }
        }

    }
    public void createSavingsAccount(String pNo) {
        // Börja med answer=-1 eftersom det är det vi vill svara om vi inte hittar kunden.
        // Finns kunden i banken så skapar vi ett Sparkonto till kunden och svart blir nu kontonummret.


        int answer = -1;
        for (Customer customer : minArray) {
            if (customer.getPersinNummer().equals(pNo)) {

                SavingsAccount newAccount = new SavingsAccount(0);
                int account = newAccount.getKontonummer();
                customer.getAllyouraccouts().add(newAccount);

                JOptionPane.showMessageDialog(null, account, "Ditt kontonummer", JOptionPane.INFORMATION_MESSAGE);

                answer = account;
            }
        }
    }
    public void getAccount(String pNo, int accountId) {

        // Här letar vi rätt på kunden i minArray, sen tittar
        // vi igenom kundens alla sparkonton och efter det kollar vi igenom alla kreditkonton
        // hittar vi inte det eftersökta kontonummret så svarar vi med null.

        //ArrayList<String> array = new ArrayList<>();
        int index = 0;
        for (Customer account : minArray) {

            if (account.getPersinNummer().equals(pNo)) {

                for (int j = 0; j < account.getAllyouraccouts().size(); j++) {

                    if (account.getAllyouraccouts().get(j).getKontonummer() == accountId) {
                        index = 1;
                        String konto = String.valueOf(account.getAllyouraccouts().get(j).getKontonummer() + " ,");
                        String saldo = account.getAllyouraccouts().get(j).getSaldo() + " kr ";
                        String kontotyp = String.valueOf(account.getAllyouraccouts().get(j).getKontotyp() + ',');
                        String ranta = account.getAllyouraccouts().get(j).getRanta() + " % ,";
                        String end = konto + " " + saldo + " " + kontotyp + " " + ranta;
                        JOptionPane.showMessageDialog(null, end, kontotyp, JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
            }
        }
        if (index == 0){
            JOptionPane.showMessageDialog(null, "Är du säker på att det här kontot tillhör dig?",
                    "!",JOptionPane.ERROR_MESSAGE);
        }

    }
    public void deposit(String pNo, int accountId, int amount)  {
        // Börjar med att sätta answer till false som default. Därefter söker vi igenom
        // listan med kunder -> listna med kundens sparkonton och sen listan med kreditkonton.
        // hittar vi kontot så sättervi in pengarna. och answer blir true.

        boolean answer = false;

        int index = 0;
        for (Customer customer : minArray)

            if (customer.getPersinNummer().equals(pNo)) {
                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {

                    if (customer.getAllyouraccouts().get(j).getKontonummer()
                            == accountId && amount > 0 &&
                            customer.getAllyouraccouts().get(j).getKontotyp().equals("Sparkonto")) {

                    index = 1;
                        double balance = customer.getAllyouraccouts().get(j).getSaldo();
                        double newBalance = balance + amount;
                        customer.getAllyouraccouts().get(j).setSaldo(newBalance);
                        JOptionPane.showMessageDialog(null, "Pengarna har satts in på kontot",
                                "Message!", JOptionPane.INFORMATION_MESSAGE);
                        SavingsAccount savings = new SavingsAccount();
                        String time = savings.timeDate()
                                + " " + nF.format(amount)
                                + " " + "Saldo:"
                                + " " + nF.format(newBalance)
                                + "\n";
                        customer.getAllyouraccouts().get(j).getAllTheTime().add(time);
                        answer = true;
                    }
                }

                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {
                    if (customer.getAllyouraccouts().get(j).getSaldo() + amount <= 5000
                            && customer.getAllyouraccouts().get(j).getKontonummer() == accountId
                            && customer.getAllyouraccouts().get(j).getKontotyp().equals("Kreditkonto")) {

                        index = 1;
                        double balance = customer.getAllyouraccouts().get(j).getSaldo();
                        double newBalance = balance + amount;
                        customer.getAllyouraccouts().get(j).setSaldo(newBalance);
                        JOptionPane.showMessageDialog(null, "Pengarna har satts in på kontot",
                                "Message!", JOptionPane.INFORMATION_MESSAGE);

                        SavingsAccount savings = new SavingsAccount();
                        String time = savings.timeDate()
                                + " " + amount
                                + " " + "Saldo:"
                                + " " + newBalance
                                + "\n";
                        customer.getAllyouraccouts().get(j).getAllTheTime().add(time);
                        answer = true;

                    }else if (customer.getAllyouraccouts().get(j).getSaldo() + amount > 5000
                            && customer.getAllyouraccouts().get(j).getKontonummer() == accountId) {

                        index = 1;
                        customer.getAllyouraccouts().get(j).setSaldo(5000);
                        SavingsAccount savings = new SavingsAccount();
                        String time = savings.timeDate()
                                + " " + amount
                                + "  " + "5000"
                                + "kr" + "\n";
                        customer.getAllyouraccouts().get(j).getAllTheTime().add(time);

                        JOptionPane.showMessageDialog(null, "Kortet kan bara innehålla 5000kr",
                                "Message!", JOptionPane.ERROR_MESSAGE);

                        answer = true;
                    }
                }
                if (index == 0 ){
                    JOptionPane.showMessageDialog(null, "Är du säker på att det här kontot tillhör dig?",
                            "!",JOptionPane.ERROR_MESSAGE);
                }
            }

    }
    public boolean withdraw(String pNo, int accountId, int amount) {
        // Börjar med att sätta answer till false som default. Därefter söker vi igenom
        // listan med kunder -> listna med kundens sparkonton och sen listan med kreditkonton.
        // hittar vi kontot så tar vi ut pengarna. och answer blir true.

        boolean answer = false;
        SavingsAccount savings = new SavingsAccount();
        int index = 0;

        for (Customer customer : minArray)
            if (customer.getPersinNummer().equals(pNo)) {

                /** Sparkonto */
                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {

                    /** Rätt kontonummer, Över 0 kr, Rätt kontotyp, Första uttaget */
                    if (customer.getAllyouraccouts().get(j).getKontonummer() == accountId
                            && amount > 0
                            && customer.getAllyouraccouts().get(j).getKontotyp().equals("Sparkonto")
                            && !customer.getAllyouraccouts().get(j).isOnceayear()) {
                        index =1;

                        double balancedouble = customer.getAllyouraccouts().get(j).getSaldo();
                        double newbalance = balancedouble - amount;

                        nF.setMaximumFractionDigits(2);
                        String SALDO = nF.format(amount);
                        String newSaldo = nF.format(newbalance);

                        if (newbalance >= 0) {
                            customer.getAllyouraccouts().get(j).setSaldo(newbalance);
                            customer.getAllyouraccouts().get(j).setOnceayear(true);

                            String time = savings.timeDate()
                                    + " -" + SALDO
                                    + " Saldo:"
                                    + " " + newSaldo
                                    + "\n";
                            customer.getAllyouraccouts().get(j).getAllTheTime().add(time);
                            answer = true;
                        }else JOptionPane.showMessageDialog(null, "Du har inte så mycket",
                                "Ops!", JOptionPane.INFORMATION_MESSAGE);

                        /** Rätt kontonummer, Över 0 kr, Rätt kontotyp, "Inte" första uttaget */
                    } else if (customer.getAllyouraccouts().get(j).getKontonummer() == accountId && amount > 0
                            && customer.getAllyouraccouts().get(j).isOnceayear()
                            && customer.getAllyouraccouts().get(j).getKontotyp().equals("Sparkonto")){
                        index =1;

                        double amount2 = amount + (amount * 0.02);
                        double balanceDouble = customer.getAllyouraccouts().get(j).getSaldo();
                        double newBalance = balanceDouble - amount2;

                        nF.setMaximumFractionDigits(2);
                        String SALDO = nF.format(amount2);
                        String newSaldo = nF.format(newBalance);

                        if (newBalance >= 0){
                            customer.getAllyouraccouts().get(j).setSaldo(newBalance);

                            String time = savings.timeDate()
                                    + " -" + SALDO
                                    + " Saldo: "
                                     + newSaldo
                                    + "\n";
                            customer.getAllyouraccouts().get(j).getAllTheTime().add(time);
                            answer = true;
                        }
                    }
                }
                /** Kreditkonto */
                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {

                    /** Mindre än -5000, Rätt kontotyp, Rätt kontonummer*/
                    if ((customer.getAllyouraccouts().get(j).getSaldo() - amount) >= -5000
                            && customer.getAllyouraccouts().get(j).getKontotyp().equals("Kreditkonto")
                            && customer.getAllyouraccouts().get(j).getKontonummer() == accountId) {
                        index =1;

                        double balanceDouble = customer.getAllyouraccouts().get(j).getSaldo();
                        double balance = balanceDouble - amount;
                        customer.getAllyouraccouts().get(j).setSaldo(balance);
                        nF.setMaximumFractionDigits(2); // Anger att vi vill ha max 1 decimal
                        String SALDO = nF.format(amount);
                        String newBalance = nF.format(balance);

                        String time = savings.timeDate() +
                                " -" + SALDO +
                                " Saldo: "
                                + newBalance
                                + "\n";
                        customer.getAllyouraccouts().get(j).getAllTheTime().add(time);
                        answer = true;

                        /** Rätt kontonummer, Rätt kontotyp*/
                    }else if (customer.getAllyouraccouts().get(j).getKontonummer() == accountId
                            && customer.getAllyouraccouts().get(j).getKontotyp().equals("Kreditkonto")) {
                        index =1;
                        //System.out.println(customer.getAllYourCedit().get(j).getSaldo() - amount + "3 ");
                        JOptionPane.showMessageDialog(null, "Minsta möjliga är 5000 kr!",
                                "Ops!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if (index == 0){
                    JOptionPane.showMessageDialog(null, "Är du säker på att det här kontot tillhör dig?",
                            "!",JOptionPane.ERROR_MESSAGE);
                }
            }

        return answer;
    }
    public String closeAccount(String pNo, int accountId) {

        // minArray -> kundens "plånbok"  -> Rätt konto -> Ta bort kontot
        // Här måste vi först söka igenom alla sparkonto och se om accoutId
        // matchar med något av dom kontona. Efter det kan vi kolla alla kreditkonton.

        String closedaccount = null;

        for (Customer customer : minArray)
            if (customer.getPersinNummer().equals(pNo)) {
                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {
                    if (customer.getAllyouraccouts().get(j).getKontonummer() == (accountId)
                            && customer.getAllyouraccouts().get(j).getKontotyp().equals("Sparkonto")) {
                        /** Sparkonto*/

                        // SALDO
                        String balance = String.valueOf(customer.getAllyouraccouts().get(j).getSaldo());
                        double balacneDouble = Double.parseDouble(balance);

                        //Kontotyp
                        String accountnummer = customer.getAllyouraccouts().get(j).getKontotyp();

                        // RÄNTA
                        String ranata = String.valueOf(customer.getAllyouraccouts().get(j).getRanta());
                        double rantaDouble = Double.parseDouble(ranata);
                        double add = (balacneDouble * rantaDouble) / 100;
                        String finalRanta = nF.format(add);

                        //NYA SALDO (-räntan)
                        double newsaldo = rantaDouble - add;
                        customer.getAllyouraccouts().get(j).setSaldo(newsaldo);

                        closedaccount = accountId
                                + " " + nF.format(balacneDouble)
                                + " " + accountnummer
                                + " " + finalRanta;
                        customer.getAllyouraccouts().remove(j);


                    }
                    /** Kreditkonto */
                    if (customer.getAllyouraccouts().get(j).getKontonummer() == (accountId)
                            && customer.getAllyouraccouts().get(j).getKontotyp().equals("Kreditkonto")) {

                        // SALDO
                        double balance = customer.getAllyouraccouts().get(j).getSaldo();

                        // Ranta
                        double getRanta = customer.getAllyouraccouts().get(j).getRanta();
                        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
                        String percentStr = String.valueOf(percentFormat.format(getRanta / 100));


                        if (balance >= 0) {

                            double ranta = (balance * 1.05);
                            customer.getAllyouraccouts().get(j).setSaldo(balance - ranta);
                            closedaccount = accountId
                                    + " " + nF.format(balance)
                                    + " " + customer.getAllyouraccouts().get(j).getKontotyp()
                                    + " " + nF.format(ranta);
                            customer.getAllyouraccouts().remove(j);

                        } else {

                            double ranta = (balance * 0.07);
                            customer.getAllyouraccouts().get(j).setSaldo(ranta);
                            closedaccount = accountId
                                    + " " + nF.format(balance)
                                    + " " + customer.getAllyouraccouts().get(j).getKontotyp()
                                    + " " + nF.format(ranta);
                            customer.getAllyouraccouts().remove(j);

                        }
                    }
                }
            }

        return closedaccount;
    }
    public void deleteCustomer(String pNo) {
        // Leta igenom minArray efter kunden, Hittar vi kunden så lägger
        // vi in pNo,för-och efternamn och "plånboken" med alla konton i en
        // lista. Sen tar vi bort kunden helt och hållet samtidigt som vi
        // svarar anropet (till den här metoden) med listan vi precis har skapat,
        // och om vi inte hittar någon kund så svarar vi bara med null.

        ArrayList<String> newArray = new ArrayList<>();             // Listan som ska returneras med information om kunden

        for (int i = 0; i < minArray.size(); i++) {                // Loop igenom listan med alla kunder
            //Listan på alla

            if (minArray.get(i).getPersinNummer().equals(pNo)) {        // Hitta EN specefic kund i arkiven

                String name =
                        minArray.get(i).getForNamn() +" " + minArray.get(i).getEfterNamn();
                newArray.add(pNo +" "+ name);

                for (int j = 0; j < minArray.get(i).getAllyouraccouts().size();) {
                    int kNumber = minArray.get(i).getAllyouraccouts().get(j).getKontonummer();
                    String answer = closeAccount(pNo, kNumber);
                    newArray.add(answer);
                }

                minArray.remove(i);
            }
        }
        if (newArray.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kunde inte hitta någon med det Perssonnummer",
                    "OPS! Något har går fel!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, newArray,
                    "Borttaget", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void createCreditAccount(String pNo) {
        // Börja med answer=-1 eftersom det är det vi vill svara om vi inte hittar kunden.
        // Finns kunden i banken så öppnar vi ett Kreditkonto till kunden och svart blir nu
        // kontonummret.
        int answer = -1;
        for (Customer customer : minArray) {

            if (customer.getPersinNummer().equals(pNo)) {

                CreditAccount newCredit = new CreditAccount(0);
                int account = newCredit.getKontonummer();
                customer.getAllyouraccouts().add(newCredit);

                JOptionPane.showMessageDialog(null, account, "Ditt kontonummer", JOptionPane.INFORMATION_MESSAGE);

                answer = account;
            }
        }
    }
    public void getTransactions(String pNo, int accountId) {
        // I den här metoden så öppnar vi en ArrayLista och vi sätter exists till false.
        // Därefter söker vi efter kunden i fråga med hjälp av kundens perssonnummer.
        // Efter det är klart så kontrollerar vi kundens alla sparkonton och kundens alla Kreditkonton,
        // för att se om något av dom matchar accountId.
        // Hittar vi konton så sätter vi exists till true.
        // Och sen hämta upp kontots alla uttag, tillsamans med datum och tid.
        ArrayList<String> arrayList = new ArrayList<>();
        int index = 0;
        for (Customer customer : minArray) {
            if (customer.getPersinNummer().equals(pNo)) {


                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {
                    if (customer.getAllyouraccouts().get(j).getKontonummer() == accountId) {
                        index = 1;
                        for (int i = 0; i < customer.getAllyouraccouts().get(j).getAllTheTime().size(); i++) {
                            arrayList.add(String.valueOf(customer.getAllyouraccouts().get(j).getAllTheTime().get(i)));
                            //arrayList.add("\n");
                        }
                    }
                }

            }
        }
        if (index == 0){
            JOptionPane.showMessageDialog(null, "Är du säker på att det här kontot tillhör dig?",
                    "!",JOptionPane.ERROR_MESSAGE);
        }else {
            //saveToFile();
            JTextArea textArea = new JTextArea();
            textArea.setText(arrayList.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(null, scrollPane, "Historik", JOptionPane.INFORMATION_MESSAGE);
        }


    }
    public void saveToFile(){

        // Vi sparar senaste sistakontonummret i en Integer, och skapar en lista
        // som lägger in alla kunder i efter att vi har förberett alla kunder för resan igenom en fil.
        // Därefter är vi redo att spara allt till en fil.

        int accountnr = Account.sistakontonummer;
        ArrayList<ListOfCustomers> saveList = new ArrayList<>();

        for (Customer account : minArray) {
            saveList.add(makeAList(account.getPersinNummer()));
        }
        try {
            FileOutputStream filOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(filOut);

            out.writeInt(accountnr);
            out.writeObject(saveList);

            out.close();
            filOut.close();
            JOptionPane.showMessageDialog(null, "Infromationen har sparats till en fil",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
        }catch (IOException e){

            JOptionPane.showMessageDialog(null, "Det gick inte att spara till filen, vänligen försök igen",
                        "Message", JOptionPane.ERROR_MESSAGE);

            e.printStackTrace();
        }



    }
    public void openFile (){

        // Skapa en lista som vi kan ladda in alla kunder i.
        // Sen öppnar vi samma fil som vi har sparat allt i,
        // Vi börjar med att ta ut kontonummret
        // sen läser vi in alla kunder, och bnygger upp deras konto ingen.
        ArrayList<ListOfCustomers> myList;

        if (minArray.isEmpty()) {

            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);

                int x = in.readInt();
                myList = (ArrayList<ListOfCustomers>) in.readObject();

                for (ListOfCustomers listOfCustomers : myList) {
                    createCustomer(listOfCustomers.getfName(), listOfCustomers.getlName(), listOfCustomers.getPno());
                }

                int y = 1001;
                int h = 0;

                for (int i = y; i    <= x; i++) {
                    for (ListOfCustomers listOfCustomers : myList) {
                        for (int j = 0; j < listOfCustomers.getList().size(); j++) {

                            if (listOfCustomers.getList().get(j).getAccountNr() == (y)) {
                                double balance = listOfCustomers.getList().get(j).getBalance();
                                ArrayList <String> timeList = listOfCustomers.getList().get(j).getTime();

                                switch (listOfCustomers.getList().get(j).getAccountType()) {
                                    case "Sparkonto" -> createSavingsAccountTwo(listOfCustomers.getPno(), balance, timeList);
                                    case "Kreditkonto" -> createCreditAccountTwo(listOfCustomers.getPno(), balance, timeList);
                                }
                                h = 1;
                                break;

                            }
                        }

                    }
                    if (h == 0) {
                        y++;
                        new SavingsAccount(0);
                    }
                    if (h == 1) {
                        y++;
                        h = 0;
                    }


                }

                in.close();
                fileIn.close();



            } catch (IOException | ClassCastException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Något gick fel med upphämntingen av information",
                    "Error!", JOptionPane.ERROR_MESSAGE);        }

    }
    public ListOfCustomers makeAList(String pNo){

        // Den här metoden är till för att förbereda alla kunder
        // för resan in i en fil.

        ListOfCustomers a = null;
        ArrayList<ListofAccounts> listofaccounts = new ArrayList<>();



        for (Customer customer : minArray) {

            if (customer.getPersinNummer().equals(pNo)) {
                // P Nummer      //för och efternamn

                 String pno = String.valueOf(customer.getPersinNummer());
                 String fNamn = String.valueOf(customer.getForNamn());
                 String lName = String.valueOf(customer.getEfterNamn());


                /** account */
                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {

                    //saldo
                    double balance = customer.getAllyouraccouts().get(j).getSaldo();
                    // kontonummer
                    int kontonummer = customer.getAllyouraccouts().get(j).getKontonummer();
                    //kontotyp
                    String kontotyp = String.valueOf(customer.getAllyouraccouts().get(j).getKontotyp());
                    //ränta
                    double rantadouble = customer.getAllyouraccouts().get(j).getRanta();


                    ArrayList<String> arrayList = new ArrayList<>();
                    for (int i = 0; i < customer.getAllyouraccouts().get(j).getAllTheTime().size(); i++) {
                        arrayList.add(String.valueOf(customer.getAllyouraccouts().get(j).getAllTheTime().get(i)));
                        //arrayList.add("\n");
                    }

                    listofaccounts.add(new ListofAccounts(kontonummer,balance,kontotyp,rantadouble,arrayList));
                }

                listofaccounts.sort(Comparator.comparingInt(ListofAccounts::getAccountNr));


                a = new ListOfCustomers(pno,fNamn, lName, listofaccounts);
            }


        }

        return a;
    }
    public void saveTextfile(String pNo, int accountID) {

        // Skapa ett SavingsAccount så att vi kan efterfråga timeDate().
        // Sen letar vi efter kunden med rätt pNo, och sen kundens konto.
        // Nu när vi har rätt kund och rätt konto så kan vi skriva ut ett kvitto
        // i en txt fil med dagens datum och tid.
        // Dagens datum
        SavingsAccount savings = new SavingsAccount();
        String time = savings.timeDate();

        int index = 0;
        ArrayList<String> list;
        for (Customer customer : minArray) {
            if (customer.getPersinNummer().equals(pNo)) {
                for (int j = 0; j < customer.getAllyouraccouts().size(); j++) {
                    if (customer.getAllyouraccouts().get(j).getKontonummer() == accountID) {
                        index = 1;
                        list = customer.getAllyouraccouts().get(j).getAllTheTime();
                        String balance = String.valueOf( customer.getAllyouraccouts().get(j).getSaldo());
                        try{
                            String userHomeFolder = System.getProperty("user.home") + File.separator +"Desktop";
                            File textFile = new File(userHomeFolder, "Kontoutdrag.txt");
                            BufferedWriter bufferedWriter = new BufferedWriter
                                    (new FileWriter(textFile));
                            bufferedWriter.write(time + "\n\n");
                            for (String string : list ) {
                                String cleaned = string.replace("\u00a0"," ");
                                bufferedWriter.write(cleaned + "\n");
                            }
                            bufferedWriter.write(balance+"kr");
                            bufferedWriter.close();
                            JOptionPane.showMessageDialog(null, "Nu har det skickats en textfil till ditt skrivbort",
                                    "Message", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (index == 0){
            JOptionPane.showMessageDialog(null, "Något gick fel, vänligen försök igen",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
        }


    }
    public void createSavingsAccountTwo(String pNo,double balance, ArrayList<String> timeList) {

        // skapa ett konto utan showMessageDialog

        for (Customer customer : minArray) {
            if (customer.getPersinNummer().equals(pNo)) {

                SavingsAccount newAccount = new SavingsAccount(balance,timeList);
                customer.getAllyouraccouts().add(newAccount);


            }
        }

    }
    public void createCreditAccountTwo(String pNo,double balance, ArrayList<String> timeList) {
        // skapa ett konto utan showMessageDialog

        for (Customer customer : minArray) {

            if (customer.getPersinNummer().equals(pNo)) {

                CreditAccount newCredit = new CreditAccount(balance, timeList);
                customer.getAllyouraccouts().add(newCredit);
            }
        }
    }
}