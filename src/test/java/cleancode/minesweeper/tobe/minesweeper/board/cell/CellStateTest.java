package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellStateTest {

    @DisplayName("셀 상태를 오픈 또는 플래그로 변경하면 체크한 상태가 된다.")
    @Test
    void isChecked() {
        // given
        CellState openCellState = CellState.initialize();
        CellState flagCellState = CellState.initialize();

        // when
        openCellState.open();
        flagCellState.flag();

        // then
        assertTrue(openCellState.isChecked());
        assertTrue(flagCellState.isChecked());
    }

    @DisplayName("셀 상태를 오픈으로 변경하면 오픈 상태가 된다.")
    @Test
    void isOpened() {
        // given
        CellState cellState = CellState.initialize();

        // when
        cellState.open();

        // then
        assertTrue(cellState.isOpened());
        assertFalse(cellState.isFlagged());
    }

    @DisplayName("셀 상태를 플래그로 변경하면 플래그 상태가 된다.")
    @Test
    void isFlagged() {
        // given
        CellState cellState = CellState.initialize();

        // when
        cellState.flag();

        // then
        assertFalse(cellState.isOpened());
        assertTrue(cellState.isFlagged());
    }
}