import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleGameController implements ActionListener {

    WordleGameModel model;
    WordleGameFrame frame;

    WordleGameController(WordleGameModel model, WordleGameFrame frame) {
        this.model = model;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(frame.getGuessedWord().length()==5) {
            model.playGuessedWord(frame.getGuessedWord());
        }
        else {
            frame.notifyError();
        }
    }
}
