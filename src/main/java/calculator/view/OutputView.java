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
        printResultPrefix();
        printResultValue(result);
    }

    private static void printResultPrefix() {
        System.out.print(RESULT_PREFIX);
    }

    private static void printResultValue(double result) {
        String resultValue = result % 1.0 == 0.0 ? String.format("%.0f", result) : String.format("%.1f", result);
        System.out.println(resultValue);
    }
}
