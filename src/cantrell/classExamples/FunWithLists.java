package cantrell.classExamples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FunWithLists {
    public static void main(String[] args) {
        List<ShoppingListItem> shoppingList = new LinkedList<ShoppingListItem>();
        shoppingList.add(new ShoppingListItem("milk"));
        shoppingList.add(new ShoppingListItem("eggs"));
        shoppingList.add(new ShoppingListItem("butter"));

        List<ShoppingListItem> originalShoppingList = new LinkedList<>(shoppingList);

        shoppingList.add(new ShoppingListItem("chocolate"));

        System.out.println("SHOPPING LIST:");
        for(ShoppingListItem item : shoppingList) {
            System.out.println(item);
        }

        System.out.println("ORIGINAL SHOPPING LIST WHEN I STARTED:");
        for(ShoppingListItem item : originalShoppingList) {
            System.out.println(item);
        }
    }
}

//class GroceryStore {
//    public Collection<String> availableItems() {
//        return .............
//    }
//}

class ShoppingListItem {
    private String description;

    public ShoppingListItem(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

