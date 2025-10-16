package calculator.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InputViewTest {

    InputView inputView;

    @Test
    void extractNumbers_커스텀_구분자_등록x() {
        //given
        String[] corrects = {
                "1.2,2,3",      //쉼표 ','만 사용
                "1.2:2:3",      //콜론 ':'만 사용
                "1.2,2:3",      //기본 구분자 ','와 ':' 혼합 사용
                "1.2,,2::3"     //구분자가 연속으로 사용된 경우
        };

        String[] wrongs = {
                "1.0,-2,3",     //음수 포함
                "1.0:2:0",      //0 포함
                "1.0,2,3a",     //허용되지 않는 문자 'a' 포함
                "1.0,2$3",      //허용되지 않는 문자 '$' 포함
        };

        //when && then

        //정상 동작
        for (String correct : corrects) {
            inputView = new InputView();
            assertThat(inputView.extractNumbers(correct).getNumberList()).contains(1.2, 2.0, 3.0);
        }

        //정상 동작 - 빈 문자열
        inputView = new InputView();
        assertThat(inputView.extractNumbers("").getNumberList()).isEmpty();


        //예외 발생
        for (String wrong : wrongs) {
            inputView = new InputView();
            Assertions.assertThatThrownBy(() -> inputView.extractNumbers(wrong))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void extractNumbers_커스텀_구분자_등록o() {
        //given
        String[] corrects = {
                "//a\\n1.2a2a3",    //단일 커스텀 구분자 'a'만 사용
                "//a\\n1.2a2,3",    //커스텀 구분자 'a'와 쉼표 ',' 혼합
                "//a\\n1.2a2:3",    //커스텀 구분자 'a'와 콜론 ':' 혼합
                "//:\\n1.2,2,3",    //커스텀 구분자 ':' (기본 구분자와 동일)와 기본 구분자 ','
                "//-\\n1.2-2:3",    //단일 커스텀 구분자 '-'와 콜론 ':' 혼합
                //"//|\\n1.2|2|3",  //단일 커스텀 구분자 '|'만 사용 (정규식 이스케이프 필요)
        };

        String[] wrongs = {
                //커스텀 구분자 등록 예외
                "//ab\\n1ab2,3",    // 커스텀 구분자가 문자열인 경우
                "//5\\n152,3",      // 커스텀 구분자가 숫자인 경우
                "//\\n1,2,3",        // '//'와 '\n' 사이에 커스텀 구분자가 누락된 경우
                "//a1.0a2,3",       // '\n'이 누락된 경우

                //숫자 값/문자열 오류
                "//a\\n1.0a-2,3",         //커스텀 구분자 사용 후 음수 포함
                "//a\\n1.0a-2,3,0",         //커스텀 구분자 사용 후 0 포함
                "//a\\n1.0a2$3",          //커스텀 구분자 외 '$'와 같은 허용되지 않는 문자 포함
        };

        //when && then
        for (String correct : corrects) {
            inputView = new InputView();
            assertThat(inputView.extractNumbers(correct).getNumberList()).contains(1.2, 2.0, 3.0);
        }

        inputView = new InputView();
        assertThat(inputView.extractNumbers("//a\\n").getNumberList()).isEmpty();


        for (String wrong : wrongs) {
            inputView = new InputView();
            Assertions.assertThatThrownBy(() -> inputView.extractNumbers(wrong))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}