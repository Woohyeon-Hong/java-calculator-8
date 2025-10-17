package calculator.view;

import calculator.model.Numbers;
import org.junit.jupiter.api.Test;

class OutputViewTest {

    OutputView outputView = new OutputView();

    @Test
    void printCalculatorStartPrompt() {
        outputView.printCalculatorStartPrompt();
    }

    @Test
    void printResult_정수만_입력되는_경우() {
        //given
        Numbers numbers = new Numbers("1.0,2.0,3.0");
        numbers.extractNumbers();
        numbers.addNumberListValues();

        //when && then
        outputView.printResult(numbers);
    }

    @Test
    void printResult_소수의_합이_정수가_되는_경우() {
        //given
        Numbers numbers = new Numbers("1.2,2.5,3.3");
        numbers.extractNumbers();
        numbers.addNumberListValues();

        //when && then
        outputView.printResult(numbers);
    }

    @Test
    void printResult_합이_소수인_경우() {
        //given
        Numbers numbers = new Numbers("1.2,2.4,3.3");
        numbers.extractNumbers();
        numbers.addNumberListValues();

        //when && then
        outputView.printResult(numbers);
    }
}