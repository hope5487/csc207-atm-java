package ATM.Exceptions;

public class NoItemToRemoveFromCartException extends Exception{
    public NoItemToRemoveFromCartException(){
        super("No item to remove from cart!");
    }
}
