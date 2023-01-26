import java.util.EventObject;

public class WordleGameEvent extends EventObject{
    private int attempts;
    private String guessedWord;
    private int[] equalWords;
    private WordleGameModel.Status status;

    public WordleGameEvent(WordleGameModel model, int attempts, WordleGameModel.Status status, String guessedWord, int[] equalWords) {
        super(model);
        this.attempts = attempts;
        this.guessedWord = guessedWord;
        this.equalWords = equalWords;
        this.status = status;
    }

    public int getAttempts() {
        return attempts;
    }

    public WordleGameModel.Status getStatus() {
        return status;
    }

    public char[] getGuessedWord() {
        return guessedWord.toCharArray();
    }

    public int getEqualWords(int i) {
        return equalWords[i];
    }
}
