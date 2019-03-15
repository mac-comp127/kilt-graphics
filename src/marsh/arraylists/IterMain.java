package marsh.arraylists;

import java.util.ArrayList;
import java.util.ListIterator;

public class IterMain {

    public static void main(String[] args) {

        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i < 30; i++) {
            stringList.add("" + i);
        }

        System.out.println(stringList.toString());

//        for(String str : stringList) {
//            System.out.print(str);
//        }

        //Get Iterator for AL

        ListIterator<String> iter = stringList.listIterator(stringList.size());

        //Iterate through list and print
        while(iter.hasPrevious()) {
            System.out.print(iter.previous());
        }
    }
}
