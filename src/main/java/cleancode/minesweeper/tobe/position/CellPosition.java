package cleancode.minesweeper.tobe.position;

import java.util.Objects;

public class CellPosition {

    private final int rowIndex;
    private final int colInext;

    private CellPosition(int rowIndex, int colIndex) {
        if (rowIndex < 0|| colIndex < 0) {
            throw new IllegalArgumentException("올바르지 않은 좌표입니다.");
        }

        this.rowIndex = rowIndex;
        this.colInext = colIndex;
    }

    public static CellPosition of(int rowIndex, int colIndex) {
        return new CellPosition(rowIndex, colIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return rowIndex == that.rowIndex && colInext == that.colInext;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colInext);
    }
}
