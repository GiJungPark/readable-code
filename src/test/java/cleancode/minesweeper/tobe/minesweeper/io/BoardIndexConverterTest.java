package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BoardIndexConverterTest {

    @DisplayName("열 인덱스는 1 이상의 숫자를 입력 받을 수 있다.")
    @Test
    void validGetSelectedRowIndex() {
        // given
        String userColInput = "c";
        int userRowInput = 1;
        String userInput = userColInput + userRowInput;

        BoardIndexConverter converter = new BoardIndexConverter();

        // when
        int rowIndex = converter.getSelectedRowIndex(userInput);

        // then
        assertEquals(userRowInput - 1, rowIndex);
    }

    @DisplayName("열 인덱스는 0 미만인 숫자는 입력 받을 수 없다.")
    @Test
    void invalidGetSelectedRowIndex() {
        // given
        String userColInput = "c";
        int userRowInput = 0;
        String userInput = userColInput + userRowInput;

        BoardIndexConverter converter = new BoardIndexConverter();

        // when

        // then
        assertThatThrownBy(() -> converter.getSelectedRowIndex(userInput))
                .isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @DisplayName("행 인덱스는 화면에 나타나는 알파벳 문자만 입력할 수 있다.")
    @Test
    void validGetSelectedColIndex() {
        // given
        String userColInput = "a";
        int userRowInput = 1;
        String userInput = userColInput + userRowInput;

        BoardIndexConverter converter = new BoardIndexConverter();

        char[] temp = userColInput.toCharArray();
        int expectedColInput = temp[0] - 'a';

        // when
        int colIndex = converter.getSelectedColIndex(userInput);

        // then

        assertEquals(expectedColInput, colIndex);
    }

    @DisplayName("행 인덱스는 특수 문자를 입력받을수 없다.")
    @Test
    void invalidGetSelectedColIndex() {
        // given
        String userColInput = "]";
        int userRowInput = 1;
        String userInput = userColInput + userRowInput;

        BoardIndexConverter converter = new BoardIndexConverter();

        // when

        // then
        assertThatThrownBy(() -> converter.getSelectedColIndex(userInput))
                .isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력입니다.");
    }
}