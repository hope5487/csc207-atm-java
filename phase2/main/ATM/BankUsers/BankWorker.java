package ATM.BankUsers;

import ATM.Accounts.Account;
import ATM.Accounts.AccountFactory;
import ATM.Accounts.ChequingAccount;
import ATM.MainOperation.ATM;
import ATM.PortfolioPackage.Portfolio;

import java.util.ArrayList;

public class BankWorker extends User {
    private final ArrayList<User> userList = ATM.dataStorage.getUserStorage().getUserList();
    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();

    BankWorker(){
        super();
    }

    public BankWorker(String userName, String password, int primaryAccountNumber, int portfolioNumber) {
        super(userName, password, primaryAccountNumber, portfolioNumber);
    }

    //initial password for user is 0000 (in the user class)
    public void createUser(String userName) {
        User user = new User(userName);
        ChequingAccount account = new ChequingAccount();
        user.setPrimaryAccountNumber(account.getAccountNumber());
        Portfolio portfolio = new Portfolio();
        user.setPortfolioNumber(portfolio.getPortfolioNumber());
        ATM.dataStorage.getPortfolioStorage().getPortfolioList().add(portfolio);
        account.addOwner(userName);
        accountList.add(account);
        userList.add(user);
        ATM.dataStorage.getUserStorage().writeUserFile();
        ATM.dataStorage.getAccountStorage().writeAccountFile();
        ATM.dataStorage.getPortfolioStorage().writePortfolioFile();
    }

    /**
     * Create new account for user.
     *
     * @param user is user who want to create account.
     * @param accountType is the string of the account name (same with the class name).
     */
    public void createAccount(User user, String accountType) {
        AccountFactory accountFactory = new AccountFactory();
        Account account = accountFactory.getAccount(accountType);
        account.addOwner(user.getUserName());
        accountList.add(account);
        ATM.dataStorage.getAccountStorage().writeAccountFile();
    }
}
