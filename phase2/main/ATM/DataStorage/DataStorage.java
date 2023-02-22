package ATM.DataStorage;

public class DataStorage {

    private final DateStorage dateStorage = new DateStorage();
    private final BillStorage billStorage = new BillStorage();
    private final AccountStorage accountStorage = new AccountStorage();
    private final TransactionStorage transactionStorage = new TransactionStorage();
    private final UserStorage userStorage = new UserStorage();
    private final PortfolioStorage portfolioStorage = new PortfolioStorage();
    private final ItemStorage itemStorage = new ItemStorage();

    public DateStorage getDateStorage() {
        return dateStorage;
    }

    public BillStorage getBillStorage() {
        return billStorage;
    }

    public AccountStorage getAccountStorage() { return accountStorage; }

    public UserStorage getUserStorage() {
        return userStorage;
    }

    public TransactionStorage getTransactionStorage() {
        return transactionStorage;
    }

    public PortfolioStorage getPortfolioStorage() { return portfolioStorage;}

    public ItemStorage getItemStorage() {
        return itemStorage;
    }
}
