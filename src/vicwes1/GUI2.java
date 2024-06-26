package vicwes1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI2 extends JFrame implements ActionListener {
    // GUI2 är en fortsättning på GUI, den liknar GUI2 in strukturen
    // Här finns det lite fler globala Jbuttons än i GUI, som vi
    // senare i createButton skapar och lägger till i rightPanel.

    BankLogic bank;
    JButton closeAccount,withdraw, deposit, createSavings, createCreditAccount, getAccount;
    JButton getAllInfo, getTransactions, clearButton, exitButton,changeName, kontoutdrag;
    JPanel rightpanel, leftPanel;
    JScrollPane scrollPane;
    JTextArea textarea;
    private final String name,lastName,pNo;



    public GUI2(String name, String lastName, String pNo ) {
        this.name = name;
        this.lastName = lastName;
        this.pNo = pNo;
        frame();
        leftJPanel();
        rightJPanel();
        createButton();
        this.setVisible(true);

    }
    private void createButton(){

        changeName = new JButton("Change Name");
        changeName.addActionListener(this);

        closeAccount = new JButton("CloseAccount");
        closeAccount.addActionListener(this);
        closeAccount.setFocusable(false);

        withdraw = new JButton("Withdraw");
        withdraw.addActionListener(this);
        withdraw.setFocusable(false);

        deposit = new JButton("Deposit");
        deposit.addActionListener(this);
        deposit.setFocusable(false);

        createSavings = new JButton("CreateSavings");
        createSavings.addActionListener(this);
        createSavings.setFocusable(false);

        createCreditAccount = new JButton("CreateCreditAccount");
        createCreditAccount.addActionListener(this);
        createCreditAccount.setFocusable(false);

        getAccount = new JButton("Get Account");
        getAccount.addActionListener(this);
        getAccount.setFocusable(false);

        getTransactions = new JButton("Get Transactions");
        getTransactions.addActionListener(this);
        getTransactions.setFocusable(false);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setFocusable(false);

        exitButton = new JButton("Logga ut");
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        getAllInfo = new JButton("Get All Info");
        getAllInfo.addActionListener(this);
        getAllInfo.setFocusable(false);

        kontoutdrag = new JButton("kontoutdrag");
        kontoutdrag.addActionListener(this);
        kontoutdrag.setFocusable(false);

        rightpanel.add(changeName);
        rightpanel.add(closeAccount);
        rightpanel.add(withdraw);
        rightpanel.add(deposit);
        rightpanel.add(createSavings);
        rightpanel.add(createCreditAccount);
        rightpanel.add(getAccount);
        rightpanel.add(getAllInfo);
        rightpanel.add(getTransactions);
        rightpanel.add(clearButton);
        rightpanel.add(exitButton);
        rightpanel.add(kontoutdrag);
    }
    private void frame (){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,2));
        this.setSize(500,500);
        //this.setLocationRelativeTo(null);
        this.setLocation(800,250);
        this.setResizable(false);
    }
    private void leftJPanel(){
        leftPanel = new JPanel();
        textarea = new JTextArea();

        textarea.setBackground(Color.LIGHT_GRAY);
        textarea.setForeground(Color.DARK_GRAY);
        textarea.setBorder(BorderFactory.createTitledBorder(this.pNo+" " + this.name + " " + this.lastName ));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setEditable(false);

        scrollPane = new JScrollPane(textarea);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setPreferredSize(new Dimension(240, 430));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        leftPanel.add(scrollPane);

        this.add(leftPanel);
    }
    private void rightJPanel(){
        rightpanel = new JPanel();
        rightpanel.setLayout(new GridLayout(6,2));
        this.add(rightpanel);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        bank = new BankLogic();
        if (e.getSource() == changeName){
            try {
                String name = JOptionPane.showInputDialog("Byt Förnamn till: ");
                String Surname = JOptionPane.showInputDialog("Byt Efternamn till:");

                if (name == null || Surname == null ) {
                    JOptionPane.showMessageDialog(null, "OPS! Något har går fel!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    bank.changeCustomerName(name,Surname,this.pNo);
                    GUI2 gui2 = new GUI2(name,Surname,pNo);
                    JOptionPane.showMessageDialog(null, "Du har nu bytit ditt namn",
                            "Message", JOptionPane.INFORMATION_MESSAGE);



                }

            }catch (NullPointerException | NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Du måste ange ett giltigt personnummer",
                        "OPS! Något har går fel!",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == closeAccount ){
            try {
                int accountNr =Integer.parseInt(JOptionPane.showInputDialog("Kontonummer:"));
                String answer = bank.closeAccount(this.pNo,accountNr);
                if (answer != null){
                    JOptionPane.showMessageDialog(null, "Du har tagit bort: " + answer,
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Kunde inte hitta kontot, försök igen",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                }

            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(null, " ",
                        "Message", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == withdraw){
            try{
                int accountId = Integer.parseInt(JOptionPane.showInputDialog("Kontonummer: "));
                int amount = Integer.parseInt(JOptionPane.showInputDialog("Hur mycket vill du ta ut? "));
                bank.withdraw(this.pNo,accountId,amount);

            }catch(NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Oj, något gick fel. Försök igen",
                        "OPS!",JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource() == deposit){
            try{
                int accountId = Integer.parseInt(JOptionPane.showInputDialog("Kontonummer: "));
                int amount = Integer.parseInt(JOptionPane.showInputDialog("Hur mycket vill du sätta in? "));
                bank.deposit(this.pNo,accountId,amount);

            }catch(NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Oj, något gick fel. Försök igen",
                        "OPS!",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == createSavings) {
            try{
                bank.createSavingsAccount(this.pNo);

            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Oj, något gick fel. Försök igen",
                        "OPS!",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == createCreditAccount) {
            try{
                bank.createCreditAccount(this.pNo);

            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Oj, något gick fel. Försök igen",
                        "OPS!",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == getAccount){
            try{
                int accountId = Integer.parseInt(JOptionPane.showInputDialog("Kontonummer: "));
                bank.getAccount(this.pNo,accountId);
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(null, " Försök igen",
                        "OPS!",JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == getTransactions){
            int accountId = Integer.parseInt(JOptionPane.showInputDialog("Kontonummer: "));
            bank.getTransactions(this.pNo,accountId);
        }
        if (e.getSource()== getAllInfo){

            for (int i = 1; i < bank.getCustomer(this.pNo).size(); i++) {
                textarea.append(bank.getCustomer(this.pNo).get(i));
            }
            textarea.append("\n");


        }
        if (e.getSource() == clearButton){
            textarea.setText("");
        }
        if (e.getSource() == exitButton){
            int x = JOptionPane.showConfirmDialog(null,"är du säker","Logga ut",JOptionPane.YES_NO_OPTION);
            if (x == 0){
                this.dispose();
            }

        }
        if (e.getSource() == kontoutdrag){
            int accountId = Integer.parseInt(JOptionPane.showInputDialog("Kontonummer: "));
            bank.saveTextfile(this.pNo,accountId);
        }

    }
}
