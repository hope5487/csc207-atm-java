package ATM.FrequentlyAskedQuestions;

public class Answers {

    public String payBillsAnswer() {
        return ("From the main menu select the Pay Bills option. Follow the prompts asking for your account \n" +
                "number, the amount, and the recipient of the bill. When you are ready click the Confirm button to \n" +
                "pay the amount to the specified recipient.");
    }

    public String depositAnswer() {
        return ("From the main menu select the Deposit option. Follow the prompts asking for your account \n" +
                "number, and the amount you want to deposit. Note that our system can only deposit funds of multiples \n" +
                "of $5, otherwise you will be notified. When you are ready to deposit, click the Confirm button");
    }

    public String withdrawAnswer() {
        return ("From the main menu select the Withdraw option. Follow the prompts asking for your account \n" +
                "number, the amount you want to withdraw and the bill type you want your money in. When you \n" +
                "are ready to withdraw, click the Confirm button.");
    }

    public String checkBalanceAnswer() {
        return ("To check the balance of your account, the net total of your accounts, select the Manage Accounts options \n" +
                "on the main menu. From there click on the View Net Total button, and your total will be displayed \n " +
                "in the text area below.");
    }

    public String transferAnswer() {
        return ("There are 2 ways to transfer funds, respectively selecting which transfer you want to perform from \n" +
                "the main menu by selecting the Transfer to Another Account option or Transfer to Another User \n" +
                "option. " +
                "1) To another account \n" +
                "\t Select your account number you want to send funds from, the account number to send funds to, and the \n" +
                "and the amount you want to transfer. When you are ready to transfer click the Confirm button. \n" +
                "2) To another user \n" +
                "\t Select your account number you want to send funds from, the user to send to (The user must be \n" +
                "in the system. For more details refer to question 9 in the drop down list), and the amount. \n" +
                "When you are ready to transfer, click the Confirm button. \n" +
                "Note: If you need to know how to create a new account refer to the corresponding question from the drop down list.");
    }

    public String loginAnswer() {
        return ("If you are unable to login to the system, please contact the bank manager and they \n" +
                "will be able to create a login in for you or you can change your password yourself. To change your password \n" +
                "refer to question 10 in the drop down list.");
    }

    public String newAccountAnswer() {
        return ("If you want to add a new bank account to your account, please contact a bank worker or the bank manager \n" +
                "and they can create a new bank account for you. If you want to know the types of accounts you can add \n" +
                "refer to question 8 in the drop down list.");
    }

    public String createAccountAnswer() {
        return ("If you are looking to add a new bank account to your account here are the types of accounts: \n" +
                "Line of Credit \n" +
                "Credit Card \n" +
                "Chequing \n" +
                "Savings \n" +
                "Student Account");
    }

    public String createUserAnswer() {
        return ("The difference between an Account and a User is that you are the User and own an Account. An account \n" +
                "holds all of your bank accounts which are categorized by account number, used for paying bills, \n" +
                "depositing funds, transferring funds, exchanging funds, and withdrawing funds. If you are want to add \n" +
                "a User to the system contact the bank manager and they can set you up with a login.");
    }

    public String changePasswordAnswer() {
        return ("If you are a new user or a longtime user and want to change your password you can do so by selecting \n" +
                "the Change Password option from the main menu.");
    }

    public String undoAnswer() {
        return ("If you want to undo a transaction that was a mistake or you are not satisfied with, you can do so by \n" +
                "contacting the bank manager, and they will help aid you.");
    }

    public String withdrawTaxAnswer() {
        return ("No, there is no tax or fee when you withdraw funds from any account.");
    }

    public String exchangeFundsAnswer() {
        return ("To exchange funds into foreign currency select the Foreign Exchange option from the main menu. \n" +
                "From there follow the prompts and select the account you wish to exchange funds from, the currency \n" +
                "you to exchange into (GBP, USD, JPY, CHF, and AUS are currently available), and the amount you want \n" +
                "to exchange. When you are ready to exchange your funds click the Confirm button.");
    }

    public String stocksAnswer() {
        return ("To buy and sell stocks select the Manage Portfolio option from the main menu. \n" +
                "You can then select whether you want to buy stocks or sell stocks you own. \n" +
                "You can also view your returns and portfolio.");
    }

}
