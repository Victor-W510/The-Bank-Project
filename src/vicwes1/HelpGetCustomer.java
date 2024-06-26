package vicwes1;

/**
 * @param accountNr skapa ett obejct som är tänkt och hålla ett konto.
 */
public record HelpGetCustomer(int accountNr, String balance, String accountType, String interest) {

    @Override
    public String toString() {
        return "HelpGetCustomer{" +
                "accountNr=" + accountNr +
                ", balance='" + balance + '\'' +
                ", accountType='" + accountType + '\'' +
                ", interest='" + interest + '\'' +
                '}';
    }
}

