import javax.swing.*;
import java.awt.*;

public class WordleGameFrame extends JFrame implements WordleGameView {
    private String correctWord;
    private JTextField guessedWord;
    private JButton playGuess;
    private JTextField[][] words;

    public WordleGameFrame() {
        getCorrectWord();
        WordleGameModel model = new WordleGameModel(correctWord);
        model.addFrame(this);
        WordleGameController controller = new WordleGameController(model, this);

        this.setLayout(new GridLayout(6,5));
        words = new JTextField[5][5];
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                JTextField text = new JTextField();
                text.setFont((new Font("Serif", Font.PLAIN, 20)));
                text.setEditable(false);
                text.setHorizontalAlignment(JTextField.CENTER);
                words[i][j] = text;
                this.add(text);
            }
        }

        guessedWord = new JTextField("Enter word");
        this.add(guessedWord);
        playGuess = new JButton("Play");
        playGuess.addActionListener(controller);
        this.add(playGuess);

        this.setTitle("Wordle Game");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private void getCorrectWord() {
        correctWord = JOptionPane.showInputDialog(this,"Please enter the 5-character word to be guessed");
        while (correctWord.length() != 5) {
            correctWord = JOptionPane.showInputDialog(this,"The Entered word must be 5-characters");
        }
    }

    public String getGuessedWord() {
        return guessedWord.getText();
    }

    public void notifyError() {
        JOptionPane.showMessageDialog(null, "Enter a five letter word");
    }

    public void play(WordleGameEvent event) {
        for(int i=0; i<5; i++) {
            words[event.getAttempts()-1][i].setText(String.valueOf(event.getGuessedWord()[i]).toUpperCase());
            if(event.getEqualWords(i)==1) {
                words[event.getAttempts() - 1][i].setBackground(Color.GREEN);
            }
            if(event.getEqualWords(i)==2) {
                words[event.getAttempts() - 1][i].setBackground(Color.YELLOW);
            }
            if(event.getEqualWords(i)==3) {
                words[event.getAttempts() - 1][i].setBackground(Color.RED);
            }
        }
    }


    @Override
    public void update(WordleGameEvent event) {
        if(event.getStatus() == WordleGameModel.Status.WON) {
            play(event);
            JOptionPane.showMessageDialog(null, "You won the game! Thanks for playing.");
            this.dispose();
        }

        else if(event.getAttempts()>=5) {
            play(event);
            JOptionPane.showMessageDialog(null, "Game Over! You were unable to guess the word, try better next time.");
            this.dispose();
        }

        else {
            play(event);
        }
    }

    public static void main(String[] args) {
        new WordleGameFrame();
    }
}
