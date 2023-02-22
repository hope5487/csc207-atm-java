package ATM.Exceptions;

public class StockAmountExceededException extends Exception {
    public StockAmountExceededException(){
        super("exceed amount of stock user has");
    }
}
