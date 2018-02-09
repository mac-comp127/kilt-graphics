package kluver.examples.inheritance;

import java.util.Scanner;

public class MadLib extends MadLibBase{

    public String getStory() {
        // get re-used words
        String name = getName();
        String verb = getVerb();

        return "There once was a dog named "+name+". "+name+" loved to "+
                verb+". "+name+" would "+verb+" "+getAdverb()+" and "+name+" " +
                "would "+verb+" "+getAdverb()+". One day "+name+" was "+verb+"ing " +
                "in the woods and found a "+getNoun()+". It was OK though, since "
                +name+" could also "+getVerb()+" which "+name+" used to save the day!";
    }

    public static void main(String[] args) {
        MadLib madlib = new MadLib();
        System.out.println(madlib.getStory());
    }
}
