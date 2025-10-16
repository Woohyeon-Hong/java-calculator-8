package calculator.view;

import calculator.model.Numbers;

public class OutputView {

    public OutputView() {
    }

    //todo: promt를 변수로 분리
    public void printCalculatorStartPrompt() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public void printResult(Numbers numbers) {
        double result = numbers.getSum();

        System.out.print("결과 : ");
        if (result == (int) result) {
            System.out.println((int)result);
        } else {
            System.out.printf("%.1f", result);
        }
    }
}
