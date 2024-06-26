package vicwes1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {

    // GUI bygger upp de Graphical user interface för kunden.
    // Här SKapar vi ett fönster med setFrame, en meny i createMenu,
    // en rightPanel och en leftPanel.
    // Det är också här vi lägger till en bild med hjälp av ImageIcon.

    private JButton createCustomer, getAllCustomers;
    private JPanel leftPanel, rightPanel;
    private JMenuBar menuBar;
    private JMenuItem  deleteCustomer,logIn,saveFile,openFile;
    private final ImageIcon icon = new ImageIcon("Image.jpg");
    private JTextArea textarea;
    JScrollPane scrollPane;
    public GUI(){

        setFrame();
        createButton();
        leftJPanel();
        rightJPanel();
        createMenu();

        this.setJMenuBar(menuBar);
        this.add(leftPanel);
        this.add(rightPanel);

        setVisible(true);
    }
    private void createMenu(){
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Meny");
        menuBar.add(menu);



        deleteCustomer = new JMenuItem("Delete Customer");
        deleteCustomer.addActionListener(this);

        JMenuItem getAccount = new JMenuItem("Show Savings Account");
        getAccount.addActionListener(this);

        saveFile = new JMenuItem("Save To File");
        saveFile.addActionListener(this);

        openFile = new JMenuItem("Open File");
        openFile.addActionListener(this);

        logIn = new JMenuItem("Logga In");
        logIn.addActionListener(this);


        menu.add(saveFile);
        menu.add(openFile);
        menu.add(deleteCustomer);
        menu.add(logIn);

    }
    private void createButton(){
        createCustomer = new JButton("Create Customer");
        createCustomer.setFocusable(false);
        createCustomer.addActionListener(this);

        getAllCustomers = new JButton("Get all Customers");
        getAllCustomers.setFocusable(false);
        getAllCustomers.addActionListener(this);

    }

    private void rightJPanel(){

        rightPanel = new JPanel(new GridLayout(2,1));
        JPanel rightTopPanel = new JPanel(new GridLayout(1, 1));

        JLabel label = new JLabel();
        label.setIcon(icon);
        rightTopPanel.add(label);

        JPanel rightBottomPanel = new JPanel(new GridLayout(2, 1));
        rightBottomPanel.add(createCustomer);
        rightBottomPanel.add(getAllCustomers);

        rightPanel.add(rightTopPanel);
        rightPanel.add(rightBottomPanel);
    }
    private void leftJPanel(){
        leftPanel = new JPanel();
        textarea = new JTextArea("Sök På Kunder....");

        textarea.setBackground(Color.LIGHT_GRAY);
        textarea.setForeground(Color.DARK_GRAY);
        textarea.setBorder(BorderFactory.createTitledBorder("Info"));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setEditable(false);

        scrollPane = new JScrollPane(textarea);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setPreferredSize(new Dimension(240, 430));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        leftPanel.add(scrollPane);
    }

    private void setFrame(){
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,2));
        this.setBackground(Color.blue);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BankLogic bank = new BankLogic();

        if (e.getSource() == createCustomer){
            try {
                String name = JOptionPane.showInputDialog("FörNamn");
                String Surname = JOptionPane.showInputDialog("Efternamn");
                String pno = JOptionPane.showInputDialog("Personnummer");

                if (name.equals("") || Surname.equals("") ) {
                    JOptionPane.showMessageDialog(null, "Försök igen, varken förnamntet eller efternamnet kan lämnas tomt",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    bank.createCustomer(name,Surname,pno);
                }
            }catch (NullPointerException | NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Du måste ange ett giltigt personnummer",
                        "OPS! Något har går fel!",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == getAllCustomers) {
            textarea.setText("");
            bank.getAllCustomers();
            for (int i = 0; i < bank.getAllCustomers().size(); i++) {
                textarea.append(bank.getAllCustomers().get(i));
            }
            textarea.append("\n");

        }

        if (e.getSource() == deleteCustomer){

            String pno = JOptionPane.showInputDialog("Personnummer");
            bank.deleteCustomer(pno);

        }
        if (e.getSource() == logIn){
            try {
                ArrayList person;
                String pno = JOptionPane.showInputDialog("Personnummer");
                person = bank.getOneCustomer(pno);

                if (!person.isEmpty()){

                    this.dispose();
                }else JOptionPane.showMessageDialog(null,null,"Något gick fel",JOptionPane.ERROR_MESSAGE);

            } catch (NullPointerException n){
                JOptionPane.showMessageDialog(null, "Du måste ange ett giltigt personnummer",
                        "OPS! Något har går fel!",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == saveFile){

            int x = JOptionPane.showConfirmDialog(null,"Spara alla kunder samt alla konton" +
                    " (inklusive de senaste transaktionerna) till en datafil",null,JOptionPane.YES_NO_OPTION);
            if (x == 0){
                bank.saveToFile();
            }
        }
        if (e.getSource() == openFile){

            int x = JOptionPane.showConfirmDialog(null,"Vill du importera från fil",null,JOptionPane.YES_NO_OPTION);
            if (x == 0){
                bank.openFile();
                bank.getAllCustomers();
                textarea.setText("");
                for (int i = 0; i < bank.getAllCustomers().size(); i++) {
                    textarea.append(bank.getAllCustomers().get(i));
                }
                textarea.append("\n");
            }
        }
    }
}