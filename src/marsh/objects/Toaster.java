package marsh.objects;

public class Toaster {

    boolean newToaster;
    boolean isOn;
    int numberOfSlots;
    int heatSetting;
    boolean isPluggedIn;
    boolean isPushedDown;
    String foodItem = "";
    int itemsInToaster;
    boolean isEmpty;

    public Toaster() {
        newToaster = true;
        numberOfSlots = 2;
        isPluggedIn = true;
        isOn = true;
        isEmpty = true;
        isPushedDown = false;
        itemsInToaster = 0;
    }

    public boolean toastThings(String item, int num, int heat) {
        if (num > numberOfSlots) {
            return false;
        }
        foodItem = item;
        itemsInToaster = num;

        heatSetting = heat;
        isPushedDown = true;

        return true;
    }

    public void resetToaster() {
        itemsInToaster = 0;
        isPushedDown = false;
        foodItem = "";
    }

}
