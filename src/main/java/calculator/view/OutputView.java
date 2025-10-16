package calculator.view;

import calculator.model.Numbers;

public class OutputView {

    private static final String START_PROMPT = "덧셈할 문자열을 입력해 주세요.";
    private static final String RESULT_PREFIX = "결과 : ";

    public OutputView() {
    }
    
    public void printCalculatorStartPrompt() {
        System.out.println(START_PROMPT);
    }

    public void printResult(Numbers numbers) {
        double result = numbers.getSum();

        if (result == (int) result) {
            System.out.println(RESULT_PREFIX + (int) result);
        } else {
            System.out.printf(RESULT_PREFIX + "%.1f", result);
        }
    }
}
