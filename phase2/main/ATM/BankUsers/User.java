package ATM.BankUsers;

import ATM.MainOperation.ATM;

public class User {


    //The user name of this user
    private String userName;

    //The password of this user (initial password is 0000)
    private String password = "0000";

    private int primaryAccountNumber;

    private int portfolioNumber;

    /**
     * Constructs a new user.
     */
    User() {}

    User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String password, int primaryAccountNumber, int portfolioNumber) {
        this.userName = userName;
        this.password = password;
        this.primaryAccountNumber = primaryAccountNumber;
        this.portfolioNumber = portfolioNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() { return password; }

    public int getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    public void setPrimaryAccountNumber(int primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }

    public int getPortfolioNumber() {
        return portfolioNumber;
    }

    void setPortfolioNumber(int portfolioNumber) {
        this.portfolioNumber = portfolioNumber;
    }

    /**
     * Sets the password for this user
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
        ATM.dataStorage.getUserStorage().writeUserFile();
    }
}