package calculator.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Test
    void numbers() {
        //given
        List<Double> numberList = new ArrayList<Double>();
        double d = 1.0;
        while (d <= 5.0) {
            numberList.add(d++);
        }

        //when
        Numbers numbers = new Numbers(numberList);

        //then
        Assertions.assertThat(numbers.getNumberList()).contains(1.0, 2.0, 3.0, 4.0, 5.0);
    }

}