package calculator.view;

import org.junit.jupiter.api.Test;

class OutputViewTest {

    @Test
    void printCalculatorStartPrompt() {
        OutputView outputView = new OutputView();
        outputView.printCalculatorStartPrompt();
    }
}