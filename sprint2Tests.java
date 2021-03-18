import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class sprint2Tests {
    private Game game;
    private alphabeticalCrypto alphabeticalCrypto;
    private numericalCrypto numericalCrypto;
    private Player Player;

    @BeforeEach
    void setUp(){
        this.game = new Game();
        this.alphabeticalCrypto = new alphabeticalCrypto();
        this.numericalCrypto = new numericalCrypto();
        this.Player = new Player("testUser");

        game.playerGuess = new char[5];
        game.playerGuess[0] = 'G';
        game.playerGuess[1] = 'T';
        game.playerGuess[2] = 'E';
        game.playerGuess[3] = 'S';
        game.playerGuess[4] = 'T';

        alphabeticalCrypto.phrase = "LetterTestPhrase";
        numericalCrypto.phrase = "NumberTestPhrase";


        numericalCrypto.intEncryptedPhrase[0] = 1;
        numericalCrypto.intEncryptedPhrase[1] = 22;
        numericalCrypto.intEncryptedPhrase[2] = 3;
        numericalCrypto.intEncryptedPhrase[3] = 44;
        numericalCrypto.intEncryptedPhrase[4] = 55;
        numericalCrypto.intEncryptedPhrase[4] = 6;
    }

    @Test
    void saveCryptogram(){
        Scanner sc1 = new Scanner(System.in);
        String loadUsername = "";
        String loadSolution = "";
        char[] ch1 = new char[alphabeticalCrypto.encryptedPhrase.length];
        char[] ch2 = new char[game.playerGuess.length];

        game.saveGame(alphabeticalCrypto,Player,sc1);

        File myObj = new File("C:\\Users\\euanb\\Documents\\2ndYear\\CS207\\2ndSemesterAssignment\\savedLetterCryptos.txt");
        List<String> words = new ArrayList<String>();
        try(Scanner sc = new Scanner((myObj), StandardCharsets.UTF_8.name())) {
            while(sc.hasNextLine()) {
                words.add(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String line : words){
            String [] split = line.split(",");
            if (split[0].equals(Player.getUsername())) {
                loadUsername = split[0];
                String loadPuzzle = split[1];
                String loadGuess = split[2];
                loadSolution = split[3];
                for (int i = 0; i < loadPuzzle.length(); i++) {
                    ch1[i] = loadPuzzle.charAt(i);
                }
                for (int i = 0; i < loadGuess.length(); i++) {
                    ch2[i] = loadGuess.charAt(i);
                }
            }
        }
        assertEquals(loadUsername, Player.getUsername());
        assertEquals(loadSolution, alphabeticalCrypto.phrase);
        for(int i=0;i<ch1.length;i++) {
            assertEquals(ch1[i], alphabeticalCrypto.encryptedPhrase[i]);
        }
        for(int i=0;i<ch2.length;i++) {
            assertEquals(ch2[i], game.playerGuess[i]);;
        }
    }
}
