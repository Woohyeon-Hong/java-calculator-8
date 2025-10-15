package calculator.view;

import calculator.model.Numbers;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    private String delimiters;

    public InputView() {
        delimiters = "[,:]";
    }


    private boolean isStartWithDoubleSlash(String input) {
        return input.startsWith("//");
    }

    private void validateUnsupportedDelimiter(String input) {
        char[] inputChars = input.toCharArray();

        for (char inputChar : inputChars) {
            if (isDigit(inputChar)) continue;
            if (inputChar == '.') continue;

            boolean isProper = false;
            for (int i = 1; i < delimiters.length() - 1; i ++) {
                if (inputChar == delimiters.charAt(i)) {
                    isProper = true;
                    break;
                }
            }

            if (!isProper) {
                throw new IllegalArgumentException("잘못된 구분자가 사용되었습니다: " + inputChar);
            }

        }
    }

    private boolean isDigit(char target) {
        return (target >= 48 && target <= 57);
    }

}
