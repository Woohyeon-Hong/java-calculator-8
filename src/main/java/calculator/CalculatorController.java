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

    public void calculate() {
        outputView.printCalculatorStartPrompt();
        String input = inputView.readInputString();

        Numbers numbers = new Numbers(input);
        numbers.extractNumbers();
        numbers.addNumberListValues();
        outputView.printResult(numbers);
    }
}
