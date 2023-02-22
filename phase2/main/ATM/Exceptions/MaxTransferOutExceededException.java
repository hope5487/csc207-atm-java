package ATM.Exceptions;


public class MaxTransferOutExceededException extends Exception{
    public MaxTransferOutExceededException(){
        super("Cannot exceed maximum amount of transferOut");
    }
}
