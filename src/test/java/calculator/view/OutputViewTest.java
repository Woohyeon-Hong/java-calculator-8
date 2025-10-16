package calculator.view;

import calculator.model.Numbers;
import java.util.ArrayList;
import java.util.List;
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
        List<Double> numberList = new ArrayList<Double>();
        numberList.add(1.0);
        numberList.add(2.0);
        numberList.add(3.0);

        Numbers numbers = new Numbers(numberList);
        numbers.addNumberListValues();

        //when && then
        outputView.printResult(numbers);
    }

    @Test
    void printResult_소수의_합이_정수가_되는_경우() {
        //given
        List<Double> numberList = new ArrayList<Double>();
        numberList.add(1.2);
        numberList.add(2.5);
        numberList.add(3.3);

        Numbers numbers = new Numbers(numberList);
        numbers.addNumberListValues();

        //when && then
        outputView.printResult(numbers);
    }

    @Test
    void printResult_합이_소수인_경우() {
        //given
        List<Double> numberList = new ArrayList<Double>();
        numberList.add(1.2);
        numberList.add(2.4);
        numberList.add(3.3);

        Numbers numbers = new Numbers(numberList);
        numbers.addNumberListValues();

        //when && then
        outputView.printResult(numbers);
    }
}