package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @DisplayName("지뢰는 지뢰 셀에만 들어있다.")
    @Test
    void landMineIsExistOnLandMineCell() {
        // given
        int NEAR_BY_LAND_MINE_COUNT = 3;

        Cell emptyCell = new EmptyCell();
        Cell landMineCell = new LandMineCell();
        Cell numberCell = new NumberCell(NEAR_BY_LAND_MINE_COUNT);

        // when
        boolean emptyCellIsLandMine = emptyCell.isLandMine();
        boolean landMineCellIsLandMine = landMineCell.isLandMine();
        boolean numberCellIsLandMine = numberCell.isLandMine();

        // then
        assertFalse(emptyCellIsLandMine);
        assertTrue(landMineCellIsLandMine);
        assertFalse(numberCellIsLandMine);
    }

    @DisplayName("주변 지뢰 개수는 넘버 셀에만 들어있다.")
    @Test
    void landMineCountIsExistOnNumberCell() {
        // given
        int NEAR_BY_LAND_MINE_COUNT = 3;

        Cell emptyCell = new EmptyCell();
        Cell landMineCell = new LandMineCell();
        Cell numberCell = new NumberCell(NEAR_BY_LAND_MINE_COUNT);

        // when
        boolean emptyCellIsLandMine = emptyCell.hasLandMineCount();
        boolean landMineCellIsLandMine = landMineCell.hasLandMineCount();
        boolean numberCellIsLandMine = numberCell.hasLandMineCount();

        // then
        assertFalse(emptyCellIsLandMine);
        assertFalse(landMineCellIsLandMine);
        assertTrue(numberCellIsLandMine);
    }

    @DisplayName("빈 셀을 열면, 빈 상태 값을 받아야한다.")
    @Test
    void emptyCellOpenThenGetEmptyStatus() {
        // given
        Cell cell = new EmptyCell();

        // when
        cell.open();

        // then
        assertEquals(cell.getSnapshot(), CellSnapshot.ofEmpty());
    }

    @DisplayName("지뢰 셀을 열면, 지뢰 상태 값을 받아야한다.")
    @Test
    void landMineCellOpenThenGetLandMineStatus() {
        // given
        Cell cell = new LandMineCell();
        CellSnapshot landMineStatus = CellSnapshot.ofLandMine();

        // when
        cell.open();

        // then
        assertEquals(landMineStatus, cell.getSnapshot());
    }

    @DisplayName("넘버 셀을 열면, 주변 지뢰 개수 상태 값을 받아야한다.")
    @Test
    void landMineCellHasNotCount() {
        // given
        int NEAR_BY_LAND_MINE_COUNT = 3;
        Cell cell = new NumberCell(NEAR_BY_LAND_MINE_COUNT);
        CellSnapshot numberStatus = CellSnapshot.ofNumber(NEAR_BY_LAND_MINE_COUNT);

        // when
        cell.open();

        // then
        assertEquals(numberStatus, cell.getSnapshot());
    }

    @DisplayName("모든 셀은 깃발을 뽑을 수 있어야 한다.")
    @Test
    void allCellCanFlag() {
        // given
        int NEAR_BY_LAND_MINE_COUNT = 3;

        Cell emptyCell = new EmptyCell();
        Cell landMineCell = new LandMineCell();
        Cell numberCell = new NumberCell(NEAR_BY_LAND_MINE_COUNT);

        CellSnapshot flagStatus = CellSnapshot.ofFlag();

        // when
        emptyCell.flag();
        landMineCell.flag();
        numberCell.flag();

        // then
        assertEquals(flagStatus, emptyCell.getSnapshot());
        assertEquals(flagStatus, landMineCell.getSnapshot());
        assertEquals(flagStatus, numberCell.getSnapshot());
    }

    @DisplayName("모든 셀은 깃발을 뽑을 수 있어야 한다.")
    @Test
    void allCellDefaultIsUnchecked() {
        // given
        int NEAR_BY_LAND_MINE_COUNT = 3;

        Cell emptyCell = new EmptyCell();
        Cell landMineCell = new LandMineCell();
        Cell numberCell = new NumberCell(NEAR_BY_LAND_MINE_COUNT);

        CellSnapshot uncheckedStatus = CellSnapshot.ofUnchecked();

        // when

        // then
        assertEquals(uncheckedStatus, emptyCell.getSnapshot());
        assertEquals(uncheckedStatus, landMineCell.getSnapshot());
        assertEquals(uncheckedStatus, numberCell.getSnapshot());
    }
}