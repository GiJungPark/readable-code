package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CellPositionTest {

    @DisplayName("좌표 값이 음수면 예외를 발생시킨다.")
    @Test
    void 좌표_값이_음수면_예외를_발생시킨다() {
        // given
        int rowIndex = -1;
        int colIndex = -1;

        // when

        // then
        assertThatThrownBy(() -> CellPosition.of(rowIndex, colIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }

    @DisplayName("좌표 값은 0 이상의 값만 받을 수 있다.")
    @Test
    void 좌표_값은_0_이상의_값만_받을_수_있다() {
        // given
        int rowIndex = 0;
        int colIndex = 0;

        // when
        CellPosition position = CellPosition.of(rowIndex, colIndex);

        // then
        assertEquals(rowIndex, position.getRowIndex());
        assertEquals(colIndex, position.getColIndex());
    }
}