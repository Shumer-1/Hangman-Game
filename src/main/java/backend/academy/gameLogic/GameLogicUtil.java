package backend.academy.gameLogic;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GameLogicUtil {

    public static boolean guessLetter(String letter, String gameWord, StringBuilder guessedWord) {
        boolean flag = false;
        for (int i = 0; i < gameWord.length(); i++) {
            if (gameWord.charAt(i) == letter.charAt(0)) {
                flag = true;
                guessedWord.setCharAt(i, letter.charAt(0));
            }
        }
        return flag;
    }
}
