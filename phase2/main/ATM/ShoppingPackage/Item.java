package ATM.ShoppingPackage;

public class Item {

    private final String itemName;
    private final double price;

    public Item(String itemName, double cost){
        this.itemName = itemName;
        this.price = cost;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

}
