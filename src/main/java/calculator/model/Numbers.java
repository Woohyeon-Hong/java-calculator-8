package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Numbers {

    private final List<Double> numberList;

    public Numbers(List<Double> numberList) {
        this.numberList = new ArrayList<>(numberList);
    }

    public List<Double> getNumberList() {
        return new ArrayList<>(numberList);
    }
}
