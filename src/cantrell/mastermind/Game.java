package cantrell.mastermind;

import java.util.List;

public class Game {
    private ColorSet answer;
    private List<Round> rounds;    // please ignore this syntax for now

    public ColorSet getAnswer() {
        return answer;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void createNewRound(ColorSet guess) {
        //.........
    }
}
