
import java.util.ArrayList;
import java.util.List;

public class WordleGameModel {

    private String correctWord;
    private Status status;
    private int attempts;
    private List<WordleGameFrame> frames = new ArrayList<>();

    public enum Status {WON, PLAYING};

    public WordleGameModel(String correctWord) {
        this.correctWord = correctWord;
        this.attempts = 0;
        status = Status.PLAYING;
    }
    
    public void addFrame(WordleGameFrame frame) {
        frames.add(frame);
    }

    public void updateFrames(WordleGameEvent event) {
        for(WordleGameFrame frame : frames) {
            frame.update(event);
        }
    }

    public void playGuessedWord(String guessedWord) {
        attempts++;
        int matchCount = 0;
        int[] equalWords = new int[5];

        char[] charGuessedWord = guessedWord.toCharArray();
        char[] charCorrectWord = correctWord.toCharArray();

        for(int i=0; i<5; i++) {
            equalWords[i] = 3;

            if(correctWord.indexOf(charGuessedWord[i])!=-1) {
                equalWords[i]=2;
            }

            if(charCorrectWord[i]==charGuessedWord[i]) {
                equalWords[i] = 1;
                matchCount++;
            }
        }

        if(matchCount==5) {status = Status.WON;}

        WordleGameEvent event = new WordleGameEvent(this, attempts, status, guessedWord, equalWords);

        updateFrames(event);
    }
}
