package calculator.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InputViewTest {

    InputView inputView = new InputView();


    @Test
    void extractNumbers() {
        //given
        String[] corrects = {"1.2:2:3", "1.2,2,3", "1.2:2,3", "1.2,,2,3"};
        String correctWithBlank = "";
        String[] wrongs = {"1.0a2", "1.0,a2", "1.0a,2", "1.0,2a", "a1.0,2"};

        //when && then
        for (String correct : corrects) {
            assertThat(inputView.extractNumbers(correct).getNumberList()).contains(1.2, 2.0, 3.0);
        }

        assertThat(inputView.extractNumbers(correctWithBlank).getNumberList()).isEmpty();


        for (String wrong : wrongs) {
            Assertions.assertThatThrownBy(() -> inputView.extractNumbers(wrong))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}