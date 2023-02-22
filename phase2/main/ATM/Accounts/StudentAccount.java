package ATM.Accounts;

public class StudentAccount extends ChequingAccount {
    /**
     * StudentAccount is simply a kind of ChequingAccount with no monthlyFee.
     */

    @Override
    public void chargeMonthlyFee(){}
}
