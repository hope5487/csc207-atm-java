package ATM.ShoppingPackage;

import ATM.Exceptions.NoItemToRemoveFromCartException;

import java.util.HashMap;

public class ShoppingCart {
    private final HashMap<Item, Integer> items = new HashMap<>();//hash map of item and amount

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void addItem(Item item, int amount){
        if (items.containsKey(item)){
            items.replace(item, items.get(item) + amount);
        } else {
            items.put(item, amount);
        }
    }

    public void removeItem(Item item, int amount) throws NoItemToRemoveFromCartException {
        if (items.containsKey(item)){
            if (items.get(item) > amount){
                items.replace(item, items.get(item) - amount);
            } else {
                items.remove(item);
            }
        } else {
            throw new NoItemToRemoveFromCartException();
        }
    }

    public int calculateTotal(){
        int sum = 0;
        for(Item item : items.keySet()){
            sum += item.getPrice() * items.get(item);
        }
        return sum;
    }
}
