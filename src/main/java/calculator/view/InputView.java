package calculator.view;

import calculator.model.Numbers;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    private boolean isStartWithDoubleSlash(String input) {
        return input.startsWith("//");
    }
}
