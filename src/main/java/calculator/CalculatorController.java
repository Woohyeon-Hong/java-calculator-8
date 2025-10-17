package calculator;

import calculator.model.Numbers;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        outputView.printCalculatorStartPrompt();
        String input = inputView.readInputString();
        Numbers numbers = calculate(input);
        outputView.printResult(numbers);
    }

    private Numbers calculate(String input) {
        Numbers numbers = new Numbers(input);

        numbers.extractNumbers();
        numbers.addNumberListValues();

        return numbers;
    }
}
