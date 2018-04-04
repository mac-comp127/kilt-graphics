package cantrell.mastermind;

public class Round {
    private Game game;
    private ColorSet guess;
    private Response response;

    public Round(Game game, ColorSet guess) {
        this.game = game;
        this.guess = guess;
    }

    public ColorSet getGuess() {
        return guess;
    }

    public Response getResponse() {
        return response;
    }

    public void respondToGuess() {
        response = new Response(guess, game.getAnswer());
    }
}
