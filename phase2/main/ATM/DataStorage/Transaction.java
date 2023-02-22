package ATM.DataStorage;

import ATM.MainOperation.ATM;

public class Transaction {

    private final int transactionNumber;
    private final String type;
    private final double amount;
    private final int accountNumber;
    private int accountNumberOfOther = -1;
    private boolean isAbleToUndo = true;

    /**
     * @param type is type of account. "deposit" or "withdraw" or "transfer" or "pay bill"
     * @param accountNumber is account number that did a transaction.
     * @param amount is the amount of money of a transaction
     */
    public Transaction(String type, int accountNumber, double amount){
        this.transactionNumber = ATM.dataStorage.getTransactionStorage().getTransactionList().size();
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
        if (type.equals("pay bill") || type.equals("monthly account fee")){
            this.isAbleToUndo = false;
        }
    }

    /**
     * @param accountNumberOfOther is account number that received a transfer from another account.
     */
    public Transaction(String type, int accountNumber, int accountNumberOfOther, double amount){
        this.transactionNumber = ATM.dataStorage.getTransactionStorage().getTransactionList().size();
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.accountNumberOfOther = accountNumberOfOther;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getAccountNumberOfOther() {
        return accountNumberOfOther;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public boolean getIsAbleToUndo() { return isAbleToUndo; }

    public void setIsAbleToUndo(boolean isAbleToUndo) {
        this.isAbleToUndo = isAbleToUndo;
    }

    @Override
    public String toString() {
        if (accountNumberOfOther != -1){
            return accountNumber + " " + type + " " + accountNumberOfOther + " $" + amount;
        }
        return accountNumber + " " + type + " $" + amount;
    }
}
