package ATM.DataStorage;

import ATM.ShoppingPackage.Item;
import java.util.ArrayList;

public class ItemStorage {

    private final ArrayList<Item> itemList = new ArrayList<>();

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList() {
        Item apple = new Item("Apple", 3);
        itemList.add(apple);
        Item banana = new Item("Banana", 1);
        itemList.add(banana);
        Item car = new Item("Car", 40000);
        itemList.add(car);
        Item condo = new Item("Condo", 500000);
        itemList.add(condo);
    }

    public Item searchItem(String itemName){
        for (Item item : itemList){
            if (itemName.equals(item.getItemName())){
                return item;
            }
        }
        return null;
    }
}
