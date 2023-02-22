package ATM.Accounts;

public class AccountFactory {

    public Account getAccount(String accountType){
        if (accountType.equalsIgnoreCase("CHEQUING ACCOUNT")){
            return new ChequingAccount();
        } else if (accountType.equalsIgnoreCase("SAVING ACCOUNT")){
            return new SavingAccount();
        } else if (accountType.equalsIgnoreCase("CREDIT CARDS ACCOUNT")){
            return new CreditCardsAccount();
        } else if (accountType.equalsIgnoreCase("LINE OF CREDIT ACCOUNT")) {
            return new LineOfCreditAccount();
        } else if (accountType.equalsIgnoreCase("STUDENT ACCOUNT")) {
            return new StudentAccount();
        }
        return null;
    }
}
