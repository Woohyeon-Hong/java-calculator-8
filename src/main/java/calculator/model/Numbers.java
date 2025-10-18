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

        //각 구분자는 '|"로 구분됨
        this.delimiters = ",|:";
        this.numberList = new ArrayList<>();
        this.sum = 0.0;
    }

    public void extractNumbers() {
        //custom delimiter 처리
        if (hasCustomDelimiter()) {
            registerCustomDelimiters();
        }

        validateUnsupportedDelimiter();
        splitWithDelimiters();
        validateNumbers();
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

    private boolean hasCustomDelimiter() {
        return input.startsWith("//");
    }

    private void registerCustomDelimiters() {
        List<Character> customDelimiterCandidates = validateCustomDelimiters();
        addCustomDelimiters(customDelimiterCandidates);
        removeCustomDelimiterSection(customDelimiterCandidates.size());
    }

    private void validateUnsupportedDelimiter() {
        char[] inputCharArray = input.toCharArray();

        for (char inputChar : inputCharArray) {
            validateCharacter(inputChar);
        }
    }

    private void splitWithDelimiters() {
        String[] splits = input.split(delimiters);

        for (String split : splits) {

            //빈 값은 생략 ex) "1,,2" |  ","
            if (split.isEmpty()) {
                continue;
            }

            double number = Double.parseDouble(split);
            numberList.add(number);
        }
    }

    private void validateNumbers() {
        for (double number : numberList) {
            if (number <= 0) {
                throw new IllegalArgumentException("0이나 음수가 입력됐습니다");
            }
        }
    }

    private List<Character> validateCustomDelimiters() {
        List<Character> customDelimiterCandidates = new ArrayList<>();
        validateCustomDelimiterFormat(customDelimiterCandidates);
        validateCustomDelimiterValue(customDelimiterCandidates);
        return customDelimiterCandidates;
    }

    private void addCustomDelimiters(List<Character> customDelimiters) {
        for (Character customDelimiter : customDelimiters) {
            delimiters += ("|" + customDelimiter);
        }
    }

    private void removeCustomDelimiterSection(int customDelimitersNum) {
        //각 커스텀 구분자 등록은 5 개의 문자로 구성되기 때문에, 5 * customDelimiterNum(등록한 커스텀 구분자 개수) 길이의 문자열을 삭제
        input = input.substring(5 * customDelimitersNum);
    }

    private void validateCharacter(char inputChar) {
        //1.25의 "1"이나 "."과 같이 수를 구성하는 요소가 아니면서, 등록된 구분자도 아니면 예외 처리
        if (!isPartOfNumber(inputChar) && !isRegisteredAsDelimiter(inputChar)) {
            throw new IllegalArgumentException("잘못된 구분자가 사용되었습니다: " + inputChar);
        }
    }

    private void validateCustomDelimiterFormat(List<Character> customDelimiters) {
        for (int i = 0; i <= input.length() - 5; i += 5) {
            if (isDigit(input.charAt(i))) {
                break;
            }

            //prefix: "//" -> 2글자
            String prefix = input.substring(i, i + 2);
            //suffix: "\n" -> 2글자
            String suffix = input.substring(i + 3, i + 5);

            if (!prefix.equals("//") || !suffix.equals("\\n")) {
                throw new IllegalArgumentException("커스텀 구분자 등록 형식이 잘못됐습니다: " + input.substring(i, i + 5));
            }

            //등록할 구분자는 커스텀 구분자 등록 형식의 3 번째에 위치
            customDelimiters.add(input.charAt(i + 2));
        }
    }

    private void validateCustomDelimiterValue(List<Character> customDelimiters) {
        for (Character customDelimiter : customDelimiters) {
            if (isDigit(customDelimiter)) {
                throw new IllegalArgumentException("커스텀 구분자로 숫자를 입력했습니다: " + (customDelimiter - 45));
            }
        }
    }

    private boolean isPartOfNumber(char inputChar) {
        return (isDigit(inputChar) || inputChar == '.');
    }

    private boolean isRegisteredAsDelimiter(char inputChar) {
        boolean isProper = false;
        for (int i = 0; i < delimiters.length(); i += 2) {
            if (inputChar == delimiters.charAt(i)) {
                isProper = true;
                break;
            }
        }
        return isProper;
    }

    private boolean isDigit(char target) {
        /**
         * ASCII 코드
         * 0: 48
         * 1: 49
         * ...
         * 9: 57
         */
        return (target >= 48 && target <= 57);
    }
}
