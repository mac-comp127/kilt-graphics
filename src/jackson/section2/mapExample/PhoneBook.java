package jackson.section2.mapExample;

import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    private TreeMap<String, String> phoneBook;

    public PhoneBook(){
        phoneBook = new TreeMap<>();
    }

    public void addPerson(String name, String number){
        phoneBook.put(name, number);
    }

    public String search(String name){
        return phoneBook.get(name);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry : phoneBook.entrySet()){
            sb.append(entry.getKey());
            sb.append(" ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args){
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPerson("Libby Shoop", "213-343-1234");
        phoneBook.addPerson("Bret Jackson", "123-234-3456");
        phoneBook.addPerson("Shilad Sen", "234-445-2345");

        //System.out.println(phoneBook.search("Shilad Sen"));

        System.out.println(phoneBook);
    }

}
