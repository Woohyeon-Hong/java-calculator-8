package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Numbers {

    private String input;
    private String delimiters;
    private List<Double> numberList;
    private double sum;

    public Numbers(String input) {
        this.input = input;
        this.delimiters = ",|:";
        this.numberList = new ArrayList<>();
        sum = 0.0;
    }

    public void extractNumbers() {
        if (isStartWithDoubleSlash(input)) {
            input = registerCustomDelimiters(input);
        }

        validateUnsupportedDelimiter(input);
        numberList = splitWithDelimiters(input);
        validateNumbers(numberList);
    }

    private boolean isStartWithDoubleSlash(String input) {
        return input.startsWith("//");
    }

    private String registerCustomDelimiters(String input) {
        List<Character> customDelimiters = new ArrayList<Character>();

        for (int i = 0; i <= input.length() - 5; i += 5) {
            if (isDigit(input.charAt(i))) {
                break;
            }

            String prefix = input.substring(i, i + 2);
            String suffix = input.substring(i + 3, i + 5);

            if (!prefix.equals("//") || !suffix.equals("\\n")) {
                throw new IllegalArgumentException("커스텀 구분자 등록 형식이 잘못됐습니다.: " + input.substring(i, i + 5));
            }

            customDelimiters.add(input.charAt(i + 2));
        }

        for (Character customDelimiter : customDelimiters) {
            if (isDigit(customDelimiter)) {
                throw new IllegalArgumentException("커스텀 구분자로 숫자를 입력했습니다.: " + (customDelimiter - 45));
            }
        }

        for (Character customDelimiter : customDelimiters) {
            delimiters = delimiters + ("|" + customDelimiter);
        }

        return input.substring(5 * customDelimiters.size());
    }

    private void validateUnsupportedDelimiter(String input) {
        char[] inputChars = input.toCharArray();

        for (char inputChar : inputChars) {
            if (isDigit(inputChar)) {
                continue;
            }
            if (inputChar == '.') {
                continue;
            }

            boolean isProper = false;
            for (int i = 0; i < delimiters.length(); i += 2) {
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

    private List<Double> splitWithDelimiters(String input) {
        List<Double> numberList = new ArrayList<>();

        String[] splits = input.split(delimiters);
        for (String split : splits) {
            if (split.isEmpty()) {
                continue;
            }

            double number = Double.parseDouble(split);
            numberList.add(number);
        }

        return numberList;
    }

    private void validateNumbers(List<Double> numberList) {
        for (double number : numberList) {
            if (number <= 0) {
                throw new IllegalArgumentException("0이나 음수가 입력됐습니다.");
            }
        }
    }

    public List<Double> getNumberList() {
        return new ArrayList<>(numberList);
    }

    public void addNumberListValues() {
        for (double value : numberList) {
            sum += value;
        }
    }

    public double getSum() {
        return sum;
    }
}
