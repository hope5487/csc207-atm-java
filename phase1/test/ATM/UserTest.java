package ATM;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private final User user = new User();

    @After
    public void cleanUp(){
        ATM.dataStorage.getUserList().clear();
    }

    @Test
    public void testGetNetTotal(){
        ChequingAccount account1 = new ChequingAccount();
        account1.setPrimary(true);
        account1.setBalance(1000);
        CreditCardsAccount account2 = new CreditCardsAccount();
        account2.setBalance(100);
        user.accountList.add(account1);
        user.accountList.add(account2);
        assertEquals(user.getNetTotal(), 900);
    }

    @Test
    public void testGetSummary(){
        ChequingAccount account1 = new ChequingAccount();
        account1.setPrimary(true);
        account1.setBalance(1000);
        CreditCardsAccount account2 = new CreditCardsAccount();
        account2.setBalance(100);
        SavingAccount account3 = new SavingAccount();
        account3.setBalance(12345);
        user.accountList.add(account1);
        user.accountList.add(account2);
        user.accountList.add(account3);
        user.getSummary();
    }

    @Test
    public void testTransferToAnotherUser() throws
            MaxWithdrawExceededException, CannotWithdrawFromCreditCardException{
        user.userName = "sender";
        ChequingAccount account1 = new ChequingAccount();
        account1.setPrimary(true);
        account1.setBalance(1000);
        user.accountList.add(account1);

        User otherUser = new User("recipient");
        ChequingAccount account2 = new ChequingAccount();
        account2.setPrimary(true);
        account2.setBalance(1000);
        otherUser.accountList.add(account2);

        user.transferToAnotherUser(otherUser, account1, 500);
        assertTrue(account1.getBalance() == 500 && account2.getBalance() == 1500);
    }

    @Test
    public void testTransferToAnotherUserMaxWithdraw() throws
            CannotWithdrawFromCreditCardException{
        user.userName = "sender";
        ChequingAccount account1 = new ChequingAccount();
        account1.setPrimary(true);
        account1.setBalance(1000);
        user.accountList.add(account1);

        User otherUser = new User("recipient");
        ChequingAccount account2 = new ChequingAccount();
        account2.setPrimary(true);
        account2.setBalance(1000);
        otherUser.accountList.add(account2);

        try {
            user.transferToAnotherUser(otherUser, account1, 1500);
        } catch (MaxWithdrawExceededException expectedException) {
            System.out.println("Catch MaxWithdrawExceededException!");
            assertTrue(true);
        }
    }

    @Test
    public void testTransferToAnotherUserCreditCard() throws
            MaxWithdrawExceededException{
        user.userName = "sender";
        CreditCardsAccount account1 = new CreditCardsAccount();
        account1.setBalance(1000);
        user.accountList.add(account1);

        User otherUser = new User("recipient");
        ChequingAccount account2 = new ChequingAccount();
        account2.setPrimary(true);
        account2.setBalance(1000);
        otherUser.accountList.add(account2);

        try {
            user.transferToAnotherUser(otherUser, account1, 500);
        } catch (CannotWithdrawFromCreditCardException expectedException) {
            System.out.println("Catch CannotWithdrawFromCreditCardException!");
            assertTrue(true);
        }
    }

    @Test
    public void testTransferToAnotherAccount() throws
            MaxWithdrawExceededException, CannotWithdrawFromCreditCardException{
        ChequingAccount account1 = new ChequingAccount();
        account1.setPrimary(true);
        account1.setBalance(1000);
        ChequingAccount account2 = new ChequingAccount();
        account2.setPrimary(false);
        account2.setBalance(1000);
        user.accountList.add(account1);
        user.accountList.add(account2);
        user.transferToAnotherAccount(account1, account2, 500);
        assertTrue(account1.getBalance() == 500 && account2.getBalance() == 1500);
    }
}
