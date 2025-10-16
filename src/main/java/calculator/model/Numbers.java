package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Numbers {

    private final List<Double> numberList;
    private double sum;

    public Numbers(List<Double> numberList) {
        this.numberList = new ArrayList<>(numberList);
        sum = 0.0;
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
