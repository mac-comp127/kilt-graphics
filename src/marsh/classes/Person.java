package marsh.classes;

public class Person {

    private String name = new String("");
    private String phoneNumber;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Improper phone number formatting");
        }
    }

    public String getAreaCode() {
        return this.phoneNumber.substring(0,3);
    }

}
